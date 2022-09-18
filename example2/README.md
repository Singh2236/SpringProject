In this example we are going to create multiple beans.<br>
The code will look like the following if we add multiple beans. 
````java
package org.navi.config;

import org.navi.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjConfig {

    @Bean  //Means that whenever we create an object of Vehicle class through spring context we will get this bean and
            // the name of the vehicle is going to be Audi.
    Vehicle vehicle1() {
        Vehicle veh = new Vehicle();
        veh.setName("Audi");
        return veh;
    }
    @Bean
    Vehicle vehicle2() {
        Vehicle veh = new Vehicle();
        veh.setName("Maruti");
        return veh;
    }
    @Bean
    Vehicle vehicle3() {
        Vehicle veh = new Vehicle();
        veh.setName("Kia");
        return veh;
    }

}
````

Following is the code we have in the <span style="color:red">Example2</span>

````java
package org.navi.main;

import org.navi.beans.Vehicle;
import org.navi.config.ProjConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example2 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);

        Vehicle vehicle = context.getBean(Vehicle.class);
        System.out.println(vehicle.getName());
    }
}

````

<br>
and we are getting error <span style="color:red">NoUniqueBeanDefinitionException</span> saying we have multiple beans available for <span style="color:red">Vehicle</span> class.

```
Exception in thread "main" org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'org.navi.beans.Vehicle' available: expected single matching bean but found 3: vehicle1,vehicle2,vehicle3
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveNamedBean(DefaultListableBeanFactory.java:1273)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveBean(DefaultListableBeanFactory.java:494)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:349)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:342)
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1172)
	at org.navi.main.Example2.main(Example2.java:11)

Process finished with exit code 1
```

### Solutions -

- Put the name of the object, whcih we have used while creating the bean, and update code like this

````java
Vehicle veh=context.getBean("vehicle1",Vehicle.class);
````

This will solve the problem, and you will get the answer as following
<br>
Output:
````
Audi
````