package com.example.hospital.domain.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

 class PeselAnalizer {//defaoult
    private final int PESEL_LENGHT = 11;
    private boolean isHaveCorectLenght(String pesel){
        return pesel.length()==PESEL_LENGHT;
    }
    static LocalDateTime parsePeselDateTime(String pesel) {
        if (pesel == null || pesel.length() != 11) {
            throw new IllegalArgumentException("PESEL must be a string of length 11.");
        }

        int year = Integer.parseInt(pesel.substring(0, 2));
        int month = Integer.parseInt(pesel.substring(2, 4));
        int day = Integer.parseInt(pesel.substring(4, 6));

        int century = year >= 0 && year <= 18 ? 2000  : (year >= 20 && year <= 99 ? 1900 : -1);

        if (century == -1) {
            throw new IllegalArgumentException("Invalid PESEL year.");
        }
        int fullYear = century + year;
        Month birthMonth = Month.of(month);
        LocalDate birthDate = LocalDate.of(fullYear, birthMonth, day);
        return birthDate.atStartOfDay();
    }
    }


