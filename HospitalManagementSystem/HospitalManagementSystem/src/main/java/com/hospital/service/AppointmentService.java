package com.hospital.service;


import com.hospital.Repository.AppointmentRepo;
import com.hospital.model.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    public ResponseEntity<List<Appointment>> getAllAppointement() {
        try {
            List<Appointment> allAppointements = this.appointmentRepo.findAll();
            return new ResponseEntity<>(allAppointements, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

    public ResponseEntity<Optional<Appointment>> getAppointmentById(Long id) {
        try {
            Optional<Appointment> appid = this.appointmentRepo.findById(id);
            if (appid.isPresent()) {
                return new ResponseEntity<>(appid, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e);
            logger.error("error occured when get Appointment by id " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity<Appointment> addAppointement(Appointment appo) {
        try {
            Appointment savedappo = this.appointmentRepo.save(appo);
            return new ResponseEntity<>(savedappo, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            logger.error("error occured in add appintement method " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    public ResponseEntity<String> deleteAppo(Long id) {
        try {
            Optional<Appointment> appointement = this.appointmentRepo.findById(id);
            if (appointement.isPresent()) {
                this.appointmentRepo.deleteById(id);
                return new ResponseEntity<>("Appointement Deleted Successfully !!", HttpStatus.OK);
            }

        } catch (Exception e) {
            System.out.println(e);
            logger.error("error occured in delete service method " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }


    public ResponseEntity<String> updateAppo(Appointment appointment, Long id) {
        try {
            Optional<Appointment> appo = this.appointmentRepo.findById(id);
            if (appo.isPresent()) {
                Appointment appointment1 = appo.get();


                appointment1.setPatientId(appointment.getPatientId());
                appointment1.setDoctorId(appointment.getDoctorId());
                appointment1.setDate(appointment.getDate());
                this.appointmentRepo.save(appointment1);
                return new ResponseEntity<>("Appointement Updated Successfully", HttpStatus.OK);

            }
        } catch (Exception e) {
            System.out.println(e);
            logger.error("error come in update service method" + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}

