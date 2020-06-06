package com.scott.spring.methodtracking.repository;

import com.scott.spring.methodtracking.annotation.TimeTrack;
import com.scott.spring.methodtracking.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author: scott
 * @Email: <a href="wliu@fleetup.com">scott</a>
 * @Date: 2020/6/6 16:33
 * @Description: Fake repository
 */
@TimeTrack
@Repository
public class UserRepository {

    public Stream<User> findAll() {
        return Stream.of(new User(1L, "Scott", "", 24),
                new User(1L, "Jeremy", "", 25),
                new User(1L, "Alex", "", 23));
    }

    public Optional<User> findById(Long id) {
        return Optional.of(User.builder()
                .id(id)
                .username("Scott")
                .password("")
                .age(24)
                .build());
    }

    public Optional<User> getByUsername(String username) {
        return Optional.of(User.builder()
                .id(1L)
                .username(username)
                .password("")
                .age(24)
                .build());
    }

    public Optional<User> getByUsernameAndAge(String username, Integer age) {
        return Optional.of(User.builder()
                .id(1L)
                .username(username)
                .password("")
                .age(age)
                .build());
    }
}
