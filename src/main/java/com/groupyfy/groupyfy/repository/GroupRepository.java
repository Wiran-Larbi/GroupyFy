package com.groupyfy.groupyfy.repository;

import com.groupyfy.groupyfy.model.Contact;
import com.groupyfy.groupyfy.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    // * Finding Group That has the Same Phonetic to that provided
    @Query(value = "SELECT * FROM group_ WHERE similarity(nom, :searchTerm) > :treshhold",nativeQuery = true)
    List<Group> findByNomWherePhoneticMatch(@Param("searchTerm") String nom, @Param("treshhold") Double treshhold);
    @Query(value = "SELECT * FROM group_",nativeQuery = true)
    List<Group> findGroupsAll();

    @Query(value = "SELECT * FROM group_ WHERE nom = :nom ORDER BY creation_date DESC LIMIT 1",nativeQuery = true)
    Group findGroupByNom(@Param("nom") String nom);

    @Query(value = "SELECT * FROM group_ ORDER BY creation_date DESC",nativeQuery = true)
    List<Group> findAllByCreationDateOrderByCreationDate();

    @Query(value = "SELECT * FROM group_ ORDER BY nom ASC",nativeQuery = true)
    List<Group> findAllByNomAsc();
}
