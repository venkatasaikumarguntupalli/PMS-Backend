package com.pm.patientservice.controller;


import com.pm.patientservice.DTO.PatientRequestDTO;
import com.pm.patientservice.DTO.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients(){
        return ResponseEntity.ok(patientService.getPatients());
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> addPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){
        return ResponseEntity.ok(patientService.createPatient(patientRequestDTO));
    }

    @GetMapping("/patient")
    public ResponseEntity<PatientResponseDTO> getPatientId(@Valid @RequestParam(name = "id", required = false) UUID id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }


}
