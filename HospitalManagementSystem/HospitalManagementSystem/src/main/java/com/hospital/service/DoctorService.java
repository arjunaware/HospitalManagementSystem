package com.hospital.service;

import com.hospital.Repository.DoctorRepo;
import com.hospital.model.Doctor;
import org.hibernate.collection.spi.PersistentBag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;


    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);

    public ResponseEntity<List<Doctor>> getAllDoctor() {
        try {
            List<Doctor> allDoctor = this.doctorRepo.findAll();
            return new ResponseEntity<>(allDoctor, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Doctor> getDoctorById(Long id) {
        try {
            Optional<Doctor> doctorbyid = this.doctorRepo.findById(id);
            Doctor doctor = doctorbyid.get();
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    public ResponseEntity<Doctor> addDoctor(Doctor doctor) {
        try {
            this.doctorRepo.save(doctor);
            return new ResponseEntity<>(doctor, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }


    }

    public ResponseEntity<String> updateDoctor(Long id, Doctor doctor) {
        try {
            Optional<Doctor> doctorbyid = this.doctorRepo.findById(id);
            if (doctorbyid.isPresent()) {
                Doctor doctor1 = doctorbyid.get();
                doctor1.setName(doctor.getName());
                doctor1.setSpeciality(doctor.getSpeciality());
                this.doctorRepo.save(doctor1);
                return new ResponseEntity<>("Record Updated Successfully !!", HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            System.out.println(e);
            logger.error("somenting went wrong in update Doctor");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

    public ResponseEntity<String> deteteDoctor(Long id) {

        try {
            Optional<Doctor> doctor = this.doctorRepo.findById(id);

            if (doctor.isPresent()) {
                this.doctorRepo.deleteById(id);
                return new ResponseEntity<>("Doctor Deleted Successfully", HttpStatus.OK);
            } else {
                logger.warn("dont give id which bot present for delete");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            }

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
