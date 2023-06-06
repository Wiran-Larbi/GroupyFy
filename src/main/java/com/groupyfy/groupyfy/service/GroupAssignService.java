package com.groupyfy.groupyfy.service;

import com.groupyfy.groupyfy.repository.GroupAssignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
}
