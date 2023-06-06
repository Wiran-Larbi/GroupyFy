package com.groupyfy.groupyfy.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "group_")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Group {
    public Group(String nom){
        this.nom = nom;
    }

    @Id
    @SequenceGenerator(name = "group_id_seq",sequenceName = "group_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "group_id_seq")
    private Long id;

    @Column(name = "nom",nullable = false)
    private String nom;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
    }

    // * Associations
    @OneToMany(mappedBy = "group_",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<GroupAssign> groupAssigns;
}
