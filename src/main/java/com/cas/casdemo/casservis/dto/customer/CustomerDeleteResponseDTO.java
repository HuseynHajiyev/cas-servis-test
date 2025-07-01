package com.cas.casdemo.casservis.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDeleteResponseDTO {
    private String pin;
    private Boolean isResident;
    private DetailsDTO details;
}
