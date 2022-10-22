# OneToMany, ManyToOne mapping demo using Classes, Students

## Adding more links to the front end in ``dashboard.html``.
There are now new links in our Dashboard page, both of which are authorised for Admin role. 
````thymeleafexpressions
<div class="col-sm-6 col-lg-3" sec:authorize="hasRole('ROLE_ADMIN')">
                    <a th:href="@{/admin/displayClasses}">
                        <div class="overview-item overview-item--c1">
                            <div class="overview__inner">
                                <div class="overview-box clearfix">
                                    <div class="icon">
                                        <i class="fas fa-envelope-open-text"></i>
                                    </div>
                                    <div class="text">
                                        <h2>Classes</h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>

                <div class="col-sm-6 col-lg-3" sec:authorize="hasRole('ROLE_ADMIN')">
                    <a th:href="@{/admin/displayCourses}">
                        <div class="overview-item overview-item--c2">
                            <div class="overview__inner">
                                <div class="overview-box clearfix">
                                    <div class="icon">
                                        <i class="fas fa-envelope-open-text"></i>
                                    </div>
                                    <div class="text">
                                        <h2>Courses</h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>

````

## Granting Admin roles to all the paths which are starting with `/path/*` path. 
``.mvcMatchers("/amin/**").hasRole("ADMIN")``

## Controller for all Admin paths 
````java
@Slf4j
@Controller
@RequestMapping("admin") // for common admin prefix admin
public class AdminController {

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        ModelAndView modelAndView = new ModelAndView("classes.html");
        return modelAndView;
    }
````
## New Table in the Database and adding new column in the Person table 

````mysql
CREATE TABLE IF NOT EXISTS `class` (
    `class_id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` varchar(50) NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`class_id`)
);

ALTER TABLE `person`
    ADD COLUMN `class_id` int NULL AFTER `address_id`,
ADD CONSTRAINT `FK_CLASS_CLASS_ID` FOREIGN KEY (`class_id`)
REFERENCES `class`(`class_id`);
````

## Creating the tables for classes
````java
@Data
@Entity
@Table(name = "class")
public class ModelClass extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int classId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @OneToMany(mappedBy = "modelClass", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Person.class)
    private Set<Person> persons;
}
````

## Adding an extra column in the database Entity for the joining the two tables 

````java
@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "classId" ,nullable = true)
    private ModelClass modelClass;
````

## Adding a new attribute to the modelAndView Object, so that the admin can add classes.

``modelAndView.addObject("modelClass", new ModelClass());``

````java
@Slf4j
@Controller
@RequestMapping("admin") // for common admin prefix admin
public class AdminController {

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("modelClass", new ModelClass());
        return modelAndView;
    }
}
````
## Create a new Template for classes.html

check the file for more information

## Adding and Deleting classes and /displayStudents Enhancement inside our Application

1. Building a method inside AdminController for the path ``/admin/addNewClass`` 
2. New Repo for ModelClass
``@Repository
   public interface ModelClassRepository extends JpaRepository<ModelClass, Integer> {
   }``
3. Autowire ModelClass class and Person class repositories in the AdminController class
````java
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
         person.setModelClass(null); //making the class fields in the person table to null before deleting the class
         personRepository.save(person);
      }
      modelClassRepository.deleteById(id);

      ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
      return modelAndView;


   }

   @GetMapping("/displayStudents")
   public ModelAndView displayStudents(Model model, @RequestParam int classId) {
      ModelAndView modelAndView = new ModelAndView("students.html");
      return modelAndView;
   }
````

## Adding code for View Classes in the backend 
There should be the list of all the students in that perticular class. 
we have modelAndView object in the above code which is "``students.html``", make a template for this link. 



