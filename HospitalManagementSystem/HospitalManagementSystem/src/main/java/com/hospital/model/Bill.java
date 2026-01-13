package com.hospital.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bill {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private long patientId;
        private String amount;
        private String status;
    }

