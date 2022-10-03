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
