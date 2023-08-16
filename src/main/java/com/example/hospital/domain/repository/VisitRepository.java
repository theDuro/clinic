package com.example.hospital.domain.repository;

import com.example.hospital.domain.enity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Long> {
    List<Visit> findByPatientIdAndDateAfter(Long patientId, LocalDateTime currentDateTime);
}
