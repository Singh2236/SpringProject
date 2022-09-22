package org.navi.config;

import org.navi.beans.Person;
import org.navi.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/*
Spring @Configuration annotation is part of the spring core framework.
Spring Configuration annotation indicates that the class has @Bean definition
methods. So Spring container can process the class and generate Spring Beans
to be used in the application.

To tell Spring it needs to search for classes annotated
with stereotype annotations, we use the @ComponentScan annotation over the
configuration class.
* */
@Configuration
@ComponentScan(basePackages = {"org.navi.implementations", "org.navi.service"})
@ComponentScan(basePackageClasses = {org.navi.beans.Vehicle.class, org.navi.beans.Person.class})
public class ProjectConfig {

}
