package com.groupyfy.groupyfy.service;

import com.groupyfy.groupyfy.model.Contact;

import java.util.List;

public interface IGroupAssignService {

    void deleteRowWhenGroupDeleted(Long groupId);

    Long GroupExistOrNot(Long groupId);




}
