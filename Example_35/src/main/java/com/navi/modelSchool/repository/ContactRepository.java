package com.navi.modelSchool.repository;

import com.navi.modelSchool.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    // By the following line, we are letting Spring Data JPA know that, there is a field in the Contact POJO Class,
    //This Status corresponds to a colum inside my database table, so find the records based on the Status value which
    // being passed. Since, this method is returning the multiple rows of the contact_msg table -->
    // return type List<Contact>
    List<Contact> findByStatus(String status);

}


