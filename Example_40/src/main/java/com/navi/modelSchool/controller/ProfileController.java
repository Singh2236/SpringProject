package com.navi.modelSchool.controller;

import com.navi.modelSchool.model.Address;
import com.navi.modelSchool.model.Person;
import com.navi.modelSchool.model.Profile;
import com.navi.modelSchool.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
public class ProfileController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayProfile")
    public ModelAndView displayMessages(Model model, HttpSession httpSession) {
        // getting person object from the HttpSession
        Person person = (Person) httpSession.getAttribute("loggedInPerson");
        //Instantiating profile object to sent trough ModelAndView to profile.html
        Profile profile = new Profile();

        //populating the profile fields from the data we get from the person object
        profile.setName(person.getName());
        profile.setMobileNumber(person.getMobileNumber());
        profile.setEmail(person.getEmail());

        //checking if the address values are populated in the Db if yes, we are also showing those to the user.
        if (person.getAddress() != null && person.getAddress().getAddressId() > 0) {
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());
        }

        // Instantiating ModelAndView to be returned through this method
        ModelAndView modelAndView = new ModelAndView("profile.html");

        // adding an object of type profile to the fround end
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors, HttpSession httpSession) {
        if (errors.hasErrors()) {
            return "profile.html";
        }
        Person person = (Person) httpSession.getAttribute("loggedInPerson");
        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());

        if (person.getAddress() != null && person.getAddress().getAddressId() > 0) {
            person.setAddress(new Address());

        }
        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());

        personRepository.save(person);

        httpSession.setAttribute("loggedInPerson", person);
        return "redirect/:displayProfile";
    }
}
