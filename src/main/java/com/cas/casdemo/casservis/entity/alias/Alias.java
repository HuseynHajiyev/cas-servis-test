package com.cas.casdemo.casservis.entity.alias;

import com.cas.casdemo.casservis.entity.accountObject.AccountObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Alias {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;
    private String pin;
    private String startDate;
    private String expirationDate;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_object_id")
    private AccountObject accountObject;
}
