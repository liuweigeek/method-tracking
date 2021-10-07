package com.scott.spring.methodtracking.service;

import com.scott.spring.methodtracking.annotation.TimeTrack;
import com.scott.spring.methodtracking.domain.User;
import com.scott.spring.methodtracking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: scott
 * @Email: <a href="liuweigeek@outlook.com">scott</a>
 * @Date: 2020/6/6 16:34
 * @Description:
 */
@TimeTrack
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordService passwordService;

    public UserService(UserRepository userRepository,
                       PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public List<User> findAllUsers() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRepository.findAll().collect(Collectors.toList());
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.getByUsername(username).orElse(new User());
    }

    public User findByUsernameAndAge(String username, Integer age) {
        User user = userRepository.getByUsernameAndAge(username, age).orElse(new User());
        user.setPassword(passwordService.getPassword());
        return user;
    }

}
