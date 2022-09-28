# In this module we will see the concepts of @RequestParam and @PathParam

# Difference b/w them Query Params and Path Params

Query:

1. The frontEnd Application can send the URl in a Query Param Style. Since, some conditions are also embedded inside
   the URL. ``like http://someWebsite.com/products?order=new``
   or ``http://someWebsite.com/products?order=popular&filer=new``.

Path:

1. This works by mentioning the variables inside the path value itself. like:
   ``http://someWebsite.com/products/popular/new``
   ``http://someWebsite.com/products/popular/highRated``
2. More variables can be added just by appending like in example two above.

# Query Params

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

# Path variable

- The ``@PathVariable`` annotation is used to extract the value from the URL. It is the most suitable for the RESTful
  web
  service where the URL contains some value. Spring MVC allows us to use multiple @PathVariable annotation in the same
  method.
- For Example, if we want to get the values from a request URL path, then we can use @PathVariable annotation like in
  below example.

````
http://localhost://8080/holidays/all
http://localhost://8080/holidays/federal
http://localhost://8080/holidays/frstival
````

# Demo of Query Params

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

# Demo Path Params

1. Update the link in the footer to
   `` <li><a th:href="@{/holidays/all}">Holidays</a></li>``
2. controller class
    1. First we need to catch the path variable by creating a ``@GetMapping`` Annotation. We put `display` variable
       there. So, whatever value we are sending through the path variable from the UI after holidays, that dynamic value
       will be assigned to the variable ``display``.
    2. In order to catch that value in our ``displyHolidays`` java method, we need to use the annotation `@PathVaribale`
       with the same exact variable name as a method input parameter.
    3. Param name should match the method parameter name otherwise we can also use the ``name`` attribute to the value.
    4. Now, we have to build the logic according the value we are receiving, and according to the value i.e. all,
       federal or festival, we need to send the data accordingly to the frontend.

````java
@GetMapping("/holidays/{display}")
public String displayHolidays(@PathVariable String display,Model model){
        if(null!=display&&display=="all"){
        model.addAttribute("festival",true);
        model.addAttribute("federal",true);
        }else if(null!=display&&display=="festival"){
        model.addAttribute("festival",true);
        }else if(null!=display&&display=="federal"){
        model.addAttribute("federal",true);

        }

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

The rest of the code for front end, is going to be the same. Since we are assigning the values to the model, according
to the value we are getting in the url.
   





