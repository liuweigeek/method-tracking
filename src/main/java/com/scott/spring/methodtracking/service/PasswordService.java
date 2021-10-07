package com.scott.spring.methodtracking.service;

import com.scott.spring.methodtracking.annotation.TimeTrack;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author: scott
 * @Email: <a href="liuweigeek@outlook.com">scott</a>
 * @Date: 2020/6/6 16:34
 * @Description:
 */
@TimeTrack
@Service
public class PasswordService {

    public String getPassword() {
        return UUID.randomUUID().toString();
    }

}
