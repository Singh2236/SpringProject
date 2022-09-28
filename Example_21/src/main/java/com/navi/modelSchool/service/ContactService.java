package com.navi.modelSchool.service;

import com.navi.modelSchool.model.ContactModel;

public class ContactService {
    public void printContactData(ContactModel contact){
        System.out.println(contact.getName() + " ");
        System.out.println(contact.getEmil() + " ");
        System.out.println(contact.getMobileNumber() + " ");
        System.out.println(contact.getSub() + " ");
        System.out.println(contact.getMsg() + " ");
    }

}
