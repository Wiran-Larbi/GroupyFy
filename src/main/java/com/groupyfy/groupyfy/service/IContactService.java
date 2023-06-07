package com.groupyfy.groupyfy.service;

import com.groupyfy.groupyfy.model.Contact;
import lombok.Builder;
import org.springframework.context.annotation.Bean;

import java.util.List;


public interface IContactService {

    List<Contact> getAllContacts();
    List<Contact> getAllContactsAsc();
    List<Contact> getAllContactsSortByCreationDate();
    List<Contact> getAllContactsSortByGenre();
    Contact getContactById(Long id);
    List<Contact> getContactByPhonetique(String nom);
    List<Contact> getContactsWithoutGroup();

    void saveContact(Contact contact);

    void deleteContact(Long id);
    void deleteContact(String telephone_person,String telephone_profess);

}
