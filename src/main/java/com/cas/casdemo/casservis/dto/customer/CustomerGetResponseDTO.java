package com.cas.casdemo.casservis.dto.customer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerGetResponseDTO {
    private String pin;
    private Boolean isResident;
    private DetailsDTO details;
}
