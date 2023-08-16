package com.example.hospital.domain.logic;

import com.example.hospital.domain.dto.PatientDto;

import java.time.LocalDateTime;
import java.util.List;

public class PacietByAgeFilter {
    private static final int ADULT_AGE = 18;
    public static List<PatientDto> firltrAdultPatients(List<PatientDto> patients){
        return patients.stream()
                .filter(p ->  PeselAnalizer.parsePeselDateTime(p.pesel())
                        .isBefore(LocalDateTime.now().minusYears(ADULT_AGE)
                        ))
                .toList();

    }
}
