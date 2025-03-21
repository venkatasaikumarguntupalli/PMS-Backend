package com.pm.patientservice.service;


import com.pm.patientservice.DTO.PatientRequestDTO;
import com.pm.patientservice.DTO.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(patient -> PatientMapper.toDTO(patient)).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        Patient newPatient = patientRepository.save(PatientMapper.toEntity(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO getPatientById(UUID id){
        return patientRepository.findById(id)
                .map(PatientMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }

}
