package com.groupyfy.groupyfy.service;

import com.groupyfy.groupyfy.model.Contact;
import com.groupyfy.groupyfy.model.Group;
import com.groupyfy.groupyfy.model.GroupAssign;

import java.util.List;
import java.util.Optional;

public interface IGroupAssignService {

    void deleteRowWhenGroupDeleted(Long groupId);

    Long GroupExistOrNot(Long groupId);

    void addGroupContactAssign(Long group_id, Long contact_id);

    void addAssign(GroupAssign groupAssign);



}
