package com.healthify.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("greet")
public class HelloController {

    @GetMapping
    public ResponseEntity<String> greet(@RequestParam(required = false,defaultValue = "Gust") String name){
        String msg="Hello "+name;
        return ResponseEntity.ok(msg);
    }
}
