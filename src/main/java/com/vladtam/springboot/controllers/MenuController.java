package com.vladtam.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
    @GetMapping
    public static String mainMenu(){
        return "menuPages/mainMenu";
    }
}
