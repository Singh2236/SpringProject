# @PostConstruct 
This annotation calls the method after the Bean through @Component,
is instantiated. Here in our case we have initialized the "name" attribute.

# @PreDestroy 
This annotation evokes the method right before destroying the bean. 

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