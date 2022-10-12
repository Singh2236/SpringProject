package com.navi.modelSchool.service;


import com.navi.modelSchool.Constants.ModelSchoolConstants;
import com.navi.modelSchool.model.Contact;
import com.navi.modelSchool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/*
@Slf4j, is a Lombok-provided annotation that will automatically generate an SLF4J
Logger static property in the class at compilation time.
* */
@Slf4j
@Service

public class ContactService {
    private final ContactRepository contactRepository;


    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * Save Contact Details into DB
     *
     * @param contact
     * @return boolean
     */
    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = true;
        contact.setStatus(ModelSchoolConstants.OPEN);
        contact.setCreatedBy(ModelSchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());

        //Return type from the save method is Pojo class object along with Primary key, which is created and stored into
        // the database. This is the create operation with repo(crud).save() method.
        Contact saveContact = contactRepository.save(contact);

        //checking if the value is saved
        if(null != saveContact && saveContact.getContactId() > 0 )
            isSaved = true;

        return isSaved;
    }

    public List<Contact> findMsgWithOpenStatus() {
        //Using derived query method "findByStatus()" to get all the rows with open status.
        List<Contact> contactMsgs = contactRepository.findByStatus(ModelSchoolConstants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId, String updatedBy) {

        Boolean isUpdated =false;
        //fetching the contact from the database according to id provided
        // contactRepository.findById(contactId) returns the POJO, if the data is not present inside the database it
        // will return null, this is the reason we have Optional of Contact.
        Optional<Contact> contact = contactRepository.findById(contactId);

        //if contact Object is present(id) modify this retrieved object
        // lambda expression population with values to be modified
        contact.ifPresent(contact1 -> {
            contact1.setStatus(ModelSchoolConstants.CLOSE);
            contact1.setUpdatedBy(updatedBy);
            contact1.setUpdatedAt(LocalDateTime.now());
        });

        //saving the modified value
        //contact.get() --> since Contact object:contact is an optional, we have to call this method
        Contact updatedContactObject = contactRepository.save(contact.get());




        if (null != updatedContactObject && updatedContactObject.getUpdatedBy() != null) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
