# Injecting Beans using @Autowired on <span style="color:red">class fields</span>

- Autowired annotation marks on a <span style="color:red"> field, setter method, constructor </span>
  is used to auto-wire the beans that is 'injecting beans'(objects) at runtime by <span style="color:red>Spring
  Dependency Injection</span> mechanism.
- With below code, Spring injects/auto-wire the vehicle bean to the person through a class field and dependency
  injection.
- The below style is not recommended for production usage as we can't remark the fields as final.

### Reqirements

- @Component : classes --> Beans
- @@Configuration : ProjectConfig File
- @ComponentScan --> Base Package : ProjectConfig File  
  Beans
  <br>
  **Vehicle**

````java

@Component
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

````

**Person**
<br>
**@Autowired(require=false) will help to avoid NoSuchBeanDefinitionExecution if the bean is not awailable
during Autowiring process.**
````java

@Component
public class Person {
    String name = "Lucy";

    @Autowired
    private Vehicle vehicle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

````

ProjectConfig File

````java

@Configuration
@ComponentScan(basePackages = "org.navi.beans")
public class ProjectConfig {


}

````

Example11 / Main

````java
package org.navi.main;

import org.navi.beans.Person;
import org.navi.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example11 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Person person = context.getBean(Person.class);
        System.out.println("Person's name: " + person.getName());

        System.out.println("Person's Car: " + person.getVehicle().getName());
    }
}

````

Console Output

````
Person's name: Lucy
Person's Car: Audi
````

# Inject Beans using @Autowiring on <span style="color:red">Setter Methods</span>

The mechanism is same for Autowiring, however you can put @Autowiring annotation on the head of setMethod() block.
like:

````java

package org.navi.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
    String name = "Lucy";

    /*@Autowired*/
    private Vehicle vehicle;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    @Autowired
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}

````

# Injecting bean using @Autowired with <span style="color:red">Constructor</span>

````java
package org.navi.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
    String name = "Lucy";

    /*@Autowired*/
    private Vehicle vehicle;

    @Autowired
    public Person(Vehicle vehicle) {
        System.out.println("Person created by Spring");
        this.vehicle = vehicle;
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
Console output
````
Person created by Spring
Person's name: Lucy
Person's Car: Audi
````
