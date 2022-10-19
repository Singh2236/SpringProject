# Profile & Address Update inside Model School App

1. Front End adding the new div for the link to Profile

````thymeleafexpressions
<div class="col-sm-6 col-lg-3">
    <a th:href="@{/displayProfile}">
        <div class="overview-item overview-item--c4">
            <div class="overview__inner">
                <div class="overview-box clearfix">
                    <div class="icon">
                        <i class="fas fa-id-card"></i>
                    </div>
                    <div class="text">
                        <h2>Profile</h2>
                    </div>
                </div>
            </div>
        </div>
    </a>
</div>
````

2. Create new Class ``ProfileController`` with Mapping and with new ModelAndView Object which shows profile.html.

````java

@Slf4j
@Controller
public class ProfileController {
    @RequestMapping("/displayProfile")
    public ModelAndView displayMessages(Model model) {
        ModelAndView modelAndView = new ModelAndView("profile.html");
        return modelAndView;
    }
}
````

3. Building the Profile html web page, which has an object ``profile`` for which we also create a POJO class in the
   backend. This html page also includes-
    1. action to /updateProfile
    2. profile object
    3. Form fields including Address
    4. Update Button and Back Button
       file ``profile.html``

````thymeleafexpressions
<!-- profile block -->
<section class="w3l-contact py-5" id="contact">
  <div class="container py-md-5 py-4">
    <div class="title-main text-center mx-auto mb-md-5 mb-4" style="max-width:500px;">
      <h3 class="title-style">My Profile</h3>
    </div>
    <div class="row login-block">
      <div class="col-md-7 login-center">
        <ul>
          <li class="alert alert-danger" role="alert" th:each="error : ${#fields.errors('profile.*')}" th:text="${error}" />
        </ul>
        <form th:action="@{/updateProfile}" method="post" class="signin-form" th:object="${profile}">
          <div class="title-main text-center mx-auto mb-md-5 mb-4" style="max-width:500px;">
            <h5 class="footer-title-29">Personal Details</h5>
          </div>
          <div class="input-grids">
            <label class="control-label" for="name">Name:</label>
            <input type="text" th:field="*{name}" id="name" class="contact-input" />
            <div class="row">
              <div class="col-sm-6">
                <label class="control-label" for="mobileNumber">Mobile Number:</label>
                <input type="text" th:field="*{mobileNumber}" id="mobileNumber"
                       class="contact-input" />
              </div>
              <div class="col-sm-6">
                <label class="control-label" for="email">Email:</label>
                <input type="text" th:field="*{email}" id="email"
                       class="contact-input" />
              </div>
            </div>
          </div>
          <div class="title-main text-center mx-auto mb-md-5 mb-4" style="max-width:500px;">
            <h5 class="footer-title-29">Address Details</h5>
          </div>
          <div class="input-grids">
            <label class="control-label" for="address1">Address Line 1:</label>
            <input type="text" th:field="*{address1}" id="address1" class="contact-input" />
            <label for="address2">Address Line 2:</label>
            <input type="text" th:field="*{address2}" id="address2" class="contact-input" />
            <label class="control-label" for="city">City:</label>
            <input type="text" th:field="*{city}" id="city" class="contact-input" />
            <div class="row">
              <div class="col-sm-6">
                <label class="control-label" for="state">State:</label>
                <input type="text" th:field="*{state}" id="state"
                       class="contact-input" />
              </div>
              <div class="col-sm-6">
                <label class="control-label" for="zipCode">Zip Code:</label>
                <input type="text" th:field="*{zipCode}" id="zipCode"
                       class="contact-input" />
              </div>
            </div>
          </div>
          <div class="col-md-8 login-center text-start">
            <button class="btn btn-style btn-style-3 text-left" >Update</button>
            <a th:href="@{/dashboard}" class="new-user text-right">
              <button class="btn btn-style btn-style-3 text-left" th:formaction="@{/dashboard}">BACK</button></a>
          </div>

        </form>
      </div>
    </div>
  </div>
</section>
<!-- //profile block -->
````

4. Creating the profile POJO Class. Specifications-
    1. This POJO class is only to be used for getting data from the Front End
    2. We don't mention @Entity annotation on the top of this class, since we don't have any table associated with class
       in the DB.
    3. We already have all the same fields in Person and Contact class and still we are making another POJO class,
       because it will be a bit cumbersome process to use them for this purpose, so we created a new one as shown below.
    4. Also, if we want to populate Person data or Address we can use the entities we already have.

````java

@Data
public class Profile {
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile Number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be at 10 digits long")
    private String mobileNumber;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid Email Address")
    private String email;

    @NotBlank(message = "Address1 must not be blank")
    @Size(min = 5, message = "Address must be at least 5 characters long")
    private String address1;

    private String address2;

    @NotBlank(message = "city must not be blank")
    @Size(min = 3, message = "city must be at least 3 characters long")
    private String city;

    @NotBlank(message = "State must not be blank")
    @Size(min = 3, message = "State must be at least 3 characters long")
    private String state;

    @NotBlank(message = "Zip Code must not be blank")
    @Pattern(regexp = "(^$|[0-9]{5})", message = "Zip code must be at least 5 digits long")
    private String zipCode;
}
````

5. From our Profile Controller, whenever we are trying to display this ``profile.html`` very first time, we should make
   sure we are sending the ``profile`` object form the backend, that's how, our html template can try to display the
   data from my profile object that we are sending from our backend controller. Let's add that code and **modify our
   step
   2.** Specifications-
    1. Create Profile object to send it to the UI by sending it through our model and view Object by adding attributes
       to the ModelAndView Controller.

````java

@Slf4j
@Controller
public class ProfileController {
    @RequestMapping("/displayProfile")
    public ModelAndView displayMessages(Model model) {
        Profile profile = new Profile();
        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }
}
````

6. Displaying the data in the profile page form, we are saving the data to the session, which we can show anytime the
   user wants to see. In this way, we don't have to query the database for the same information again and again. We can
   simply fetch from the session, which we will store in the dashboard controller. 
    1. Dashboard controller class modifications-





