package com.cas.casdemo.casservis.dto.alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliasPostRequestDTO {

    @NotBlank(message = "Alias type cannot be empty")
    private String type;

    @NotBlank(message = "Alias pin cannot be empty")
    private String pin;

    @NotNull(message = "Alias start date cannot be null")
    @Future(message  = "Activation date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Instant startDate;

    @NotNull(message = "Alias expiration date cannot be null")
    @Future(message = "Expiration date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Instant expirationDate;

    private String status;
}
