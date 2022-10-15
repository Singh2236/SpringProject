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
        1. ``@Documented``  //optional
        2. ``@Constraint(validatedBy = {})`` --> Mention the location/package/class details of the annotation.
        3. ``@Target`` --> Targets are the fields, on the top of what, the annotations can be used.
        4. ``@Retention(RUNTIME)`` --> Creation during the runtime or during the compilation. RUNTIME, ClASS, SCORE
        5. ``@Repeatable(List.class)`` --> Support mention multiple time on the top of a field.
        6. ``public @interface`` AnnotationName --> makes an interface, an annotation.
        7. ``message()`` --> for dev to write his own msg, while implementing the anno. ``defalut`` is default. 
        8. ``groups()`` --> purpose of grouping the validations.
        9. ``payload()`` --> to put some logic in case of annotation validation fails, but rarely used by developers.
        10. ``regex()`` and ``flag()`` --> to define your own regex pattern.
        11. Also handle repeatable nature, research for more details. 

````java
@Constraint(validatedBy = FieldsValueMatchValidator.class) //class --> implements this interface and here logic resides.
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "Fields values don't match!";

    String field();  // First of two fields to be matched.

    String fieldMatch(); // 2nd of two fields to be matched.

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }
````

````java
@Documented //Optional and is this annotation is documented etc.
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "Please Choose Strong Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
````

2. Creation of the class that implements ``ConstraintValidator<annotationName,fieldDataTypeForUsageOfTheAnn> ``
   interface and overriding the ``isValid()`` method.
````java
public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }


    @Override
    public boolean isValid(Object value,ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);
        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}
````

````java
public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {

    List<String> weakPasswords;


    @Override
    public void initialize(PasswordValidator passwordValidator) { // data/business logic needed to validations
        weakPasswords = Arrays.asList("12345", "password", "qwerty"); //list to check weak passwords
    }

    @Override //if isValid fails, requirement of this particular business logic is not fulfilled.
    public boolean isValid(String passwordField, ConstraintValidatorContext context) { //1.para = value entered by user, 2.param context from initialize method
        return passwordField != null && (!weakPasswords.contains(passwordField));
    }
}
````


3. Mention the annotation that is created on the top of the field inside a POJO class dh in our case Person class.

````java
@Data
@Entity //for database usage 
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Passwords do not match!"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Email Addresses do not match!"
        )

})
public class Person extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int personId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Email must not be blank.")
    @Email(message = "Please provide a valid Email Address")
    private String email;

    @NotBlank(message = "Confirm Email must not be blank")
    @Email(message = "Please provide a valid confirm email address" )
    @Transient
    private String confirmEmail;

    @NotBlank(message="Password must not be blank")
    @Size(min=5, message="Password must be at least 5 characters long")
    @PasswordValidator
    private String pwd;

    @NotBlank(message="Confirm Password must not be blank")
    @Size(min=5, message="Confirm Password must be at least 5 characters long")
    @Transient
    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId",nullable = false)
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId",nullable = true)
    private Address address;
}
````

## Creating Controller method inside ``PublicController`` for ``register.html`` form action path
````java
 @RequestMapping(value = "/createUser", method = {RequestMethod.POST})
    public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors) {
        if (errors.hasErrors()) {
            return "register.html";
        }

        return "redirect:/login?register=true";

    }
````

## Updating the information in Login Controller for registration successful.

````java
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "logout", required = false) String logout,
                                   @RequestParam(value = "register", required = false) String register,//updated
                                   Model model) {
        String errorMessge = null;
        if (error != null) {
            errorMessge = "Username or Password is incorrect !!";
        } else if (logout != null) {
            errorMessge = "You have been successfully logged out !!";
        } else if (register != null) { //updated
            errorMessge= "Your registration is successful. Login with registered credentials";
        }
            model.addAttribute("errorMessage", errorMessge);
        return "login.html";
    }

````

# Deep Dive into one-to-one relationships 








