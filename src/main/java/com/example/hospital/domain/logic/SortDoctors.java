package com.example.hospital.domain.logic;

import com.example.hospital.domain.dto.DoctorDto;
import com.example.hospital.domain.enity.Doctor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
public class SortDoctors implements Comparator<DoctorDto> {
    @Override
    public int compare(DoctorDto o1,DoctorDto o2) {
        int resaoult;
        resaoult = o1.specialization().compareTo(o2.specialization());
        if(resaoult==0){
            resaoult = o1.firstName().compareTo(o2.firstName());
        }
        if(resaoult==0){
            resaoult = o1.lastName().compareTo(o2.lastName());
        }
        return resaoult;
    }
}
