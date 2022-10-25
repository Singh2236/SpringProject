# ManyToMany mapping demo using Courses, Students
## creating the tables in the databse 
````mysql
CREATE TABLE IF NOT EXISTS `courses` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `fees` varchar(10) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`course_id`)
);

CREATE TABLE IF NOT EXISTS `person_courses` (
  `person_id` int NOT NULL,
  `course_id` int NOT NULL,
  FOREIGN KEY (person_id) REFERENCES person(person_id),
  FOREIGN KEY (course_id) REFERENCES courses(course_id),
   PRIMARY KEY (`person_id`,`course_id`)
);
````

## Making Entity tables, and joining them 
````java
@Slf4j
@Getter
@Setter
@Entity
public class Courses extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator ="native" )
    @GenericGenerator(name = "native", strategy = "native")
    private int courseId;
    private String name;
    private String fees;
    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Person> persons = new HashSet<>();
}
````

````java
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "person_courses",
            joinColumns = {
                    @JoinColumn(name = "person_id", referencedColumnName = "personid")},
            inverseJoinColumns = {
                    @JoinColumn(name = "course_Id", referencedColumnName = "courseID")})
    private Set<Courses> courses = new HashSet<>();
````

## Creating the Courses Repository 
````java
@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
}
````

## Building paths which are mentioned in the dashboard.html file in the AdminController 

``\<a th:href="@{/admin/displayCourses}">``

````java

````


