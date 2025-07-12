package com.cas.casdemo.casservis.dto.accountObject;

import com.cas.casdemo.casservis.enums.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountObjectGetResponseDTO {
    private Long id;
    private AccountDTO account;
    private String type;
    private Currency currency;
    private AccountObjectDetailsDTO accountObjectDetails;
    private ServicerDTO servicer;
}

