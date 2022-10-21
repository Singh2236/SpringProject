package com.navi.modelSchool.controller;

import com.navi.modelSchool.model.ModelClass;
import com.navi.modelSchool.repository.ModelClassRepository;
import com.navi.modelSchool.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("admin") // for common admin prefix admin
public class AdminController {
    @Autowired
    ModelClassRepository modelClassRepository;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<ModelClass> modelClasses = modelClassRepository.findAll(); //get all the classes available in the databse
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("modelClass", new ModelClass());
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("modelClass") ModelClass modelClass) {
        modelClassRepository.save(modelClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }
}
