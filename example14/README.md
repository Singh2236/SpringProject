# Bean Scope 



# Bean Scopes inside Spring 
1. Singleton

````java
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
punclic class classNameBean(){
    //some code
}
````
2. Prototype
3. Request
4. Session
5. Application