package com.groupyfy.groupyfy.service;

import com.groupyfy.groupyfy.model.Contact;
import com.groupyfy.groupyfy.model.Group;
import com.groupyfy.groupyfy.model.GroupAssignId;
import com.groupyfy.groupyfy.repository.GroupAssignRepository;
import com.groupyfy.groupyfy.repository.GroupRepository;
import groovy.transform.AutoImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService implements IGroupService{

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupAssignService groupAssignService;

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
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
        if(groupAssignService.GroupExistOrNot(id) != 0)
            groupAssignService.deleteRowWhenGroupDeleted(id);
    }
}
