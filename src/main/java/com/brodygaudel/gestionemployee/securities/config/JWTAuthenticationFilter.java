package com.brodygaudel.gestionemployee.securities.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.brodygaudel.gestionemployee.securities.entities.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            user = null;
            e.printStackTrace();
        }
        assert user != null;
        return authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) {
        org.springframework.security.core.userdetails.User springUser =
                (org.springframework.security.core.userdetails.User)
                        authResult.getPrincipal();
        List<String> roles = new ArrayList<>();
        springUser.getAuthorities().forEach(au-> roles.add(au.getAuthority()));
        String jwt = JWT.create().
                withSubject(springUser.getUsername()).
                withArrayClaim("roles", roles.toArray(new String[roles.size()])).
                withExpiresAt(new Date(System.currentTimeMillis()+10*24*60*60*1000)).
                sign(Algorithm.HMAC256(SecParams.SECRET));
        response.addHeader("Authorization", jwt);
    }

}
