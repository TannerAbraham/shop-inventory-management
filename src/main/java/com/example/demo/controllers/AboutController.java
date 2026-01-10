package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Part D: Controller for About page
 */
@Controller
public class AboutController {
    
    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }
}
