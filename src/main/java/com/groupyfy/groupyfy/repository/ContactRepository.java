package com.groupyfy.groupyfy.repository;

import com.groupyfy.groupyfy.model.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface ContactRepository extends JpaRepository<Contact,Long> {

        // * Displaying All Contacts By Alphabetic Order Ascending
        @Query(value = "SELECT * FROM contact_ ORDER BY nom ASC;",nativeQuery = true)
        List<Contact> findAllOrderByNomAsc();

        // * Displaying All Contacts By Alphabetic Order Descending
        @Query(value = "SELECT * FROM contact_ ORDER BY nom DESC",nativeQuery = true)
        List<Contact> findAllOrderByNomDesc();

        @Query(value = "SELECT * FROM contact_ ORDER BY creation_date DESC",nativeQuery = true)
        List<Contact> findAllByCreationDateOrderByCreationDate();

        @Query(value = "SELECT * FROM contact_ ORDER BY genre DESC",nativeQuery = true)
        List<Contact> findAllOrderByGenreDesc();


        // * Finding Contact That has the Same Phonetic to that provided
        @Query(value = "SELECT * FROM contact_ WHERE similarity(nom, :searchTerm) > :treshhold",nativeQuery = true)
        List<Contact> findByNomWherePhoneticMatch(@Param("searchTerm") String nom,@Param("treshhold") Double treshhold);

        // * Search for a contact by id
        @Query(value = "SELECT * FROM contact_ WHERE id = :id",nativeQuery = true)
        Contact findContactById(@Param("id") Long id);

        // * Search for a contact by name
        @Query(value = "SELECT * FROM contact_ WHERE nom = :nom",nativeQuery = true)
        Contact findContactByNom(@Param("nom") String nom);

        // * Search for a contact by phone number
        @Query(value = "SELECT * FROM contact_ WHERE telephone_personnel = :telephone OR telephone_professionnel = :telephone",nativeQuery = true)
        Contact findContactByTelephone(@Param("telephone") String telephone);

        @Query(value = "SELECT * FROM contact_ WHERE id NOT IN (SELECT contact_id FROM group_assign)",nativeQuery = true)
        List<Contact> findContactsWithNoGroup();



        @Override
        void deleteById(Long id);

        @Query(value = "DELETE FROM contact_ WHERE telephone_personnel = :telephone_personnel AND telephone_professionnel = :telephone_professionel",nativeQuery = true)
        void deleteByTelephonePersonnelAndTelephoneProfessionnel(@Param("telephone_personnel") String telephone_personnel,@Param("telephone_professionel") String telephone_professionel);
}
