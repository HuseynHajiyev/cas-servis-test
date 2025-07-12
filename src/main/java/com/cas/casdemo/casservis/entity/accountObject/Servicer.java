package com.cas.casdemo.casservis.entity.accountObject;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicer {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String bic;
}

