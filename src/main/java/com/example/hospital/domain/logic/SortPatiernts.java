package com.example.hospital.domain.logic;

import com.example.hospital.domain.dto.PatientDto;
import com.example.hospital.domain.enity.Patient;

import java.util.Comparator;

public class SortPatiernts implements  Comparator<PatientDto> {
    @Override
    public int compare(PatientDto o1, PatientDto o2) {
        int resaoult;
        resaoult = o1.residence().compareTo(o2.residence());
        if(resaoult==0){
            resaoult = o1.residence().compareTo(o2.residence());
        }
        if(resaoult==0){
            resaoult = o1.residence().compareTo(o2.residence());
        }
        return resaoult;
    }
}

