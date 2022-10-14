# Auditing Support by Spring Data JPA

* Spring data provides sophisticated support to transparently keep tract of who created or changed an entity and when
  the change happened. To benenefit from that functionality, you have to equip your entity class with metadata that can
  be defined either using annotations or by implementing an interface.
* Additionally, auditing has to be enabled either through Annotations configurations or XML configurations to register
  the required infrastructure interface.
* Below are the steps that needs to be followed -

1. Use Annotations to indicate the audit related columns inside DB tables. Spring Data JPA ships with an entity listener
   that can be used to trigger the capturing of auditing information. ``AuditingEntityListener`` has to be registered
   for all the required entities.

_Note: ``@CreateData``, ``@CreateBy``, ``@LastModifiedDate``, ``@LastModifiedBy`` are the key annotations that
support JPA Auditing._

````java
@Data   //lombok
@MappedSuperclass  //for Spring data jpa
@EntityListeners(AuditingEntityListener.class) // for Auditing
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false) //during update operation, don't update this data field and so on.
    private LocalDateTime createdAt;
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;
}
````

2. Date Related info will be fetched from the server by JPA but for CreatedBy and UpdatedBy, we need to let JPA know how
   to fetch that info by implementing ``AuditorAware`` interface like shown below-

````java
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName()); //name of the logged in user
    }
}
````

3. Enable JAP Auditing by annotating a configuration class with the ``@EnableJpaAuditing`` annotation.

````java
@SpringBootApplication
@EnableJpaRepositories("com.navi.modelSchool.repository")
@EntityScan("com.navi.modelSchool.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class SchoolWebsite {

    public static void main(String[] args) {
        SpringApplication.run(SchoolWebsite.class, args);
    }

}
````

_Note: We can also print the queries that are being formed and executed by Spring Data JPA by enabling the below
properties,

````application.properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
````


+ Show-sql property will print the query on the console/logs whereas format_sql property will print the queries in the
  readable friendly style. 
+ Not recommended for production use, as in professional environments, it will affect the performance.