package com.navi.modelSchool.controller;

import com.navi.modelSchool.model.Courses;
import com.navi.modelSchool.model.ModelClass;
import com.navi.modelSchool.model.Person;
import com.navi.modelSchool.repository.CoursesRepository;
import com.navi.modelSchool.repository.ModelClassRepository;
import com.navi.modelSchool.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("admin") // for admin urls
public class AdminController {
    @Autowired
    ModelClassRepository modelClassRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CoursesRepository coursesRepository;

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
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession httpSession, @RequestParam(value = "error", required = false) String error) {
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
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + modelClass.getClassId() + "&error=true");
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
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId=" + modelClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model) {
        // static sorting
        //List<Courses> courses = coursesRepository.findByOrderByNameDesc();

        //Dynamic Sorting
        List<Courses> courses = coursesRepository.findAll(Sort.by("name")/*.descending()*/);

        ModelAndView modelAndView = new ModelAndView("courses_secure.html"); //since we already have courses.html
        modelAndView.addObject("courses", courses); //List of all the courses in the DB for Table view
        modelAndView.addObject("course", new Courses());//sending an empty object for new Course objects
        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(Model model, @ModelAttribute("course") Courses addCourse) {
        ModelAndView modelAndView = new ModelAndView();
        coursesRepository.save(addCourse);
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return modelAndView;
    }

    //viewStudents(id=${course1.courseId})

    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(Model model, @RequestParam int id, HttpSession httpSession,
                                     @RequestParam(required = false) String error) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("course_students.html");
        Optional<Courses> courses = coursesRepository.findById(id);
        modelAndView.addObject("courses", courses.get());
        modelAndView.addObject("person", new Person());
        httpSession.setAttribute("courses", courses.get());
        if (error != null) {
            errorMessage = "Invalid Email Address";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(Model model, @ModelAttribute("person") Person person, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        Courses courses = (Courses) httpSession.getAttribute("courses");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if (personEntity == null || !(personEntity.getPersonId() > 0)) {
            modelAndView.setViewName("redirect:/admin/viewStudents?id=" + courses.getCourseId() + "&error=true");
            return modelAndView;
        }
        personEntity.getCourses().add(courses);
        courses.getPersons().add(personEntity);
        personRepository.save(personEntity);
        httpSession.setAttribute("courses", courses);
        modelAndView.setViewName("redirect:/admin/viewStudents?id=" + courses.getCourseId());
        return modelAndView;
    }

    ///admin/deleteStudentFromCourse(personId=${person.personId})
    @GetMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourse(Model model, @RequestParam int personId,
                                                HttpSession httpSession) {
        Courses courses = (Courses) httpSession.getAttribute("courses");
        Optional<Person> person = personRepository.findById(personId);
        person.get().getCourses().remove(courses);
        courses.getPersons().remove(person);
        personRepository.save(person.get());
        httpSession.setAttribute("courses", courses);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/viewStudents?id=" + courses.getCourseId());
        return modelAndView;
    }
}
