package com.cas.casdemo.casservis.dto.customer;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerGetResponseDTO {
    private Long id;
    private String pin;
    private Boolean resident;

    @JsonProperty("details")
    private CustomerDetailsDTO customerDetails;

}
