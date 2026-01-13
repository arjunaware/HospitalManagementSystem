package com.hospital.controller;


import com.hospital.model.Bill;
import com.hospital.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;


    @GetMapping("/getAllBill")
    public ResponseEntity<List<Bill>> getAllBill()
    {
        return  this.billService.getAllBill();
    }

    @PostMapping("/add")
    public ResponseEntity<Bill> addBill(@RequestBody Bill bill)
    {
        return this.billService.addBill(bill);
    }


    @GetMapping("getBill/{id}")
    public ResponseEntity<Optional<Bill>> getBillById(@PathVariable Long id)
    {
      return this.billService.getBillById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id)
    {
        return this.billService.deleteBillById(id);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Bill bill)
    {
       return  this.billService.updateBill(id,bill);
    }





}
