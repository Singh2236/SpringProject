package com.navi.modelSchool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {
    @RequestMapping({"","/","/home", "/home.html"})
    public String displayHome(){
        return "home.html";
    }
}
