package com.brodygaudel.gestionemployee.securities.services;

import com.brodygaudel.gestionemployee.securities.entities.Role;
import com.brodygaudel.gestionemployee.securities.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User updateUser(User user);
    User findUserByUsername (String username);
    Role addRole(Role role);
    List<Role> findAllRoles();
    User addRoleToUser(String username, String rolename);
    User removeRoleFromUser(String username, String rolename);
    User findUserById(Long id);
    List<User> findAllUsers();
    void deleteUser(Long id);
    User createUser(User user);
}
