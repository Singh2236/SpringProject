# Introduction to Spring Security

So far :

* Bean,
* Autowiring,
* Dependency Injection,
* AOP,
* Spring MVC Pattern.

Now:

* Spring Security

Our Web Application is so far Publicly Accessible.

Requirement :

1. Build a dynamic Web- Application for a School.
2. Integrate Login Functionality.
3. User should be able to log in their Dashboard.
4. Roles Support: Student, Teacher, Admin, etc.
5. Based on the roles, the content should be displayed in the dashboard.
6. Public/Unauthorised users can be able to use static pages of the website.

In order to secure the Application inside Spring Framework, they have created a Project called "Spring Security".
https://spring.io/projects/spring-security

By writing just very less code, we can control, which user can access which data or classes or URLs.

* Authorisation and Authentication Mechanism
* Protect from CSRF(Cross-Site Request Forgery).

# Spring Security

1. Spring Security is highly customizable authentication and access-control framework. It is the de-facto standard for
   security Spring-based applications.
2. Below is the maven dependency that we can add to implement security using Spring Security project in any of the
   SpringBoot Projects.

````
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-security</artifactId>
   <version>2.7.3</version>
</dependency>
````

4. Spring Security is a framework that provides authentication, authorization, and protection against common attacks.
5. Spring Security helps developers with easier configurations to secure a web application by using standard
   username/password authentication mechanism.
6. Spring Security provides out of the box features to handle common security attacks like CSRF, CORs. It also has good
   integrations with security standards like JWT, OAUTH2 etc.

# Demo

1. Add dependency
2. Build and Run the application
3. Open Browser and go to localhost:8080, asking for username and password
4. Username : user Password : (see logs of application Startup)

## Setting your own Username and Passwords

1. Application.properties - (not the production standard, not recommend)
   ````
   spring.security.user.name= navi
   spring.security.user.password= 12345
   ````
   By default, the pages are secured inside our web application, and furthermore we are going to make it more specific.

## Understanding the default security settings

https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
since websecurityconfigureradapter is deprecated.

1. class ``webConfigureSecuriyAdapter``
   <kbd> ![img.png](img.png) </kbd>

2. here we are securing any type of url requests. ``(.anyrequest --> authenticate)``
3. ``.authenticated()`` method tells the spring mvc to authenticate the particular request.
4. ``.anyRequest()`` since we are using anyRequest(), spring mvc will perform authentication on all the requests by
   default.
5. ``http.formLogin()`` provides support for username and password being provided through a html form.
6. ``http.httpBasic()`` Auth using HTTP header in order to provide the username and password when making the request to
   the server.
7. In order to override the current default configurations we need to import this class and override this method.

## Overriding the default behavior of the Application.

### Making all the pages Public

1. Using ``permitAll()`` configurations, we can allow full/public access to a specific resource/path or all the
   resources/paths inside a web application.
2. Create a new Class extend ``WebSecurityConfigurerAdapter`` and override the configure method.

````java

@Controller
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        //Permit all the requests inside the web-application

        http.authorizeRequests()
                .anyRequest().permitAll()
                .and().formLogin()
                .and().httpBasic();
    }
````

### Denying all the requests.

````java
protected void configure(HttpSecurity http)throws Exception{
        //Permit all the requests inside the web-application

        http.authorizeRequests()
        .anyRequest().denyAll()
        .and().formLogin()
        .and().httpBasic();
        }
        }
````

note: In the case of denyAll(), Spring will ask for credentials and even after putting right credentials, it will still
deny to show the page. Because, a user is authenticated but not authorised to view this particular page or in the above
case we are not authorised to see any page on our web application since we have denied all the requests. 


## Updates

From Spring Security 5.7, the `WebSecurityConfigurerAdapter` is deprecated to encourage users to move towards a
component-based security configuration. It is recommended to create a bean of type `SecurityFilterChain` for security
related configurations.

````java

@Configuration
public class ProjectSecurityConfig  /*extends WebSecurityConfigurerAdapter*/ {

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {

        // Permit All Requests inside the Web Application
        http.authorizeRequests().
                    anyRequest().permitAll().
                    and().formLogin()
                    .and().httpBasic();

        // Deny All Requests inside the Web Application
        *//*http.authorizeRequests().
                anyRequest().denyAll().
                and().formLogin()
                .and().httpBasic();*//*
    }*/

    /**
     * From Spring Security 5.7, the WebSecurityConfigurerAdapter is deprecated to encourage users
     * to move towards a component-based security configuration. It is recommended to create a bean
     * of type SecurityFilterChain for security related configurations.
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        // Permit All Requests inside the Web Application
        http.authorizeRequests().anyRequest().permitAll().
                and().formLogin()
                .and().httpBasic();

        // Deny All Requests inside the Web Application
            /*http.authorizeRequests().anyRequest().denyAll().
                    and().formLogin()
                    .and().httpBasic();*/

        return http.build();

    }
````







