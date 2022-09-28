# Thymeleaf

1. Add dependency to the pom.xml file
2. Move `home.html` from Static to `templates` package, because thymeleaf is no the static file. Templates generate a
   value and send plane HTML to the browser.
3. Import thr name space of thymeleaf to the html file, and then we can use thymeleaf tags inside HTML page.All the
   thymeleaf tags start with `th:`tags.

````
<html lang="en" xmlns:th="http://www.thymeleaf.org">
````

### Modifying the static code to dynamic code using th

````html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Model School</title>
</head>
<body>
<h1 th:text="'Hello, ' + ${username} + ' !!! Welcome to ModelSchool.'"></h1>

</body>
</html>
````

This `username` is the dynamic value we will be getting from the controller class. After the `th:` tag you have double
quotes and inside the double quotes `""`, you can also pass the single quotes` " '' "`. Inside the single quotes, the
values are String values, outside of single quotes, the values are dynamic. <br>

In order to append the dynamic content, we have to follow the syntax `${ //SOME VARIABLE NAME }` and plus `+` symbols to
concat in the string.

### Model Interface

Whenever we want to send some dynamic values to the thymeleaf/frontEnd we sent it through the `Model` interface. <br>

Model is an interface inside Spring MVC Framework, which act as a container between you UI and Backend code. (Earlier
we used to do it with the help of `ServletRequest` and `ServletResponse`)

````java

@Controller
public class homeController {
    @RequestMapping({"", "/", "/home"})
    public String displayHome(Model model) {
        model.addAttribute("username", "Navi");
        return "home.html";
    }
}
````

# Disabling thymeleaf caching

Normally, Thymeleaf cache the dynamic code inside the html file and save it, and shows the same code in the browser. On
modifications in the code we have to restart the server to get the updated code. But on disabling it we don't have
restarted the server and just with the build of code, we can get the updated values.
To do so, just add the following property to application.properties file.

````
spring.thymeleaf.cache=false
````

# Dev Tools

With dev tools we do not need to refresh the web browser, and all the changes can be updated in the browser as well.
It is really great for developers. These tools can only be leveraged, when we are making an application with spring
boot. Just with Spring and Spring MVC, dev Tools don't work. Just add dependencies and it's fun to go.
Provided features -

1. Automatic Restart
2. Live reload

### How DevTools works

1. DevTools maintain 2 class loaders. One with classes that doesn't change and other with classes that change. When
   restart is need it only reloads the second class loader, which makes the class restart faster as well.
2. DevTools includes an embedded LiveReload server that can be used to trigger a browser refresh when a resource is
   changed. Live Reloader extensions are available for most of the browsers.
3. DevTools trigger a restart, whenever a restart is triggered through IDE or by Maven commands. DevTools disable the
   caching options by default during development. Repacked archives do not contain DevTools by default.

### Adding Thymeleaf links to all the home buttons

````thymeleafexpressions
th:href="@{/home}"
````

### Upgrading Controller class logic in Configurations file

Whenever we have a scenario, where we have to manage many kinds of Mappings, we use kind of class below.

```java

@Configuration
public class webConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/courses").setViewName("courses"); //Url pattern and file name, don't need to put .html extension

    }
}
```

# Contact page

is not to be configured using `andViewControllers`, because we have to handle forms in it.

1. New Controller class

````java

@Controller
@Controller
public class ContactController {
    private static Logger log = LoggerFactory.getLogger(ContactController.class);


    @PostMapping(value = "/saveMsg")
    //@RequestMapping(value = "/saveMsg", method = POST) //the variable names should be same as the name/id in the HTML code 
    public ModelAndView displayContactPage(@RequestParam String name, @RequestParam(value = "mobileNum") String mob,
                                           @RequestParam String email, @RequestParam String subject,
                                           @RequestParam String message) {
        log.info("Name " + name);
        log.info("Mobile Number  " + mob);
        log.info("Email:  " + email);
        log.info("Subject: " + subject);
        log.info("Msg:  " + message);

        return new ModelAndView(("redirect:/contact"));

    }
}
````

The problem here is that we are accepting too many parameters, if there are more parameters than the method will grow
and making things complex and hard to handle, extra code, no neat code, we could have done that with simple pojo class.
We will do that next.

### ModelAndView class

helps in sending the data from backend to UI.

### Taking variable values from UI through Model class i.e. ``Contact class``

The variable names in the Contact class should be same as variables(name/id) which we are getting from the UI/HTML.

