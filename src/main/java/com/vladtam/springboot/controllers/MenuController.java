package com.vladtam.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
    private MenuController(){}

    @GetMapping
    public static String mainMenu(){
        return "menuPages/mainMenu";
    }
}
