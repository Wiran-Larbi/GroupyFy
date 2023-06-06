package com.groupyfy.groupyfy.service;

import com.groupyfy.groupyfy.model.Contact;
import com.groupyfy.groupyfy.model.Group;

import java.util.List;

public interface IGroupService {

    List<Group> getAllGroups();
    List<Group> getGroupByPhonetique(String nom);

    List<Group> getAllGroupssSortByCreationDate();

    List<Group> getAllGroupsSortByNomAsc();



    void deleteGroup(Long id);
}
