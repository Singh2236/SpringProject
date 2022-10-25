package com.navi.modelSchool.controller;

import com.navi.modelSchool.model.Person;
import com.navi.modelSchool.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession httpSession) {
        Person person = personRepository.readByEmail(authentication.getName()); // through get Name we are going to get the email of the user
        model.addAttribute("username", person.getName()); //once we have a person we can get his name
        model.addAttribute("roles", authentication.getAuthorities().toString());

        if (null != person.getModelClass() && null != person.getModelClass().getName()) {
            model.addAttribute("enrolledClass", person.getModelClass().getName());
        }

        httpSession.setAttribute("loggedInPerson", person);  //storing person's information to the session.
      return "dashboard.html";
    }

}