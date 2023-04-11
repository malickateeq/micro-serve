package com.malikatique.microserve.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "AuthServe";
    }

    @GetMapping("/auth")
    public String authenticated() {
        return "AuthServe Authenticated.";
    }

}