## Layers

Build you project in such a way that:
Controller : You will do all your validations, business logic.
Service layer : Once the validations are done, you will hand over that data to the service layer. Service layer will do
its own business logic i.e. something related to service layer,
Persistence layer : once the serve layer done processing the information it will send that information to the
persistence layer/Data Layer. <br>

UI > Controller > Service > Persistence/Data
Annotations : ```@Controller``` , ``@Service`` , ``@Repository``

1. Create Service package
2. Create ```ContactService``` class.
3. Use Annotation ```@Service``` on the top of the class name.

````java

@Service
public class ContactService {
    public static Logger log = LoggerFactory.getLogger(ContactService.class);

    public boolean printContactData(Contact contact) {
        boolean isSaved = true;
        //TODO: Need to Persist the Data inti the DB table.
        log.info(contact.toString());
        return isSaved;
    }

}
````

<br>

Now, our contactService class is ready, we need to inject this into the controller layer, with the help of AutoWiring
concept.

````java

@Controller
public class ContactController {
    private static Logger log = LoggerFactory.getLogger(ContactController.class);

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping(value = "/saveMsg")
    public ModelAndView displayContactPage(Contact contact) {
        return new ModelAndView(("redirect:/contact"));
    }

}
````

So, Now i my ContactService bean injected in ContactController class.
So we can also accept the information from Front-end using the POJO object. Spring MVC will do all the magic behind the
scenes, from converting all the infroamtio that the user is entering, bit from our side we just have to see that the
names are matching. Spring MVC can't understand how to map the values from fround-end to the back-end.

### Sending Holiday data to the front end

1. Create POJO holidays
2. Create HolidaysController

````java

@Controller
public class HolidaysController {
    @RequestMapping("/holidays")
    public String displayHolidays(Model model) {
        List<Holiday> holidays = Arrays.asList(
                new Holiday(" Jan 1 ", "New Year's Day", Holiday.Type.FESTIVAL),
                new Holiday(" Oct 31 ", "Halloween", Holiday.Type.FESTIVAL),
                new Holiday(" Nov 24 ", "Thanksgiving Day", Holiday.Type.FESTIVAL),
                new Holiday(" Dec 25 ", "Christmas", Holiday.Type.FESTIVAL),
                new Holiday(" Jan 17 ", "Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
                new Holiday(" July 4 ", "Independence Day", Holiday.Type.FEDERAL),
                new Holiday(" Sep 5 ", "Labor Day", Holiday.Type.FEDERAL),
                new Holiday(" Nov 11 ", "Veterans Day", Holiday.Type.FEDERAL)
        );
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(), //looking for one type and sending the data i.e. FESTIVAL or FEDERAL
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
        return "holidays.html";
    }
}

````

<p> 
model.addAttributes will take these filtered values and send them to the front end refering to return -->
holidays.html this is done with the help of model attribute(since we are not sending any ServletResponse) Thymeleaf will
look inside this model object and get the values from this to holidays.html; As if there are any references, thymeleaf
will take care of that. </p>

3. Now we have Model class and the controller, we need defining a new HTML file.
4. As we see in the html files and our controller class, we habe two types of holidays, how to show that two differnt
   kind of data.

````html

<div th:each="holiday : ${FEDERAL}" class="column">
    <div th:each="holiday : ${FESTIVAL}" class="column">
````

from the html code snippets we can see that, there are two variables out thymeleaf is looking for, definitely there is
an
attribute name called ``FESTIVAL`` in the controller class. This is the Model attribute name, which thymeleaf have to
pull from the MODEL object. Since, it is the list of holidays, we are trying to iterate them with the help of Thymeleaf
``th:each`` tag. So, whenever it is trying to iterate, we are naming that object ``holiday`` like we have iterator in
java.

5. During this iteration, inside this each loop of block, we want to display, what is the reason, that we can fetch
   by ``holiday.reason`` and ``holiday.day``, that we have also defined in the Holiday POJO class.So, these ar all the
   holiday of FEDERAL type, we also have the Holidays of other type in the next code snippet in the holiday.html file.

````html

<div th:each="holiday : ${FEDERAL}" class="column">
    <div class="title">
        <h2 th:text="${holiday.reason}"></h2>
    </div>
    <div class="description">
        <h6 th:text="${holiday.day}" class="fas fa-calendar-alt"><i class="fas fa-calendar-alt"></i></h6>
    </div>
</div>
````


