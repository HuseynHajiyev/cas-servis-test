package com.cas.casdemo.casservis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pin;
    private Boolean isResident;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="details_id")
    private Details details;
}
