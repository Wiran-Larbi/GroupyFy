package com.groupyfy.groupyfy.service;


import com.groupyfy.groupyfy.model.Contact;
import com.groupyfy.groupyfy.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements IContactService{

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    public List<Contact> getAllContactsAsc(){
        return contactRepository.findAllOrderByNomAsc();
    }

    public Contact getContactById(Long id){
        return contactRepository.findContactById(id);
    }
    public List<Contact> getAllContactsSortByGenre(){
        return contactRepository.findAllOrderByGenreDesc();
    }

    public List<Contact> getAllContactsSortByCreationDate() {
        return contactRepository.findAllByCreationDateOrderByCreationDate();
    }
    public List<Contact> getContactByPhonetique(String nom) {
        return contactRepository.findByNomWherePhoneticMatch(nom,0.1);
    }

    @Override
    public List<Contact> getContactsWithoutGroup() {
        return contactRepository.findContactsWithNoGroup();
    }


    public void saveContact(Contact contact){
        contactRepository.save(contact);
    }

    public void deleteContact(Long id){
        contactRepository.deleteById(id);
    }

    public void deleteContact(String telephone_perso,String telephone_profess){
        contactRepository.deleteByTelephonePersonnelAndTelephoneProfessionnel(telephone_perso,telephone_profess);

    }
}
