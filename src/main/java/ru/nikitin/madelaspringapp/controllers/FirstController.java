package ru.nikitin.madelaspringapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/first")
    public String firstPage() {
        return "first/firstPage";
    }
}
