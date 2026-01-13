package com.hospital.service;


import com.hospital.Repository.PatientRepo;

import com.hospital.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);


    public ResponseEntity<Page<Patient>> getAllPatient(int page, int size) {

        try {
            Pageable pageable= PageRequest.of(page,size);
            Page<Patient> allPatient = patientRepo.findAll(pageable);
            return new ResponseEntity<>(allPatient, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            logger.error("Error Occured When Fething THe Record {}" + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    public ResponseEntity<Patient> addPatient(Patient patient) {

        try {
            Patient savedPatient = this.patientRepo.save(patient);
            return new ResponseEntity<>(savedPatient, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            logger.error("Error Occured When Saved Patient {}" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    public ResponseEntity<Optional<Patient>> getPatientById(Long id) {

        Optional<Patient> patient = patientRepo.findById(id);
        try {
            if (patient.isPresent()) {
                Patient p = patient.get();
            } else {
                logger.warn("Error Occured When returning patient by its id ");
            }
        } catch (Exception e) {
            System.out.println(e);
            logger.warn("Error Occured When returning patient by its id in catch block " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }


    public ResponseEntity<String> deletePatient(Long id) {

        try {
            Optional<Patient> patient = patientRepo.findById(id);

            if (patient.isPresent()) {
                patientRepo.deleteById(id);
                logger.info("Patient deleted successfully with id {}", id);
                return ResponseEntity.ok("Deleted Successfully"); // 200
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Patient not found with id " + id); // 404
            }

        } catch (Exception e) {
            logger.error("Error occurred while deleting patient with id {}", id, e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while deleting patient"); // 500
        }
    }

    public ResponseEntity<String> updatePatient(Patient patient, Long id) {
        Optional<Patient> patient1 = this.patientRepo.findById(id);

        try {
            if (patient1.isPresent()) {
                Patient patient2 = patient1.get();
                patient2.setName(patient.getName());
                patient2.setGender(patient.getGender());
                patient2.setAge(patient.getAge());

                this.patientRepo.save(patient2);
                return new ResponseEntity<>("Patient Updated Successfully ", HttpStatus.CREATED);


            } else {
                logger.error("Errror Occured when saving patient that patient not exists");

            }
        } catch (Exception e) {
            System.out.println(e);
            logger.error("Error occured in catch block in update patient{}" + e.getMessage());


        }
        return new ResponseEntity<>("Patient not exists ", HttpStatus.BAD_REQUEST);
    }
}



