package com.brodygaudel.gestionemployee.securities.services;

import com.brodygaudel.gestionemployee.securities.entities.Role;
import com.brodygaudel.gestionemployee.securities.entities.User;
import com.brodygaudel.gestionemployee.securities.repositories.RoleRepository;
import com.brodygaudel.gestionemployee.securities.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        log.info("user saved");
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User u = userRepository.findById(user.getUserId()).orElse(null);
        if(u != null){
            u.setUsername(user.getUsername());
            u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            u.setEnabled(user.getEnabled());
            log.info("user updated");
            return userRepository.save(u);
        }
        log.info("user not found");
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role addRole(Role role) {
        Role r = roleRepository.findByName(role.getName());
        if(r==null){
            log.info("role saved");
            return roleRepository.save(role);
        }else{
            log.info("role not saved");
            return null;
        }
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public User addRoleToUser(String username, String rolename) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(rolename);
        user.getRoles().add(role);
        log.info("role added to user");
        return user;
    }

    @Override
    public User removeRoleFromUser(String username, String rolename) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(rolename);
        user.getRoles().remove(role);
        log.info("role removed from user");
        return user;
    }

    @Override
    public User findUserById(Long id) {
        User u = userRepository.findById(id).orElse(null);
        if (u != null){
            log.info("user found .");
            return u;
        }
        log.error("user not found .");
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        log.info("list of users returned");
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        log.info("user deleted");
    }

    @Override
    public User createUser(User user) {
        User u = saveUser(user);
        addRoleToUser(u.getUsername(), "USER");
        return u;
    }
}
