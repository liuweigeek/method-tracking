package com.scott.spring.methodtracking.controller;

import com.scott.spring.methodtracking.annotation.TimeTrack;
import com.scott.spring.methodtracking.domain.User;
import com.scott.spring.methodtracking.service.PasswordService;
import com.scott.spring.methodtracking.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: scott
 * @Email: <a href="wliu@fleetup.com">scott</a>
 * @Date: 2020/6/6 16:38
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordService passwordService;

    public UserController(UserService userService,
                          PasswordService passwordService) {
        this.userService = userService;
        this.passwordService = passwordService;
    }

    @TimeTrack
    @GetMapping
    public List<User> findAll() {
        return userService.findAllUsers();
    }

    @TimeTrack
    @GetMapping("/{username}")
    public User getByUsername(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        user.setPassword(passwordService.getPassword());
        return user;
    }

    @TimeTrack
    @GetMapping("/{username}/{age}")
    public User getByUsername(@PathVariable("username") String username, @PathVariable("age") Integer age) {
        User user = userService.findByUsernameAndAge(username, age);
        user.setPassword(passwordService.getPassword());
        return user;
    }
}
