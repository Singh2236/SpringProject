# New Registration, Custom Validations,OnetoOne Mapping Demo

## Adding the link to front end login Page

````thymeleafexpressions
<a th:href="@{/public/register}" class="new-user text-right" href="">New User ?</a>
````

/public -> All the public accessed paths are to be configured in this dir. This is the easy was to Configure Spring
Security.

## Controller for this path

````java

@Slf4j
@Controller
@RequestMapping("public")
public class PublicController {

    PersonService personService;

    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public String displayRegisterPage(Model model) {
        model.addAttribute("person", new Person);
        return "register.html";
    }
}
````

## Spring Security Configurations for `register.html`

``.mvcMatchers("/register").permitAll()``, this is not the correct path
``.mvcMatchers("/public/register").permitAll()``, Not Dynamic, and absurd to use /public, if we don't
use that in use.  
``.mvcMatchers("/public/**").permitAll()``, Dynamic, and reusable, reduce configurations, for all the public links.

## More Security

Since Spring Boot Security blocks all the POST/PUT requests, to avoid CSRF attacks, but our registration form is using
POSt method

``\<form th:action="@{/public/createUser}" method="post" class="signin-form" th:object="${person}">``

and since registration is a public operation, no data to protect, no worries about csrf, --> disable CSRF for this path.

`` http.csrf().ignoringAntMatchers("/public/**").and()......``

# Validations

1. Create Fields with Annotations on the top of them.
2. In Spring MVC Controller classes --> method where Form is submitted by the action path --> Params
    1. ``@Valid`` annotation,
    2. Accept the POJO object @ModelAttribute("name of the object") Type newName,
       dh ``@ModelAttribute("person") Person``
       person.
    3. Accept the Error object dh ``Error error``. if we find errors, throw to console log.

````
public String registerMethod(annotation1, 2, 3){
        if error has errors{ 
        error.toString();
        return with errors to the same page.
        }
businesslogic.savemsg(PersonObject);
return redirect to fresh same registration paath. 
}
````

# Custom Validation annotations

Aim :Build three custom validations

1. Make sure --> user is writing same email address --> email and confirm-email fields
2. Make sure --> point 1 is valid for passwords.
3. Restrict the user to choose weak passwords.

There are no annotations available in both of our Validations Libraries dh ``java.validations.constraints.*`` and
``org.hibernate.validator.constraints.*``. We build our own @Annotations or ValidationCustomLogic.

### How to make a custom annotation.

Following are the steps. We are talking the help from the spring framework-

1. Creation of Annotation (like for not accepting the weak password) and provide the class name where the actual
   validation logic present.
    1. Parts of Annotations
        1. ``@Documented``
        2. ``@Constraint(validatedBy = {})`` --> Mention the location/package/class details of the annotation.
        3. ``@Target`` --> Targets are the fields, on the top of what, the annotations can be used.
        4. ``@Retention(RUNTIME)`` --> Creation during the runtime or during the compilation.
        5. ``@Repeatable(List.class)`` --> Support mention multiple time on the top of a field.
        6. ``public @interface`` AnnotationName --> makes an interface, an annotation.
        7. ``message()`` --> message invoked during the error. ``defalut`` is default. 
        8. ``groups()`` --> purpose of grouping the validations.
        9. ``payload()`` --> to put some logic in case of annotation validation fails, but rarely used by developers.
        10. ``regex()`` and ``flag()`` --> to define your own regex pattern. 

2. Creation of the class that implements ``ConstraintValidator<annotationName,fieldDataTypeForUsageOfTheAnn> ``
   interface and overriding the ``isValid()`` method.

3. Mention the annotation that is created on the top of the field inside a POJO class. 





