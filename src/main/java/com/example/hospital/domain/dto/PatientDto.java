package com.example.hospital.domain.dto;

import com.example.hospital.domain.enity.Visit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import java.util.List;
@Builder
public record PatientDto(Long id
        ,String firstName
        ,String lastName
        ,String pesel
        ,String residence
        ,@JsonIgnore List<Visit>visit) {
}
