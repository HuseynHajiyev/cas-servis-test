package com.cas.casdemo.casservis.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPutRequestDTO {
    @Size(min=7, max=7, message="invalid pin length")
    private String pin;

    @AssertTrue(message="isResident must be true")
    private Boolean resident;

    @JsonProperty("details")
    private CustomerDetailsDTO customerDetails;
}