package com.cas.casdemo.casservis.entity.accountObject;

import com.cas.casdemo.casservis.entity.accountObject.Account;
import com.cas.casdemo.casservis.entity.accountObject.AccountObjectDetails;
import com.cas.casdemo.casservis.entity.accountObject.Servicer;
import com.cas.casdemo.casservis.entity.customer.Customer;
import com.cas.casdemo.casservis.entity.alias.Alias;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountObject {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;
    private String currency;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "servicer_id")
    private Servicer servicer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private AccountObjectDetails accountObjectDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "accountObject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alias> aliases;
}
