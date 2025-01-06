package com.healthify;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.healthify.config.UserDetailServiceImpl;
import com.healthify.config.WebSecurityConfig;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented

@Import({WebSecurityConfig.class,UserDetailServiceImpl.class})
@Configuration
public @interface EnableServiceSecurity {
}
