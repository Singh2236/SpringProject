# Different ways to name a bean.

### @Bean(<span style="color:red">name</span>="BeanName/Value") <br>
### @Bean(<span style="color:red">value</span>="BeanName/Value") <br>
### @Bean("BeanName/Value") <br>

<br>

### Code 
- Pojo
```java
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

```
- ProjectConfig 
````java
package org.navi.config;

import org.navi.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    @Bean("kia")
    Vehicle vehicle3() {
        Vehicle veh = new Vehicle();
        veh.setName("Kia");
        return veh;
    }

}

````

- Example3: Main
````java
package org.navi.main;

import org.navi.beans.Vehicle;
import org.navi.config.ProjConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example3 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);

        Vehicle veh1 = context.getBean("Audi", Vehicle.class);
        System.out.println(veh1.getName());

        Vehicle veh2 = context.getBean("Maruti", Vehicle.class);
        System.out.println(veh2.getName());

        Vehicle veh3 = context.getBean("kia", Vehicle.class);
        System.out.println(veh3.getName());

    }
}

````

- Output
````
Audi
Maruti
Kia
````