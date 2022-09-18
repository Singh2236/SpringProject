Example1: @Configuration Annotation

## @Configuration Annotation

<p>Spring <span style="color:red">@Configuration</span> annotation is the part of Spring Core framework.
    Spring Configuration annotation indicates that the class has <span style="color:red">@Bean</span> definition methods.
    Spring container can process the class and generate Spring beans to be used in the application</p>
<p>import org.springframework.context.annotation.Configuration;</p>

## @Bean Annotation

<p><span style="color:red">@Bean</span> annotation, which lets Spring know that it needs to call
    this method when it initializes its context and adds the returned
    value to the context.</p>

### Method name notations

<p>The method names usually follow verbs notation. But for methods
    which we will use to create beans, can use <span style="color:blue">nouns</span> as names.
    This will be a good practise as the method names will become
    bean names as well in the context.</p>

# Building this application

#### step1:

- Creating a module of maven type.
- Edit pom.xml file and add the following dependency

```xml

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.22</version>
</dependency>
```

- Create three pakages naming - beans, config and main
- Inside bean package create a Class called <span style="color:orange">'vehicle'</span>. This class will look like the following code.

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

- Inside config package, create a new Class clalled <span style="color:orange">'ProjConfig'</span>. This class 
will look as the following code.
```java
package org.navi.config;

import org.navi.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjConfig {

    @Bean  //Means that whenever we create an object of Vehicle class through spring context we will get this bean and
            // the name of the vehicle is going to be Audi.
    Vehicle vehicle() {
        Vehicle veh = new Vehicle();
        veh.setName("Audi");
        return veh;
    }


    @Bean
    String hello() {
        return "Hello World";
    }

    @Bean
    Integer integer(){
        return 65;
    }
}
```
Here we notice that we have two annotations **@Configuration** and **@Bean**.

- Then we make the main class in the main package(in my case it is <span style="color:orange">Example1</span>).
This is how it looks like. 
```java
package org.navi.main;

import org.navi.beans.Vehicle;
import org.navi.config.ProjConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example1 {
    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle();
        vehicle.setName("Maruti");
        System.out.println("Vehicle name from non-spring context: " + vehicle.getName());


        var context = new AnnotationConfigApplicationContext(ProjConfig.class);

        Vehicle veh = context.getBean(Vehicle.class);
        System.out.println("Vehicle's name from the spring context: " + veh.getName());

        /*
        * We don't need to do any explicit casting while fetching a Bean from the context.
        * Spring is smart enough to look for a Bean of the type you requested in its context.
        * If such a Bean doesn't exist, it will through an error.
        * */

        String stringBean = context.getBean(String.class);
        System.out.println(stringBean);

        Integer integerBean = context.getBean(Integer.class);
        System.out.println(integerBean);
    }
}
```
The **most important part in the class is** -
creating an object of " **<span style="color:red">AnnotationConfigApplicationContext(ProjConfig.class);</span>** " and 
<br>
using this object called " **<span style="color:red">context** " to get beans from the IoC container. 


Here we have a *Vehicle object : **vehicle***, created my a developer and not taken from the spring context.
<br>
Then we have a *Vehicle object: **veh***, taken from the IoC container or Spring context. 

Following is the output of the following code.

```
Vehicle name from non-spring context: Maruti
Vehicle's name from the spring context: Audi
Hello World
65

Process finished with exit code   0
```



