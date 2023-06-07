package com.groupyfy.groupyfy.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GroupDto {
    Long id;
    String nom;
}
