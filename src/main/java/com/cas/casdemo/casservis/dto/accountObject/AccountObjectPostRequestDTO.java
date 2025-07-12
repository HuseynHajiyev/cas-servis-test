package com.cas.casdemo.casservis.dto.accountObject;


import com.cas.casdemo.casservis.enums.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountObjectPostRequestDTO {

    @NotNull(message = "Account must be provided")
    @Valid
    private AccountDTO account;

    @NotBlank(message = "type must be provided")
    private String type;

    @NotNull(message = "currency must be provided")
    private Currency currency;

    @JsonProperty("details")
    @NotNull(message = "details must be provided")
    @Valid
    private AccountObjectDetailsDTO details;

    @NotNull(message = "servicer must be provided")
    @Valid
    private ServicerDTO servicer;
}
