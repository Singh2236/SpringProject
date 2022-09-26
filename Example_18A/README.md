# Changing the default behaviour of the Spring framework

##### Default Behaviour

Default port : 8080 <br>
Context path : ' ' i.e. Blank

##### Modifications

For all the configurations inside the Spring Boot Application, we modify `application.properties` file
under `src/resources/application.properties`. Default configurations will be overridden by mentioning those properties
inside this file.
````
server.port = 8081
server.servlet.context-path= /modelSchool
````
![img_2.png](img_2.png)


#### Browser View

![img_3.png](img_3.png)

**or**
![img_4.png](img_4.png)
