package com.groupyfy.groupyfy.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_assign")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupAssign {

    @EmbeddedId
    private GroupAssignId id;

    public GroupAssign(Contact contact,Group group){
        this.contact_ = contact;
        this.group_ = group;
        this.id = new GroupAssignId(contact.getId(), group.getId());
    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @MapsId("contactId")
    @JoinColumn(name = "contact_id",foreignKey = @ForeignKey(name = "group_assign_contact_fkey"))
    private Contact contact_;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @MapsId("groupId")
    @JoinColumn(name = "group_id",foreignKey = @ForeignKey(name = "group_assign_group_fkey"))
    private Group group_;

    @CreationTimestamp
    @Column(name = "assign_at",nullable = false)
    private LocalDateTime assignedAt;


}
