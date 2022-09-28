package com.navi.modelSchool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
    private static Logger log = LoggerFactory.getLogger(ContactController.class);


    @PostMapping(value = "/saveMsg")
    //@RequestMapping(value = "/saveMsg", method = POST)
    public ModelAndView displayContactPage(@RequestParam String name, @RequestParam(value = "mobileNum") String mob,
                                           @RequestParam String email, @RequestParam String subject,
                                           @RequestParam String message) {
        log.info("Name: " + name);
        log.info("Mobile Number  " + mob);
        log.info("Email:  " + email);
        log.info("Subject: " + subject);
        log.info("Msg:  " + message);

        return new ModelAndView(("redirect:/contact"));

    }
}
