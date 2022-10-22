package com.navi.modelSchool.service;

import com.navi.modelSchool.Constants.ModelSchoolConstants;
import com.navi.modelSchool.model.Person;
import com.navi.modelSchool.model.Roles;
import com.navi.modelSchool.repository.PersonRepository;
import com.navi.modelSchool.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewPerson(Person person) {
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(ModelSchoolConstants.STUDENT_ROLE); // Role from the DB or its entity
        person.setRoles(role);      // Get the person object and assign the role
        person.setPwd(passwordEncoder.encode(person.getPwd())); //getting the plain text password from the object and decoding it.
        person = personRepository.save(person);  // saving the data and hashed password.
        // if saved return true
        if (null != person && person.getPersonId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }
}
