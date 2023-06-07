package com.groupyfy.groupyfy.controller;

import com.groupyfy.groupyfy.model.Contact;
import com.groupyfy.groupyfy.service.ContactService;
import com.groupyfy.groupyfy.service.GroupAssignService;
import com.groupyfy.groupyfy.service.IContactService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/contacts")
@SessionAttributes("id")
public class ContactController {

    private ContactService contactService;
    private GroupAssignService groupAssignService;

    @GetMapping()
    public String contacts(Model model){
        List<Contact> contacts = contactService.getAllContactsAsc();

        contacts.forEach(contact -> {
            String groupName = groupAssignService.getGroupNameForContact(contact.getId());
            System.out.println(groupName);
            contact.setGroupName(groupName);
        });


        model.addAttribute("contacts",contacts);
        model.addAttribute("contacts_count",contacts.size());

        return "contacts-ui";
    }

    @GetMapping("/sort/date_creation")
    public String sortContactsDateCreation(Model model){
        List<Contact> contacts = contactService.getAllContactsSortByCreationDate();
        model.addAttribute("contacts",contacts);
        model.addAttribute("contacts_count",contacts.size());
        return "contacts-ui";
    }
    @GetMapping("/sort/genre")
    public String sortContactsGenre(Model model){
        List<Contact> contacts = contactService.getAllContactsSortByGenre();
        model.addAttribute("contacts",contacts);
        model.addAttribute("contacts_count",contacts.size());
        return "contacts-ui";
    }

    @GetMapping("/search/phonetique")
    public String searchPhonetique(Model model,@RequestParam("nom") String nom) {
        if(nom.equals("all")) {
            List<Contact> contacts = contactService.getAllContacts();
            model.addAttribute("contacts",contacts);
            model.addAttribute("contacts_count",contacts.size());
            return "contacts-ui";
        }else{
            List<Contact> contacts = contactService.getContactByPhonetique(nom);
            model.addAttribute("contacts",contacts);
            model.addAttribute("contacts_count",contacts.size());

            return "contacts-ui";
        }

    }

    @GetMapping("/add")
    public String addContact(Model model) {
        model.addAttribute("contact",new Contact());
        return "add-contact";
    }

    @PostMapping("/add")
    public String saveContact(Model model, @ModelAttribute("contact") Contact contact, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "add-contact";
        }
        Long IdToDelete = (Long) model.getAttribute("id");
        contactService.deleteContact(IdToDelete);
        /*
        if(contactToDelete != null && contactToDelete.getTelephonePersonnel() != null && contactToDelete.getTelephoneProfessionnel() != null){
            contactService.deleteContact(contactToDelete.getTelephonePersonnel(),contactToDelete.getTelephoneProfessionnel());
        }
        */


        contactService.saveContact(contact);
        return "redirect:/contacts?message=updated-successfully";
    }

    @GetMapping("/edit")
    public String UpdateContact(Model model,@RequestParam(name="Id") Long id){
        // ? First We will try to fetch the contact and distplay its old Values in the form
        Contact contact = contactService.getContactById(id);
        contact.setId(id);

        // Save the model object to the database or perform other operations

        // Add the model object to the model


        model.addAttribute("contact",contact);
        model.addAttribute("id",id);


        return "add-contact";

    }


    @GetMapping("/delete")
    public String deleteContact(Model model,@RequestParam(name="Id") Long id){
        // * Deleting the Contact with the specified id

            contactService.deleteContact(id);
            return "redirect:/contacts?message=deleted_successfully";

    }
    /*
    @GetMapping()
    public String defaultPath(){
        return "default";
    }

     */



}
