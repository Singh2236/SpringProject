package com.navi.modelSchool.controller;

import com.navi.modelSchool.model.ModelClass;
import com.navi.modelSchool.model.Person;
import com.navi.modelSchool.repository.ModelClassRepository;
import com.navi.modelSchool.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

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
        List<ModelClass> modelClasses = modelClassRepository.findAll(); //get all the classes available in the database
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("modelClasses", modelClasses);  // model full of all the classes information
        modelAndView.addObject("modelClass", new ModelClass()); //empty object to get the information of the new class from the front end
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("modelClass") ModelClass modelClass) {
        modelClassRepository.save(modelClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        Optional<ModelClass> modelClass = modelClassRepository.findById(id);
        for (Person person : modelClass.get().getPersons()) {
            person.setModelClass(null);
            personRepository.save(person);
        }
        modelClassRepository.deleteById(id);

        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;


    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession httpSession,
                                        @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<ModelClass> modelClass = modelClassRepository.findById(classId);
        modelAndView.addObject("modelClass", modelClass.get()); // using .get() method to get the object from the optional data type
        modelAndView.addObject("person", new Person());
        httpSession.setAttribute("modelClass", modelClass.get()); //saving the modelClass object in the session
        if (error != null) {
            errorMessage = "Invalid Email Entered";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        ModelClass modelClass = (ModelClass) httpSession.getAttribute("modelClass");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if (personEntity == null || !(personEntity.getPersonId() > 0)) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + modelClass.getClassId()
                    + "&error=true");
            return modelAndView;
        }
        personEntity.setModelClass(modelClass);
        personRepository.save(personEntity);
        modelClass.getPersons().add(personEntity);
        modelClassRepository.save(modelClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + modelClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession httpSession) {
        ModelClass modelClass = (ModelClass) httpSession.getAttribute("modelClass");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setModelClass(null);
        modelClass.getPersons().remove(person.get());
        ModelClass modelClassSaved = modelClassRepository.save(modelClass);
        httpSession.setAttribute("modelClass", modelClassSaved);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId=" +
                modelClass.getClassId());
        return modelAndView;
    }
}
