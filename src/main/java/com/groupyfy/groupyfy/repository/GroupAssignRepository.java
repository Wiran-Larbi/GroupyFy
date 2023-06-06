package com.groupyfy.groupyfy.repository;

import com.groupyfy.groupyfy.model.GroupAssign;
import com.groupyfy.groupyfy.model.GroupAssignId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupAssignRepository extends JpaRepository<GroupAssign, GroupAssignId> {

        @Query(value = "DELETE FROM group_assign WHERE group_id = :groupId",nativeQuery = true)
        void deleteWhenGroupDeleted(@Param("groupId") Long id);

        @Query(value = "SELECT COUNT(*) FROM group_assign  WHERE group_id = :groupId",nativeQuery = true)
        Long findGroupCountById(@Param("groupId") Long id);
}
