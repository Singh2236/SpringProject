# Primary annotation 
Having multiple beans of the same name/value, you can make a bean as a primary bean
which will be executed if the named bena is not ab

<p>we use @Primary to give higher preference to a bean when there are multiple beans of the same type.
we need to register more than one bean of the same type.
we need to register more than one bean of the same type.
Spring throws NoUniqueBeanDefinitionException if we try to run the application.</p>


Let's have a look at the code now:

- POJO
````java
package org.navi.beans;

public class Vehicle {
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
````
- ProjConfig
````java
package org.navi.config;

import org.navi.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProjConfig {

    @Bean(name = "Audi")
    Vehicle vehicle1() {
        Vehicle veh = new Vehicle();
        veh.setName("Audi");
        return veh;
    }
    @Bean(value = "Maruti")
    Vehicle vehicle2() {
        Vehicle veh = new Vehicle();
        veh.setName("Maruti");
        return veh;
    }
    @Primary
    @Bean("kia")
    Vehicle vehicle3() {
        Vehicle veh = new Vehicle();
        veh.setName("Kia");
        return veh;
    }

}

````
- main
````java
package org.navi.main;

import org.navi.beans.Vehicle;
import org.navi.config.ProjConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example4 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);
        Vehicle veh1 = context.getBean(Vehicle.class);
        System.out.println(veh1.getName());
    }
}

````

* Without **@Primary** Annotation, i would have got **"NoUniqueBeanDefinitionException"**