package com.computerAccessoriesStore.controller.main;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/home";
        }
        return "main/main";
    }

    @GetMapping("/home")
    public String homePage() {
        return "main/home";
    }

    @GetMapping("/map")
    public String ourAccommodation() {
        return "main/ourAccommodation";
    }
}
