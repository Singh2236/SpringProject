# Adding new beans using XMl Configurations

- In the Initial versions of SPring, the bean and the other configurations
  used to be done using XML. However over the time, Spring team beings annotation
  based configurations to make developers life easy. Today we can see XMl
  configurations only in the older applications built based on initial versions
  of Spring.
- It is good to understand on how to create a bean inside Spring context using XML
  style configuration. SO that, it will be useful if ever there is a scenario where
  you need to work in a project based on initial versions of Spring.

  <br>
- **Following is the XML code snippet** --

````xml

<bean id="vehicle" class="com.navi.beans.Vehicle">
    <property name="name" value="Honda"/>
</bean>
````
Properties are the attributes of the POJO class. Properties are used to initialize the variables. <br>
name = name of the variable 
Value = value which you want assign to it. 

- **Following is the Java code snippet.**

````java
var context=new ClassPathXmlApplicationContext("beans.xml");
        Vehicle vehicle=context.getBean(Vehicle.class);
        system.out.println("Vehicle name form Spring Context is: "+vehicle.getName());

````

- **Output on Console**

````
Vehicle name from Spring Context is: Honda
````