package com.navi.modelSchool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {
    @RequestMapping({"","/","/home"})
    public String displayHome(Model model){
        model.addAttribute("username", "Navi");
        return "home.html";
    }
}
