package com.hospital.controller;


import com.hospital.model.Patient;
import com.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @GetMapping("/patients")
    public ResponseEntity<Page<Patient>> getAllPatients(
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size)
    {

        return patientService.getAllPatient(page, size);
    }



    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient)
    {
        return this.patientService.addPatient(patient);

    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Patient>> getPatientById(@PathVariable("id") Long id)
    {
        return this.patientService.getPatientById(id);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id")Long id)
    {
        return this.patientService.deletePatient(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRecord(@RequestBody Patient patient, @PathVariable Long id)
    {
        return this.patientService.updatePatient(patient,id);


    }
}




