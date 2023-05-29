package com.brodygaudel.gestionemployee.securities.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";

    @Autowired
    private AuthenticationManager authenticationManager;


    @Bean
    public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
                                             UserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();

                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(List.of("Authorization"));
                    config.setMaxAge(3600L);
                    return config;
                }).and()
                .authorizeHttpRequests()
                .requestMatchers("/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/employee/list/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/employee/get/**").hasAnyAuthority(ADMIN, USER)
                .requestMatchers(HttpMethod.GET, "/employee/search/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.POST, "/employee/save/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.PUT, "/employee/update/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/employee/delete/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/employee/clear/**").hasAuthority(ADMIN)

                .requestMatchers(HttpMethod.GET, "/formation/list/**").hasAnyAuthority(ADMIN, USER)
                .requestMatchers(HttpMethod.GET, "/formation/get/**").hasAnyAuthority(ADMIN, USER)
                .requestMatchers(HttpMethod.POST, "/formation/save/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.PUT, "/formation/update/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/formation/delete/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/formation/clear/**").hasAuthority(ADMIN)

                .requestMatchers(HttpMethod.GET, "/experience/list/**").hasAnyAuthority(ADMIN, USER)
                .requestMatchers(HttpMethod.GET, "/experience/get/**").hasAnyAuthority(ADMIN, USER)
                .requestMatchers(HttpMethod.POST, "/experience/save/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.PUT, "/experience/update/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/experience/delete/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/experience/clear/**").hasAuthority(ADMIN)

                .requestMatchers(HttpMethod.GET, "/absence/list/**").hasAnyAuthority(ADMIN, USER)
                .requestMatchers(HttpMethod.GET, "/absence/get/**").hasAnyAuthority(ADMIN, USER)
                .requestMatchers(HttpMethod.POST, "/absence/save/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.PUT, "/absence/update/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/absence/delete/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/absence/clear/**").hasAuthority(ADMIN)

                .anyRequest().authenticated().and()
                .addFilterBefore(new JWTAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
