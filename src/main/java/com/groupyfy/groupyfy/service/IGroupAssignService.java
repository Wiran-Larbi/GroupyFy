package com.groupyfy.groupyfy.service;

public interface IGroupAssignService {

    void deleteRowWhenGroupDeleted(Long groupId);

    Long GroupExistOrNot(Long groupId);


}
