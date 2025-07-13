package com.cas.casdemo.casservis.entity.customer;

import com.cas.casdemo.casservis.entity.accountObject.AccountObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String pin;
    private Boolean active;
    private Boolean resident;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="details_id")
    private CustomerDetails customerDetails;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AccountObject> accountObjects;
}
