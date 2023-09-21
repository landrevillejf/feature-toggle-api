package com.protonmail.landrevillejf.featuretoggle.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        // Using "redirect:" to serve the Swagger UI HTML page
        return "redirect:/swagger-ui/index.html";
    }
}

