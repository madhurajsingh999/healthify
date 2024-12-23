package com.healthify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class OpenApiController {
    
    @RequestMapping("/")
    public String openSwaggerUI() {
        log.info("DOC Controller");
        return "redirect:/webjars/swagger-ui/index.html";
    }
}
