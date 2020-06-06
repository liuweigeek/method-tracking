package com.scott.spring.methodtracking.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: scott
 * @Email: <a href="wliu@fleetup.com">scott</a>
 * @Date: 2020/6/6 16:24
 * @Description:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TimeTrack {
}
