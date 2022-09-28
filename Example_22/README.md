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

# Making changes in the Project to add @RequestParams

````html

<li><a th:href="@{/holidays(festival='true',federal='true')}">Holidays</a></li>
````

All the query params are seperated by a comma.

Now, let's change the code in our Controller class.

````java
@GetMapping("/holidays")
public String displayHolidays(@RequestParam(required = false) boolean festival,
@RequestParam(required = false) boolean federal,Model model){
        model.addAttribute("festival",festival); //false //saving the inputs from the UI inside the Model Object.
        model.addAttribute("federal",federal); //false 
        List<Holiday> holidays=Arrays.asList(
        new Holiday(" Jan 1 ","New Year's Day",Holiday.Type.FESTIVAL),
        new Holiday(" Oct 31 ","Halloween",Holiday.Type.FESTIVAL),
        new Holiday(" Nov 24 ","Thanksgiving Day",Holiday.Type.FESTIVAL),
        new Holiday(" Dec 25 ","Christmas",Holiday.Type.FESTIVAL),
        new Holiday(" Jan 17 ","Martin Luther King Jr. Day",Holiday.Type.FEDERAL),
        new Holiday(" July 4 ","Independence Day",Holiday.Type.FEDERAL),
        new Holiday(" Sep 5 ","Labor Day",Holiday.Type.FEDERAL),
        new Holiday(" Nov 11 ","Veterans Day",Holiday.Type.FEDERAL)
        );
        Holiday.Type[]types=Holiday.Type.values();
        for(Holiday.Type type:types){
        model.addAttribute(type.toString(),
        (holidays.stream().filter(holiday->holiday.getType().equals(type)).collect(Collectors.toList())));
        }
        return"holidays.html";
        }

````

The purpose of adding inputs to the model is: we can send them back again to our holidays.html page,inside our
holidays.html we have some ``if-else`` conditions saying: ONly if this attribute is true/false and display the
content accordingly. These are all the changes in the back-end controller. Now, let's change the code in holidays.html

````thymeleafexpressions
 <div class="col-lg-6" th:if="${festival}==true">
     <h5 class="sub-title-timeline"><i class="fas fa-snowman"></i> Festival Holidays</h5>
        <div class="timeline">
            <div class="timeline">
                <div th:each="holiday : ${FESTIVAL}" class="column">
                    <div class="title">
                        <h2 th:text="${holiday.reason}"></h2>
                    </div>
                    <div class="description">
                         <h6 th:text="${holiday.day}" class="fas fa-calendar-alt"><i class="fas fa-calendar-alt"></i></h6>
                    </div>
                </div>
             </div>
        </div>
    </div>
 </div>
````

So, if our ``if`` condition is false, this entire ``div`` won't work. The condition which we want to mention is, there
will be an attribute inside our Model, and the same name has to be set in Controller class as an attribute name. And
there also you will have a value true and false. So whenever, the value of this "festival" is true, we will show the
festival holidays. Otherwise, we won't display. The same changes we have made in the "federal" holidays.

<br>

Since ``required=false`` attribute is false of the @RequestParam, even if the link is sent to the backend, the back-enf
will assume it to be null/false and show the expected holidays. That Param was an Optional.

<br>

Let's make param mandatory by making the ``required`` fields ``true```.
This means that, backend server now wants these boolean values, egal true or false, and if not provided, it will
throw an error.


