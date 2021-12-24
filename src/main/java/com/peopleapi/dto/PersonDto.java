package com.peopleapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonDto {
    private Long id;

    private String adi;

    private String soyadi;

    private List<String> adresler;
}
