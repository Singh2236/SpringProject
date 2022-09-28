package com.navi.modelSchool.service;

import com.navi.modelSchool.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    public static Logger log = LoggerFactory.getLogger(ContactService.class);
    public boolean saveContactData(Contact contact){
        boolean isSaved = true;
        //TODO: Need to Persist the Data inti the DB table.
        log.info(contact.toString());
        return isSaved;
    }

}
