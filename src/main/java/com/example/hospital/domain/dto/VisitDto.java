package com.example.hospital.domain.dto;

import com.example.hospital.domain.enity.Doctor;
import com.example.hospital.domain.enity.Patient;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record VisitDto(Long id
        , LocalDateTime date
        , Patient patient
        , Doctor doctor) {
}
