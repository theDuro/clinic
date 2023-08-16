package com.example.hospital.domain.mapper;

import com.example.hospital.domain.dto.DoctorDto;
import com.example.hospital.domain.enity.Doctor;
import org.springframework.stereotype.Component;

import java.util.List;
public class DoctorMapper {
    public static Doctor toDoctor(DoctorDto dto){
        return Doctor.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .specialization(dto.specialization())
                .visits(dto.visit())
                .build();
    }
    public static DoctorDto toDoctorDto(Doctor entity){
        return DoctorDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .specialization(entity.getSpecialization())
                .visit(entity.getVisits())
                .build();
    }
    public static List<Doctor> toListDoctors(List<DoctorDto> listDto){
        return listDto.stream()
                .map(DoctorMapper::toDoctor)
                .toList();
    }
    public static List<DoctorDto> toDoctorDtoList(List<Doctor> listEntity){
        return listEntity.stream()
                .map(DoctorMapper::toDoctorDto)
                .toList();
    }

}
