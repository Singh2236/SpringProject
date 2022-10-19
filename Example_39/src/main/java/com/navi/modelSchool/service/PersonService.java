package com.navi.modelSchool.service;

import com.navi.modelSchool.Constants.ModelSchoolConstants;
import com.navi.modelSchool.model.Person;
import com.navi.modelSchool.model.Roles;
import com.navi.modelSchool.repository.PersonRepository;
import com.navi.modelSchool.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    public boolean createNewPerson(Person person) {
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(ModelSchoolConstants.STUDENT_ROLE);
        person.setRoles(role);
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }
}
