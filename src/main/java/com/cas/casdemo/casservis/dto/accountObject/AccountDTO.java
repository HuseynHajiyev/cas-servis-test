package com.cas.casdemo.casservis.dto.accountObject;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    @NotNull(message = "IBAN must be provided")
    @NotBlank(message = "IBAN must not be blank")
    @Pattern(
            regexp  = "^[A-Z]{2}\\d{2}[A-Z0-9]{1,30}$",
            message = "must be a valid IBAN"
    )
    private String iban;
}

