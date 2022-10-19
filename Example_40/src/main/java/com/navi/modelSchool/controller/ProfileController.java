package com.navi.modelSchool.controller;

import com.navi.modelSchool.model.Person;
import com.navi.modelSchool.model.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class ProfileController {
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
}
