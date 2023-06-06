package com.groupyfy.groupyfy.service;

import com.groupyfy.groupyfy.model.Contact;
import com.groupyfy.groupyfy.model.Group;
import com.groupyfy.groupyfy.model.GroupAssign;
import com.groupyfy.groupyfy.model.GroupAssignId;
import com.groupyfy.groupyfy.repository.ContactRepository;
import com.groupyfy.groupyfy.repository.GroupAssignRepository;
import com.groupyfy.groupyfy.repository.GroupRepository;
import groovy.transform.AutoImplement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GroupService implements IGroupService{

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupAssignService groupAssignService;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Group> getAllGroups(){
        return groupRepository.findGroupsAll();
    }

    public List<Group> getGroupByPhonetique(String nom) {
        return groupRepository.findByNomWherePhoneticMatch(nom,0.1);
    }
    @Override
    public List<Group> getAllGroupssSortByCreationDate(){
        return groupRepository.findAllByCreationDateOrderByCreationDate();
    }

    @Override
    public List<Group> getAllGroupsSortByNomAsc(){
        return groupRepository.findAllByNomAsc();
    }

    @Override
    public int addGroupAssign(String nom, List<Long> contact_ids) {

        // ? Saving first the group then finding its id
        groupRepository.save(new Group(nom));
        // ? Finding the id of the saved group
        Group group = groupRepository.findGroupByNom(nom);

        for (int i = 0; i < contact_ids.size(); i++){
            Contact contact = contactRepository.findContactById(contact_ids.get(i));

            GroupAssign groupAssign = new GroupAssign(contact,group);

            groupAssignService.addAssign(groupAssign);

        }

        /*
        List<Long> contacts_ids = contact_ids;
        groupAssignService.addGroupContactAssign(group.getId(),contacts_ids.get(0));
        */
        return 1;
    }

    @Override
    public Long getGroupContactCounts(Long group_id) {
        return groupAssignService.getGroupCountContacts(group_id);
    }

    @Override
    public void addGroup(String nom) {
        // ? Saving first the group then finding its id
        groupRepository.save(new Group(nom));

    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
        if(groupAssignService.GroupExistOrNot(id) != 0)
            groupAssignService.deleteRowWhenGroupDeleted(id);
    }
}
