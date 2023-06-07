package com.groupyfy.groupyfy.model;


import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "contact_")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {

    @Id
    @SequenceGenerator(name = "contact_id_seq",sequenceName = "contact_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "contact_id_seq")
    private Long id;

    @NotBlank(message = "Nom Vide !")
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotBlank(message = "Prenom Vide !")
    @Column(name = "prenom",nullable = false)
    private String prenom;

    @Pattern(regexp = "\\+212-6\\d{2}-\\d{3}-\\d{3}", message = "Telephone Personnel format non Valid !")
    @Column(name = "telephone_personnel",nullable = false)
    private String telephonePersonnel;
    @Pattern(regexp = "\\+212-5\\d{2}-\\d{3}-\\d{3}", message = "Telephone Professionnel format non Valid !")
    @Column(name = "telephone_professionnel",nullable = false)
    private String telephoneProfessionnel;

    @NotBlank(message = "Adreese Vide !")
    @Column(name = "adresse",nullable = false)
    private String adresse;

    @NotBlank(message = "Email Personnel Vide !")
    @Email(message = "Invalid personnal email format. Please provide a valid email address.")
    @Column(name = "email_personnel",nullable = false)
    private String emailPersonnel;

    @NotBlank(message = "Email Professionnel Vide !")
    @Email(message = "Invalid professionnel email format. Please provide a valid email address.")
    @Column(name = "email_professionnel",nullable = false)
    private String emailProfessionnel;

    @NotBlank(message = "Genre Vide !")
    @Pattern(regexp = "^(Male|Female)$", message = "Invalid value. Only 'Male' and 'Female' are allowed.")
    @Column(name = "genre",nullable = false)
    private String genre;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
    }

    @Transient
    private String groupName;

    public Contact(String nom, String prenom, String telephonePersonnel, String telephoneProfessionnel, String adresse, String emailPersonnel, String emailProfessionnel, String genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephonePersonnel = telephonePersonnel;
        this.telephoneProfessionnel = telephoneProfessionnel;
        this.adresse = adresse;
        this.emailPersonnel = emailPersonnel;
        this.emailProfessionnel = emailProfessionnel;
        this.genre = genre;
    }

    // * Association
    @OneToMany(mappedBy = "contact_",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<GroupAssign> groupAssigns;



}
