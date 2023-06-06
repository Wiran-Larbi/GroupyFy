package com.groupyfy.groupyfy;

import com.github.javafaker.Faker;
import com.groupyfy.groupyfy.model.Contact;
import com.groupyfy.groupyfy.model.Group;
import com.groupyfy.groupyfy.model.GroupAssign;
import com.groupyfy.groupyfy.repository.ContactRepository;
import com.groupyfy.groupyfy.repository.GroupAssignRepository;
import com.groupyfy.groupyfy.repository.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GroupyFyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupyFyApplication.class, args);
    }

    public Contact generateContact(){
        Faker faker = new Faker();
        String first_name = faker.name().firstName();
        String last_name = faker.name().lastName();
        String tel_perso = faker.numerify("+212-6##-###-###");
        String tel_pro = faker.numerify("+212-5##-###-###");
        String address = faker.address().streetAddress();
        String email_perso = String.format("%s.%s@gmail.com",last_name,first_name);
        String email_pro = String.format("%s.%s@work.co",last_name,first_name);
        String genre = "Male";
        Contact contact = new Contact(last_name,first_name,tel_perso,tel_pro,address,email_perso,email_pro,genre);

        return contact;

    }

    @Bean
    CommandLineRunner commandLineRunner(ContactRepository contactRepository, GroupRepository groupRepository, GroupAssignRepository groupAssignRepository) {
        return (args) -> {

            // * First Thing To do is To create a new Contacts

            //GroupAssign assign = new GroupAssign(generateContact(),new Group("Fight"));

            //groupAssignRepository.save(assign);
            /*
            Group group1 = new Group("familly");
            Group group2 = new Group("sport");
            Group group3 = new Group("fun");

            groupRepository.saveAll(List.of(group1,group2,group3));
            */

/*
            Contact contact = generateContact();




            Contact contact1 = new Contact("Wiran","Larbi","+212 603 167 837","+212 566 345 112","221 Amyot Ville","Wiran.larbi@gmail.com","Wiran.Larbi@work.co","male");
            Contact contact2 = new Contact("Wayrani","Souad","+212 622 445 102","+212 533 567 102","221 Mondo Ville","Wayrani.Souad@gmail.com","Wayrani.Souad@work.co","female");
            Contact contact3 = new Contact("Uirane","Omar","+212 611 234 567","+212 511 667 778","4553 Hank City","Uirane.Omar@gmail.com","Uirane.Omar@work.co","male");
            Contact contact4 = generateContact();
            Contact contact5 = generateContact();


             contactRepository.saveAll(List.of(contact,contact1,contact2,contact3,contact4,contact5));
*/
            /*
            // * Displaying Contacts By Alphabetique Ordre ASC
            System.out.println("Displaying Contacts by Alphabetic Order ASC");
            List<Contact> contactsAsc = contactRepository.findAllOrderByNomAsc();
            contactsAsc.stream().forEach(contact -> {
                System.out.println(contact.getNom());
            });
            // * Displaying Contacts By Alphabetique Ordre DESC
            System.out.println("Displaying Contacts by Alphabetic Order DESC");
            List<Contact> contactsDesc = contactRepository.findAllOrderByNomDesc();
            contactsDesc.stream().forEach(contact -> {
                System.out.println(contact.getNom());
            });


            // * Finding a Contact by Phonetic Search - means the names that sounds like similar
            System.out.println("Displaying Contacts That has Similar Phonetic With a given string");
            List<Contact> contactsPhonetic = contactRepository.findByNomWherePhoneticMatch("irani",0.1);
            contactsPhonetic.stream().forEach(contact -> {
                System.out.println(contact.getNom());
            });
            */
            // * Updating the name and genre for an entity
           /*
            try{
                Long id = 252L;
                contactRepository.findById(id).map(contact -> {
                    // Updating the fields of the existing contact with the new data;
                    contact.setPrenom("Sara");
                    contact.setNom("Ouald Chaib");
                    contact.setEmailPersonnel("Ouald.Sara@gmail.com");
                    contact.setEmailProfessionnel("Ouald.Sara@work.co");
                    contact.setGenre("female");

                    // * Saving the updated contact
                    return contactRepository.save(contact);

                }).orElseThrow(() -> new EntityNotFoundException("The Contact with the following id doesnt exist : " + id));

            }catch(Exception excp) {
                System.out.println(excp.getMessage());
            }
            */
            // * Deleting a contact in db by id
            /*
            try{
                Long id = 252L;
                if(contactRepository.existsById(id)){
                    contactRepository.deleteById(id);
                    System.out.println(String.format("Contact with id %d is deleted successfully ...",id));
                }else{
                    throw new EntityNotFoundException("The Contact with the following id doesnt exist : " + id);
                }

            }catch(Exception excp) {
                System.out.println(excp.getMessage());
            }

             */



        };
    }

}
