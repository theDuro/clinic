package com.example.hospital.contlorel;

import com.example.hospital.domain.dto.*;
import com.example.hospital.domain.enity.Doctor;
import com.example.hospital.domain.enity.Patient;
import com.example.hospital.domain.enity.Visit;
import com.example.hospital.domain.repository.DoctorRepository;
import com.example.hospital.domain.repository.PatientRepository;
import com.example.hospital.domain.repository.VisitRepository;
import com.example.hospital.domain.repository.VisitSummaryRepository;
import com.example.hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HospitalController {
    private final HospitalService hospitalService;


    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;

    }

    @GetMapping("/patients")
    public List<PatientDto> getAllPatients() {
        return hospitalService.getAllPatient();
    }

    @GetMapping("/spec/{spec}")
    public List<DoctorDto> getDoctorBySpecialisation(@PathVariable("spec") Specialization special ){
        return hospitalService.findBySpecialisation(special);

    }



    @GetMapping("/doctors")
    public List<DoctorDto> getAllDoctors() {
        return hospitalService.getAllDoctors();
    }

    @GetMapping("/visits")
    public List<VisitDto> getAllVisits() {
        return hospitalService.getAllVisits();
    }

    @GetMapping("/actual/{patientId}")
    public List<VisitDto> getAllActuallPatientVisits(@PathVariable("patientId")Long patientId) {
        return hospitalService.getAllActualVisits(patientId);
    }
    @GetMapping("/patients/adult")
    public List<PatientDto> getAllAdultPatients() {
        return hospitalService.getAllAdultPatients();
    }

    @GetMapping("/summary/{patientId}/{doctorId}")
    public List<VisitSummaryDto> getSummaryVisits(
            @PathVariable("patientId") Long patientId,
            @PathVariable("doctorId") Long doctorId
    ) {
        return hospitalService.getSummaryVisits(patientId, doctorId);
    }
    @GetMapping("/patients/sort")
    public List<PatientDto> getAllSortedPatients() {
        return hospitalService.getSortedPatient();
    }
    @GetMapping("/doctors/sorted")
    public List<DoctorDto> getAllSordetDoctors() {
        return hospitalService.getASortedDoctors();
    }

    @PostMapping("/add-patient")
    public ResponseEntity<Patient> addPatient(@RequestBody PatientDto patientDto) {
        Patient savedPatient = hospitalService.addPatient(patientDto);
        return ResponseEntity.ok(savedPatient);
    }

    @PostMapping("/add-doctor")
    public ResponseEntity<Doctor> addDoctor(@RequestBody DoctorDto doctorDto) {
        Doctor savedDoctor = hospitalService.addDoctor(doctorDto);
        return ResponseEntity.ok(savedDoctor);
    }

    @PostMapping("/add-visit")
    public ResponseEntity<Visit> addVisit(@RequestBody VisitDto visitDto) {
        Visit savedVisit = hospitalService.addVisit(visitDto);
        return ResponseEntity.ok(savedVisit);
    }
    @DeleteMapping("delte-doctor/{id}")
    public void delteDoctor(@PathVariable("id") Long id){
        hospitalService.delteDoctor(id);
    }
    @DeleteMapping("delte-patient/{id}")
    public void deltePatient(@PathVariable("id") Long id){
        hospitalService.deltePatient(id);
    }
    @DeleteMapping("delte-visit/{id}")
    public void delteVisit(@PathVariable("id") Long id){
        hospitalService.delteVisit(id);
    }





}

