package com.hospital.Repository;

import com.hospital.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BillRepo extends JpaRepository<Bill,Long> {
}
