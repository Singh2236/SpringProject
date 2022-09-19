# Programmatically adding beans to the Spring context

- Sometimes, we want to create new instances of an object and add them into the spring context
  based on the programming condition.
- For the same, from Spring 5 version, a new approach is provided to create the beans prgrammatically by invoking
  the <span style="color: red">registerBean() </span>
````java
context.registerBean("kia", Vehicle.class, kiaSupplier);
````
where 
- **contxt** : The ApplicationContext INstance object
- **registerBean()** : The method to register the bean to the context.
- **"kia"** : The name we want to give to the bean, that we add to spring context.
- **Vehicle.class** : Type of bean we want to create 
- **kiaSupplier** : The supplier returning the object instance that we want to add to
                    the spring context. 


## Creating the Bean 
- Make a POJO for the Object.
- Don't need to make the projet config class for this particular functionality, but for other functionalities, sure. 
- Main class/ Example 7 / Main business logic etc

*One can ignore this for now but we need it later, while calling the Beans from the context.
under main, if you want to scan the components from the config class*
````java
 var context = new AnnotationConfigApplicationContext(ProjConfig.class);
````
*The first method to create a supplier for **volkswagen** bean*
````java
Vehicle volkswagen = new Vehicle();
        volkswagen.setName("Volkswagen");
        Supplier<Vehicle> volkswagenSupplier = () -> volkswagen; 
````
*Secound method to create a supplier for **audi** bean*
````java
Supplier<Vehicle> audiSupplier = () -> {
            Vehicle audi = new Vehicle();
            audi.setName("Audi");
            return audi;
        }; 
````

*Creating the random functions just to check, weather such beans are being created in the context or not*<br>
*Accoring to thi random number we are registering the beans**
````java
Random random = new Random();
        int randomNumber = random.nextInt(10);
        System.out.println("randomNumber = " + randomNumber);

        if((randomNumber% 2) == 0){
            context.registerBean("volkswagen",
                    Vehicle.class,volkswagenSupplier);
        }else{
            context.registerBean("audi",
                    Vehicle.class,audiSupplier);
        }
````
*here, the code is just checking weather which bean has been registered to the context*<br>
*Ther can be possible Executions, and we are handing it **NoSuchBeanDefinitionException***
````java
Vehicle volksVehicle = null;
        Vehicle audiVehicle = null;
        try {
            volksVehicle = context.getBean("volkswagen",Vehicle.class);
        }catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException){
            System.out.println("Error while creating Volkswagen vehicle");
        }
        try {
            audiVehicle = context.getBean("audi",Vehicle.class);
        }catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException){
            System.out.println("Error while creating Audi vehicle");
        }

        if(null != volksVehicle){
            System.out.println("Programming Vehicle name from Spring Context is: " + volksVehicle.getName());
        }else{
            System.out.println("Programming Vehicle name from Spring Context is: " + audiVehicle.getName());
        }
````