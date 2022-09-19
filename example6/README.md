# @PostConstruct

- We have seen that when we are using stereotype annptations, we don't have control
  while creating a bean. But what if want to execure some instructions post Spring creates the bean.
  For the same, we can use @POstConstruct annotation.
- We can define a method in the component class and annotation that method with @POstContruct, which
  instructs Spring to execute that method after it finishes creating them.
- Spring borrows the @POStConstruct form JavaEE.

# @PreDestroy

- @PreDestroy annotation can be used on the top of the methods and spring will make sure to call this method just
  before clearing and destroying the context.
- This can be used in the scenarios where we want to close any IO resources, Database
  connection etc.
- Spring borrows the @PreDestroy annotation from Java EE.

Code:

- Pojo/Bean/Component

````java
package org.navi.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Vehicle {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String printHello() {
        return "@Component: Vehicle is running";
    }

    @PostConstruct
    public void initialize() {
        this.name = "Mustang";
    }

    @PreDestroy
    public void destroy() {
        System.out.println("@Component: Vehicle is destroying.");
    }

}

````

- ProjectConfig

````java
package org.navi.config;

import org.navi.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "org.navi.beans")
public class ProjConfig {

}

````

- Example 6 /main

````java
package org.navi.main;

import org.navi.beans.Vehicle;
import org.navi.config.ProjConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example6 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);

        Vehicle vehicle = context.getBean(Vehicle.class);
        System.out.println(vehicle.getName()); // Name of a field is initialized using  @PostConstruct Annotation
        vehicle.destroy(); //before destroying the bean, method using @PreDestroy Annotation
    }
}

````