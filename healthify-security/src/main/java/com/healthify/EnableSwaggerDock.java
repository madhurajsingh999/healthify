package com.healthify;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.healthify.config.OpenAPIConfig;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented

@Import({OpenAPIConfig.class})
@Configuration
public @interface EnableSwaggerDock {
    
}
