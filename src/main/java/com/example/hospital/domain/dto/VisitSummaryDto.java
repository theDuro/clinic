package com.example.hospital.domain.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record VisitSummaryDto(
        Long doctorId
        , Long patientId
        , Date date
        , Long visitCount
) {
}