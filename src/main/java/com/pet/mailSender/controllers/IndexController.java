package com.pet.mailSender.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @GetMapping
    public String index(Model model){
        model.addAttribute("attr","this is value");
        return "index";
    }
}
