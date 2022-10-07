# Databases

1. JDBC
2. JDBC Template
3. H2

# JDBC

1. Add dependencies

````xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
````

# JDBC Template

* Reduces errors and handles -
    * Creating the connection.
    * Maintaining the connection.
    * Closing the connection.
    * Creating the Statement Object
    * Avoiding the common errors
* Getting started (**optional with SpringBoot, with SpringBoot- define - inside application.properties**)
  Creating the bean of JDBC Template - This bean can be used in all the repo classes, and can perform database
  operations by invoking various methods available inside this JDBC Template.
    1. Creating the bean of type ``DataSource`` of JDBC Data Source. This provides the database connection to my
       application.
    2. Inside any Repository/DAO classes we want to execute the queries, we need to create a bean/object
       of ``jdbcTemplate`` by injecting the ``dataSource`` bean.
    3. Start doing the operations like the code below.

* some methods()
    * `this.jdbcTemplate`
        * `.queryForObject("query", return_type, anyReqPara)`
        * `.update("query")`  //can also be used for stored procedures
        * `.execute("query")`

# H2

### Setting UP

1. dependencies

````xml

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
````

2. Properties of H2 database inside application.properties

````
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
````

3. Table creation scripts inside resources folder

````mysql
CREATE TABLE IF NOT EXISTS `contact_msg`
(
    `contact_id` int AUTO_INCREMENT PRIMARY KEY,
    `name`       varchar(100) NOT NULL,
    `mobile_num` varchar(10)  NOT NULL,
    `email`      varchar(100) NOT NULL,
    `subject`    varchar(100) NOT NULL,
    `message`    varchar(500) NOT NULL,
    `status`     varchar(10)  NOT NULL,
    `created_at` TIMESTAMP    NOT NULL,
    `created_by` varchar(50)  NOT NULL,
    `updated_at` TIMESTAMP   DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL
);
````

# Connection and CRUD Operations

1. Data present inside your database table has to be converted into pojo class. So, we have to make sure that our pojo
   classes are in sync with our database tables. Let's check our model class of ``contect`` and add the required fields.
2. Create a new package ``repositories``, add a repo class for Contact i.e. ``contactRepo`` and add the method to save
   message coming from controller layer. Here, ``jdbcTemplate`` is autowired at constructor, and it is autoconfigured. 

````java
@Repository
public class ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveContactMsg(Contact contact) {
        String sql = "INSERT INTO CONTACT_MSG(NAME,MOBILE_NUM,EMAIL,SUBJECT,MESSAGE,STATUS,CREATED_AT,CREATED_BY) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, contact.getName(), contact.getMobileNum(), contact.getEmail(),
                contact.getSubject(), contact.getMessage(), contact.getStatus(), contact.getCreatedAt(),
                contact.getCreatedBy());
    }
}
````
3. From our service layer we need to invoke the repo. For that we need to inject ``contactRepo`` bean to ``contactService``. 


