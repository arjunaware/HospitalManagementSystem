package com.hospital.service;

import com.hospital.Repository.BillRepo;
import com.hospital.model.Bill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepo billRepo;

    Logger logger = LoggerFactory.getLogger(BillService.class);
    @Autowired
    private View error;

    public ResponseEntity<List<Bill>> getAllBill() {
        try {
            List<Bill> allBills = this.billRepo.findAll();
            return new ResponseEntity<>(allBills, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            logger.error("error occured when accessing all bills{}" + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    public ResponseEntity<Bill> addBill(Bill bill) {
        try {
            Bill savedBill = this.billRepo.save(bill);
            return new ResponseEntity<>(savedBill, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            logger.error("Error occured in bill adding time" + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

    }

    public ResponseEntity<Optional<Bill>> getBillById(Long id) {
        try {
            Optional<Bill> bill = this.billRepo.findById(id);
            if (bill.isPresent()) {
                return new ResponseEntity<>(bill, HttpStatus.OK);
            }

        } catch (Exception e) {
            System.out.println(e);
            logger.error("error occured in getbillby id method service " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity<String> deleteBillById(Long id) {
        try {
            Optional<Bill> bill = this.billRepo.findById(id);
            if (bill.isPresent()) {

                this.billRepo.deleteById(id);
                return new ResponseEntity<>("Bill deleted Successfully", HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e);
            logger.error("error occured delete Bt Id  service " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity<String> updateBill(Long id, Bill bill) {
        try {
            Optional<Bill> bill1 = this.billRepo.findById(id);
            if (bill1.isPresent()) {
                Bill bill2 = bill1.get();
                bill2.setPatientId(bill.getPatientId());
                bill2.setAmount(bill.getAmount());
                bill2.setStatus(bill.getStatus());
                Bill save = this.billRepo.save(bill2);
                System.out.println("Update Successfully " + save);
                return new ResponseEntity<>("Bill updated Successfully", HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e);
            logger.error("Error accured when updateing bill {}" + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
