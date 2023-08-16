package com.example.hospital.domain.logic;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeselAnalizerTest {
   @Test
    public void dataFromPeselV1(){
        String pesel = "98070607652";
        LocalDateTime corectAge =LocalDateTime.of(1998, Month.JULY, 6, 0, 0);
        LocalDateTime localDateTime = PeselAnalizer.parsePeselDateTime(pesel);
        assertEquals(corectAge,localDateTime);
  }
    @Test
    public void dataFromPeselV2(){
        String pesel = "28052107652";
        LocalDateTime corectAge =LocalDateTime.of(1928, Month.MAY, 21, 0, 0);
        LocalDateTime localDateTime = PeselAnalizer.parsePeselDateTime(pesel);
        assertEquals(corectAge,localDateTime);
    }
    @Test
    public void dataFromPeselV3(){
        String pesel = "08010107652";
        LocalDateTime corectAge =LocalDateTime.of(2008, Month.JANUARY, 1, 0, 0);
        LocalDateTime localDateTime = PeselAnalizer.parsePeselDateTime(pesel);
        assertEquals(corectAge,localDateTime);
    }
}
