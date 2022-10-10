package com.navi.modelSchool.service;


import com.navi.modelSchool.Constants.ModelSchoolConstants;
import com.navi.modelSchool.model.Contact;
import com.navi.modelSchool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        //TODO - Need to persist the data into the DB table
        contact.setStatus(ModelSchoolConstants.OPEN);
        contact.setCreatedBy(ModelSchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        int results = contactRepository.saveContactMsg(contact);
        if(results > 0 )
            isSaved = true;

        return isSaved;
    }

    public List<Contact> findMsgWithOpenStatus() {
        List<Contact> contactMsgs = contactRepository.findMsgsWithStatus(ModelSchoolConstants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId, String updatedBy) {
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(contactId, ModelSchoolConstants.CLOSE, updatedBy);
        if (result > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
