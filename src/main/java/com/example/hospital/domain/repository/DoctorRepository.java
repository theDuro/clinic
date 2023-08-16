package com.example.hospital.domain.repository;

import com.example.hospital.domain.dto.Specialization;
import com.example.hospital.domain.enity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    @Query("SELECT d FROM Doctor d WHERE d.specialization = :specialization")
    List<Doctor> findAllBySpecialization(@Param("specialization") Specialization specialization);



}
