# Introduction to bean wiring inside the spring

source: https://data-flair.training/blogs/spring-beans-autowiring/

- Inside java web applications,usually the object delegate certain responsibilities to the other objects.
  So, in this scenario, objects will have dependency on others.
- In a very similar lines, when we create various beans using Spring, it is our responsibility to understand
  the dependencies that bean have and wire them. The concept inside called
  <span style="color:red"> Wiring/Autowiring</span>

### No wiring scenario inside spring

<p>Consider a scenario where we have two java classes Person and Vehicle. The Person class has dependency on
the Vehicle. Based on the belo code, we are only creating the bean inside the SPringcontext
and no wiring will be done. Due to this both beans present inside the Spring Context
with out knowing abot each other. 
</p>

````java
public class Vehicle {
    private String name;
}
````

````java
public class Person {
    private String name;
    private Vehicle vehicle;
}
````

````java
import org.springframework.context.annotation.Bean;

@Bean
public Vehicle vehicle(){
        Vehicle vehicle=new Vehicle();
        vehicle.setName("Toyota");
        return vehicle;
        }
@Bean
public Person person(){
        Person person=nre Person();
        person.setName("Lucy");
        return person;
        }
````

![img_2.png](img_2.png)

### Wiring Beans using <span style="color:red">Method Calls</span>

- Here in the belo code, we are trying to wire or establish a relationship between a person and Vehicle, by invoking the
  vehicle() bean method from peron() bean method. Now inside Spring Context, person owns the vehicle.
- Spring will make sure to have only 1 vehicle bean is created and also vehicle bean will be created first always as
  person bean has dependency on it.

````java
import org.springframework.context.annotation.Bean;

@Bean
public Vehicle vehicle(){
        Vehicle vehicle=new Vehicle();
        vehicle.setName("Toyota");
        return vehicle;
        }
@Bean
public Person person(){
        Person person=nre Person();
        person.setName("Lucy");
        person.setVehicle(Vehicle());
        return person;
        }
````
![img_3.png](img_3.png)
<!-- Remve this image with code -->
![img_4.png](img_4.png)
<!-- Remve this image with code -->
![img_5.png](img_5.png)

### There are also other modes of wiring in the Spring 

![img_1.png](img_1.png)

![img.png](img.png)