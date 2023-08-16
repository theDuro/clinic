package com.example.hospital.domain.mapper;

import com.example.hospital.domain.dto.PatientDto;
import com.example.hospital.domain.enity.Patient;

import java.util.List;

public class PatientMapper {
    public static Patient toPatient(PatientDto dto){
        return Patient.builder()
                //.id(dto.id()) no need id
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .pesel(dto.pesel())
                .residence(dto.residence())
                .visit(dto.visit())
                .build();
    }
    public static  PatientDto toPatientDto(Patient entity){
        return PatientDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .pesel(entity.getPesel())
                .residence(entity.getResidence())
                .visit(entity.getVisit())
                .build();
    }
    public static List<Patient> toPaientList(List<PatientDto> patientDtoList){
        return patientDtoList.stream()
                .map(PatientMapper::toPatient)
                .toList();
    }
    public static List<PatientDto> toPatientDtoList(List<Patient> patientList){
        return patientList.stream()
                .map(PatientMapper::toPatientDto)
                .toList();
    }
}
