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
- Inside bean package 

