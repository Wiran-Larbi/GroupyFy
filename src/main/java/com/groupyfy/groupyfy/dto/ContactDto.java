package com.groupyfy.groupyfy.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContactDto {
    Long id;
    String nom;
    String prenom;
    String telephone_personnel;
    String telephone_professionnel;
    String adresse;
    String email_personnel;
    String email_professionnel;
    String genre;
}
