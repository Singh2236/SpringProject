package com.navi.modelSchool.controller;

import com.navi.modelSchool.model.Person;
import com.navi.modelSchool.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model,Authentication authentication) {
        Person person = personRepository.readByEmail(authentication.getName()); // through get Name we are going to get the email of the user
        model.addAttribute("username", person.getName()); //once we have a person we can get his name
        model.addAttribute("roles", authentication.getAuthorities().toString());
      return "dashboard.html";
    }

}