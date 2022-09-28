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
    //@RequestMapping(value = "/saveMsg", method = POST)
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
