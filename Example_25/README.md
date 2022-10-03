# Demo of Spring Bean Web Scopes

https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/ch04s04.html

1. **Singletons** - Scopes a single bean definition to a single object instance per Spring IoC container, same beam to
   be
   referred and used by our Spring Application. Regardless of threads.
2. **Prototype** - Scopes a single bean definition to any number of object instances. Opposite of Singletons.
3. **Request** -Scopes a single bean definition to the lifecycle of a single HTTP request; that is each and every HTTP
   request will have its own instance of a bean created off the back of a single bean definition. Only valid in the
   context of a web-aware Spring ApplicationContext.
4. **Session** - Scopes a single bean definition to the lifecycle of a HTTP Session. Only valid in the context of a
   web-aware Spring ApplicationContext.
5. **Application** - Scopes a single bean definition to the lifecycle of a global HTTP Session. Typically, only valid
   when
   used in a portlet context. Only valid in the context of a web-aware Spring ApplicationContext.

## The Last three Bean web scopes.

These three bean web scopes are only to be used inside Web Applications. Whereas, Singleton and Prototype beans you can
use in any kind of applications. The Reason for that is: They work based upon the web requests. Each request in the Web
Application is processed by the help of HTTP Protocol Requests.

# Request Scope (@RequestScope)

Anything we do in the front end, which has to do sth to the back end, will send a HTTP request to backend. This
annotation helps in creating bean for every **request** that is coming to the back end server.

# Session Scope (@SessionScope)

Bean created, when a new HTTP Session is formed, it maintains the same bean until the same particular HTTP Session is
over or invalidated. A session will start when a user start using our Web-Application. The Same session will continue
until he closes the browser, or invalidating the session by signing out of the Web-Application, or clearing the cache.
HTTP Session is combination of multiple HTTP Requests that is coming from the same user.

# Application Scope (ApplicationScope)

Only one bean is created for the entire application, like if there are multiple users accessing you Application, they
will have the same reference of   bean. 

