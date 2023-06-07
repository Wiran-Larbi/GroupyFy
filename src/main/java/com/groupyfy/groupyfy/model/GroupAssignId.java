package com.groupyfy.groupyfy.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupAssignId implements Serializable {

    @Column(name = "contact_id", nullable = false)
    private Long contactId;

    @Column(name = "group_id", nullable = false)
    private Long groupId;
}
