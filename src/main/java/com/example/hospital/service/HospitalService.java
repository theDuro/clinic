package com.example.hospital.service;

import com.example.hospital.domain.dto.*;
import com.example.hospital.domain.enity.Doctor;
import com.example.hospital.domain.enity.Patient;
import com.example.hospital.domain.enity.Visit;
import com.example.hospital.domain.logic.PacietByAgeFilter;
import com.example.hospital.domain.logic.SortDoctors;
import com.example.hospital.domain.logic.SortPatiernts;
import com.example.hospital.domain.mapper.DoctorMapper;
import com.example.hospital.domain.mapper.PatientMapper;
import com.example.hospital.domain.mapper.VisitMapper;
import com.example.hospital.domain.repository.DoctorRepository;
import com.example.hospital.domain.repository.PatientRepository;
import com.example.hospital.domain.repository.VisitRepository;
import com.example.hospital.domain.repository.VisitSummaryRepository;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class HospitalService {
    private static final String CHASCHE_DOCTORS ="doctors";
    private static final String CHASHE_PATIENTS = "patients";
    private static final String CHASHE_SPEC_DOCTORS = "spec_doctors";
    private static final String CHASHE_VISITS ="visits";

    private static final String CHASHE_ACTUAL_VISITS = "actual-visits";
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private  final VisitRepository visitRepository;
    private final VisitSummaryRepository visitSummaryRepository;
    private final SortDoctors sortDoctors;
    private final SortPatiernts sortPatiernts;
    @Autowired
    public HospitalService(DoctorRepository doctorRepository
            , PatientRepository patientRepository
            , VisitRepository visitRepository
            , VisitSummaryRepository visitSummaryRepository
            , SortDoctors sortDoctors
            , SortPatiernts sortPatiernts
    ) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
        this.visitSummaryRepository = visitSummaryRepository;
        this.sortDoctors = sortDoctors;
        this.sortPatiernts = sortPatiernts;
    }
    @Cacheable(value = CHASHE_PATIENTS)
    public List<PatientDto> getAllPatient(){
        List<Patient> patients = patientRepository.findAll();
        return PatientMapper.toPatientDtoList(patients);
    }
    @Cacheable(CHASCHE_DOCTORS)
    public List<DoctorDto> getAllDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();
        return DoctorMapper.toDoctorDtoList(doctors);
    }
    @Cacheable(CHASHE_VISITS)
    public List<VisitDto> getAllVisits(){//get
        List<Visit> visitList =  visitRepository.findAll();
        return VisitMapper.toVisitDtoList(visitList);
    }
    public List<PatientDto> getSortedPatient(){
        List<PatientDto> patientDtos =  new ArrayList<>(getAllPatient());
        Collections.sort(patientDtos,sortPatiernts);
        return patientDtos;
    }
    public List<DoctorDto> getASortedDoctors(){
        List<DoctorDto> doctors = new ArrayList<>(getAllDoctors());
        Collections.sort(doctors,sortDoctors);
        return doctors;
    }
    @Cacheable(CHASHE_ACTUAL_VISITS)//todo need timeout
    public List<VisitDto> getAllActualVisits(Long patientId){
        List<Visit> visitList =  visitRepository.findByPatientIdAndDateAfter(patientId, LocalDateTime.now());// LocalDateTime.now()?
        return VisitMapper.toVisitDtoList(visitList);
    }
    public  List<PatientDto> getAllAdultPatients(){
        List<PatientDto> patients = getAllPatient();
        return PacietByAgeFilter.firltrAdultPatients(patients);
    }
    public List<VisitSummaryDto> getSummaryVisits(Long patientId,Long doctorId){
        return  visitSummaryRepository.getVisitSummaries(patientId,doctorId);
    }
    @Cacheable(CHASHE_SPEC_DOCTORS)
    public List<DoctorDto> findBySpecialisation(Specialization specialization){
        List<Doctor> doctors = doctorRepository.findAllBySpecialization(specialization);
        return DoctorMapper.toDoctorDtoList(doctors);
    }
    @CacheEvict(value = CHASHE_PATIENTS)
    public Patient addPatient(PatientDto patientDto){
        return patientRepository.save(PatientMapper.toPatient(patientDto));
    }
    @CacheEvict({CHASCHE_DOCTORS,CHASHE_SPEC_DOCTORS})
    public Doctor addDoctor(DoctorDto doctorDto){
        return  doctorRepository.save(DoctorMapper.toDoctor(doctorDto));
    }
    @CacheEvict({CHASHE_VISITS,CHASHE_ACTUAL_VISITS})
    public Visit addVisit(VisitDto visitDto){
        return  visitRepository.save(VisitMapper.toVisit(visitDto));
    }
    @CacheEvict({CHASCHE_DOCTORS,CHASHE_SPEC_DOCTORS})
    public void delteDoctor(Long doctorId){
        doctorRepository.deleteById(doctorId);
    }
    @CacheEvict(CHASHE_PATIENTS)
    public void deltePatient(Long patientId){
        patientRepository.deleteById(patientId);
    }
    @CacheEvict({CHASHE_VISITS,CHASHE_ACTUAL_VISITS})
    public void delteVisit(Long visitId){
        visitRepository.deleteById(visitId);
    }

}
