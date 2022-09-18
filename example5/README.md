# Bean creation using @Component Annotation
<p>Spring <span style="color:red">@Configuration</span> annotation is part of the spring core framework.
Spring Configuration annotation indicates that the class has @Bean definition
methods. So Spring container can process the class and generate Spring Beans
to be used in the application.

To tell Spring it needs to search for classes annotated
with stereotype annotations, we use the <span style="color:red">@ComponentScan</span> annotation over the configuration
class.</p>