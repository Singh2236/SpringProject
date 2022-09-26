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
    @RequestMapping({"","/","/home"})
    public String displayHome(Model model){
        model.addAttribute("username", "Navi");
        return "home.html";
    }
}
````
