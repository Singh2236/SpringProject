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

## Creating the tables for classes