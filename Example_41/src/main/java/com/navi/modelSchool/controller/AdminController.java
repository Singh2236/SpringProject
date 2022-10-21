package com.navi.modelSchool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("admin") // for common admin prefix admin
public class AdminController {

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        ModelAndView modelAndView = new ModelAndView("classes.html");
        return modelAndView;
    }
    @RequestMapping("/displayCourses")
    public ModelAndView displayCourses(Model model) {
        ModelAndView modelAndView = new ModelAndView("courses.html");
        return modelAndView;
    }
}
