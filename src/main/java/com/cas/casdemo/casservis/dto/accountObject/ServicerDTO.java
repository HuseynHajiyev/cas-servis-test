package com.cas.casdemo.casservis.dto.accountObject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicerDTO {
    @NotBlank(message = "BIC must be provided")
    @Pattern(
            regexp = "^[A-Z]{6}[A-Z0-9]{2}([A-Z0-9]{3})?$",
            message = "must be a valid BIC"
    )
    private String bic;
}

