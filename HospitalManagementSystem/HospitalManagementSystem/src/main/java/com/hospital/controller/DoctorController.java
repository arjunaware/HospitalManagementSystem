package com.hospital.controller;


import com.hospital.model.Doctor;
import com.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("allDoctor")
    public ResponseEntity<List<Doctor>> getAllDoctor()
    {
        return this.doctorService.getAllDoctor();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("id") Long id)
    {
        return this.doctorService.getDoctorById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor)
    {
       return  this.doctorService.addDoctor(doctor);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRecord(@PathVariable("id") Long id,@RequestBody Doctor doctor)
    {
        return this.doctorService.updateDoctor(id,doctor);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable("id") Long id)
    {
        return  this.doctorService.deteteDoctor(id);
    }


}
