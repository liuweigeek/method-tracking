package com.scott.spring.methodtracking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: scott
 * @Email: <a href="liuweigeek@outlook.com">scott</a>
 * @Date: 2020/6/6 16:28
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;

    private String username;

    private String password;

    private Integer age;
}
