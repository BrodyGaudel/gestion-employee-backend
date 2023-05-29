package com.brodygaudel.gestionemployee.securities.restcontroller;

import com.brodygaudel.gestionemployee.securities.entities.User;
import com.brodygaudel.gestionemployee.securities.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/security")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
       return userService.findAllUsers();
    }

    @GetMapping("/get/{id}")
    public User findUserById(@PathVariable(name = "id") Long id){
        return userService.findUserById(id);
    }

    @PostMapping("/save")
    public User addUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

}
