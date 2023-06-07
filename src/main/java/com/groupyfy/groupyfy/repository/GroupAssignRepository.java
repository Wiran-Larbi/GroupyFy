package com.groupyfy.groupyfy.repository;

import com.groupyfy.groupyfy.model.Contact;
import com.groupyfy.groupyfy.model.GroupAssign;
import com.groupyfy.groupyfy.model.GroupAssignId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupAssignRepository extends JpaRepository<GroupAssign, GroupAssignId> {

        @Query(value = "DELETE FROM group_assign WHERE group_id = :groupId",nativeQuery = true)
        void deleteWhenGroupDeleted(@Param("groupId") Long id);

        @Query(value = "SELECT COUNT(*) FROM group_assign  WHERE group_id = :groupId",nativeQuery = true)
        Long findGroupCountById(@Param("groupId") Long id);

        @Query(value = "INSERT INTO group_assign(group_id,contact_id) VALUES (:group_id,:contact_id)",nativeQuery = true)
        void addAssign(@Param("group_id") Long group_id, @Param("contact_id") Long contact_id);
        @Query(value = "SELECT g.nom FROM group_ g JOIN group_assign a ON g.id = a.group_id WHERE a.contact_id = :id LIMIT 1",nativeQuery = true)
        String findGroupNameForContact(@Param("id") Long contact_id);

}
