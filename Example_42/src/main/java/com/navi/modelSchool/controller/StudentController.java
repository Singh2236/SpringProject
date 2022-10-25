package com.navi.modelSchool.controller;

import com.navi.modelSchool.model.Person;
import com.navi.modelSchool.repository.CoursesRepository;
import com.navi.modelSchool.repository.ModelClassRepository;
import com.navi.modelSchool.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("student")
public class StudentController {
    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model, HttpSession httpSession) {
        Person person = (Person) httpSession.getAttribute("loggedInPerson");
        ModelAndView modelAndView = new ModelAndView("courses_enrolled.html");
        modelAndView.addObject("person", person);
        return modelAndView;
    }
}
