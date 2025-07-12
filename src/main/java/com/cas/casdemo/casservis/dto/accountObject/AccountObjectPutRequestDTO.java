package com.cas.casdemo.casservis.dto.accountObject;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountObjectPutRequestDTO {
    private AccountPutRequestDTO account;
    private String type;
    private String currency;
    @JsonProperty("details")
    private AccountObjectDetailsPutRequestDTO accountObjectDetails;
    private ServicerDTO servicer;
}

