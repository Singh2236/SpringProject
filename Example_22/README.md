# @RequestParam Annotation

- In Spring ``@RequestParam`` annotation is used to map either query parameter or form data.
- For Example: if we want to get parameter value from a HTTP GET request URL then we can use @RequestParam Annotation
  like in the example below:

```http://localhost:8080/holidays?featival=true&federal=true```

1. The @RequestParam supports annotations like ``name``, ``required``, ``value``, ``defaultValue``. We can use then in
   our application based on the requirements.
2. The ``name`` attribute indicates the name of the request parameter bind to.
3. The ``required`` attribute is used to make a field either optional or mandatory. If it is mandatory, an exception
   will through case of missing fields.
4. The ``value`` attribute is similar to named elements and can be used as alies.
5. The ``defaultValue`` for the parameter is to handle missing values or null values. If the parameter does not contain
   any value then this default value will be considered.

# Path variable Annotations

- The @PathVariable annotation is used to extract the value from the URL. It is the most suitable for the RESTful web
  service where the URL contains some value. Spring MVC allows us to use multiple @PathVariable annotation in the same
  method.
- For Example, if we want to get the values from a request URL path, then we can use @PathVariable annotation like in
  below example.

````
http://localhost://8080/holidays/all
http://localhost://8080/holidays/federal
http://localhost://8080/holidays/frstival
````
