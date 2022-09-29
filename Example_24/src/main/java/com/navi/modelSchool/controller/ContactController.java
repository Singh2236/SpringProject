package com.navi.modelSchool.controller;

import com.navi.modelSchool.model.Contact;
import com.navi.modelSchool.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ContactController {
    private static Logger log = LoggerFactory.getLogger(ContactController.class);

    private final ContactService contactService;
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html"; // this contact.html referring to the same page not the fresh contact page.
    }


    /*@PostMapping(value = "/saveMsg")
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

    }*/

    @PostMapping(value = "/saveMsg")
    public String displayContactPage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {  //How the fuck, this method knows about the variable coming from UI.
        if (errors.hasErrors()) {
            log.error("Contact form validation failed due to: " + errors.toString());
            return "contact.html"; //This is not the new fresh page but the same page, where user was typing the information.
        }
        contactService.saveContactData(contact);
        return "redirect:/contact"; // This is new page shown when the form is submitter correctly.
    }

}
