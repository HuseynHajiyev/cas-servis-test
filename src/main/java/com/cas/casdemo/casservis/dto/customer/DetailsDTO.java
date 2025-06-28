package com.cas.casdemo.casservis.dto.customer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailsDTO {
    private String name;
    private String surname;
    private String phoneNumber;
}
