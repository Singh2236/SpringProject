# Working with multiple beans at the same time.

- By default, Spring tries autowiring with class type. But this approach will fail if the same class type has multiple
  beans.
- If Spring context has multiple beans of same class type like below, then spring will try to autowire based on the
  parameter name/fields name that we use while configuring autowiring annotation.
- if the parameter name/field name that we use while configuring autowiring annotation is not
  matching with any of these bean names, them Spring will look for the bean which has @Primary configured.
-

**People class**

````java
package org.navi.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component  //personBean1 of Person with dependency on VehicleBean1
public class Person {
    String name = "Lucy";

    /*@Autowired*/
    private Vehicle vehicle;

    @Autowired // Autowired at constructor
    public Person(Vehicle vehicle1) {
        System.out.println("Person created by Spring");
        this.vehicle = vehicle1;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    /*@Autowired*/
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}

````

**Vehicle class**

````java
package org.navi.beans;

import org.springframework.stereotype.Component;

@Component //vehicleBean1
public class Vehicle {
    private String name = "Audi";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String printHello() {
        return "@Component: Vehicle is running";
    }

}

````

**ProjectConfig class**

````java

@Configuration
@ComponentScan(basePackages = "org.navi.beans")
public class ProjectConfig {

    @Bean //vehicleBean2
    @Primary
    public Vehicle vehicle2() {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Ford");
        return vehicle;
    }

    @Bean // personBean2
    @Primary
    public Person person2() {
        Person person = new Person(vehicle2());
        person.setName("Navi");
        return person;
    }
}
````

<br>

**Beans**
Vehicle Beans: Toyota , Ford
Person Beans: Lucy, Navi

<br>

**Example12/ main class**

````java
package org.navi.main;

import org.navi.beans.Person;
import org.navi.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example12 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Person person = context.getBean(Person.class);
        System.out.println("Person's name: " + person.getName());
        System.out.println("Person's Car: " + person.getVehicle().getName());
    }

}

````

````
Person created by Spring
Person created by Spring
Person's name: Navi
Person's Car: Ford

````

In the above code, Spring context is looking for Beans --> found 2 from each--> thrown an exception
*"Exception in thread "main" org.springframework.beans.factory.<span style="color:red">
NoUniqueBeanDefinitionException</span>:
No qualifying bean of type 'org.navi.beans.Person' available: expected single matching bean but found 2:
person,person2"*

<br>

Added @Primary annotation on vehicle2 and person2, and we got the output above.

# <span style="color:red">@Qualifier </span>

- If the parameter name/field name that we use while configuring autowiring annotation is not matching with any
  of bean names and even Primary bean is not configured, then Spring will look if @Qualifier annotation is used
  with the bean name matching with Spring Context bean names.
- In the below scenario, we used 'vehicle2' with @Qualifier annotation. Spring will try to auto-wire the bean
  which has same name like shown in the code below.

**Spring Context --> same bean name --> @Primary annotation --> @Qualifier**


ALl the code is same, we just have added @Qualifier annotation for Person Bean. ANd we have removed the @Primary
annotation from the Vehicle Bean. 

````java
@Component  //personBean1 of Person with dependency on VehicleBean1
public class Person {
    String name = "Lucy";

    /*@Autowired*/
    private Vehicle vehicle;

    @Autowired // Autowired at constructor
    public Person(@Qualifier("vehicle2") Vehicle vehicle1) {
        System.out.println("Person created by Spring");
        this.vehicle = vehicle1;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    /*@Autowired*/
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}

````

Some additional features like You can assign the bean to the main object with the reference of the bean name.

````java
@Component("person")
````
````java
Person person = context.getBean("person",Person.class);
````


