package com.navi.modelSchool.repository;

import com.navi.modelSchool.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {

    /* Spring Data JPA aloows us to apply static sorting by adding the OrderBY  Keyword to the method name along with
     * the property name and sort directions (Ascending and descending)
     */
    List<Courses> findByOrderByNameDesc();

    /* The Asc keyword is optional as OrderBY , by Default, sorts the results in the ascending order.
    *
    * */

    List<Courses> findByOrderByName();
}
