package com.example.hospital.domain.repository;

import com.example.hospital.domain.dto.VisitSummaryDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.security.Timestamp;
import java.time.ZoneId;
import java.util.List;

@Repository
public class VisitSummaryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<VisitSummaryDto> getVisitSummaries(Long patientId, Long doctorId) {
        String queryString = "SELECT v.doctor_id, v.patient_id, MAX(v.date), COUNT(*) " +
                "FROM visit v " +
                "WHERE v.patient_id = :patientId AND v.doctor_id = :doctorId " +
                "GROUP BY v.doctor_id, v.patient_id";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("patientId", patientId);
        query.setParameter("doctorId", doctorId);

        List<Object[]> results = query.getResultList();
        return results.stream().map(result -> new VisitSummaryDto(
                ((BigInteger) result[0]).longValue(),
                ((BigInteger) result[1]).longValue(),
                ((java.sql.Timestamp) result[2]), // Zmiana tutaj
                ((BigInteger) result[3]).longValue()
        )).toList();
    }
}



