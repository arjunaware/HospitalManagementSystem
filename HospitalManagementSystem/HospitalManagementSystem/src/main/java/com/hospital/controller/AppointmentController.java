package com.hospital.controller;


import com.hospital.model.Appointment;
import com.hospital.model.Bill;
import com.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appo")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("/getAllBill")
    public ResponseEntity<List<Appointment>> getAllAppintment()
    {
        return this.appointmentService.getAllAppointement();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Appointment>> getAppointmentById(@PathVariable Long id)
    {
       return this.appointmentService.getAppointmentById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appo)
    {
       return this.appointmentService.addAppointement(appo);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppo(@PathVariable Long id)
    {
       return this.appointmentService.deleteAppo(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAppo(@RequestBody Appointment appointment, @PathVariable("id") Long id)
    {
        return this.appointmentService.updateAppo(appointment,id);
    }




}
