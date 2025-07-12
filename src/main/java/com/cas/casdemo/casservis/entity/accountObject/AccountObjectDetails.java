package com.cas.casdemo.casservis.entity.accountObject;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountObjectDetails {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String authMethod;
}

