package com.groupyfy.groupyfy.service;

import com.groupyfy.groupyfy.model.Contact;
import com.groupyfy.groupyfy.model.Group;
import com.groupyfy.groupyfy.model.GroupAssign;
import com.groupyfy.groupyfy.repository.GroupAssignRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroupAssignService implements IGroupAssignService{
    @Autowired
    private GroupAssignRepository groupAssignRepository;

    @Override
    public void deleteRowWhenGroupDeleted(Long groupId) {
        groupAssignRepository.deleteWhenGroupDeleted(groupId);
    }

    @Override
    public Long GroupExistOrNot(Long groupId) {
        return groupAssignRepository.findGroupCountById(groupId);
    }

    @Override
    public void addGroupContactAssign(Long group_id, Long contact_id){
        groupAssignRepository.addAssign(group_id,contact_id);
    }

    @Override
    public void addAssign(GroupAssign groupAssign) {
        groupAssignRepository.save(groupAssign);
    }

    @Override
    public Long getGroupCountContacts(Long group_id) {
        return  groupAssignRepository.findGroupCountById(group_id);
    }

    @Override
    public String getGroupNameForContact(Long contact_id) {
        return groupAssignRepository.findGroupNameForContact(contact_id);
    }
}
