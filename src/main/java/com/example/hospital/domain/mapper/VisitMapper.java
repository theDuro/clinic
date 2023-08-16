package com.example.hospital.domain.mapper;

import com.example.hospital.domain.dto.PatientDto;
import com.example.hospital.domain.dto.VisitDto;
import com.example.hospital.domain.enity.Patient;
import com.example.hospital.domain.enity.Visit;

import java.util.List;

public class VisitMapper {
    public static Visit toVisit(VisitDto visitDto){
        return Visit.builder()
                .date(visitDto.date())
                .patient(visitDto.patient())
                .doctor(visitDto.doctor())
                .build();
    }
    public static VisitDto toVisitDto(Visit visit){
        return VisitDto.builder()
                .id(visit.getId())
                .date(visit.getDate())
                .doctor(visit.getDoctor())
                .patient(visit.getPatient())
                .build();
    }
    public static List<Visit> toVisistList(List<VisitDto> visitDtoList){
        return visitDtoList.stream()
                .map(VisitMapper::toVisit)
                .toList();
    }
    public static List<VisitDto> toVisitDtoList(List<Visit> visitList){
        return visitList.stream()
                .map(VisitMapper::toVisitDto)
                .toList();
    }

}
