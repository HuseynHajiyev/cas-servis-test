package com.cas.casdemo.casservis.dto.alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AliasPutRequestDTO {

    @Size(min = 1, message = "Alias type must not be empty")
    private String type;

    @Size(min = 1, message = "Alias pin must not be empty")
    private String pin;

    @Future(message = "Activation date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Instant startDate;

    @Future(message = "Expiration date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Instant expirationDate;

    @Pattern(
            regexp   = "^\\s*\\S.{0,}\\S\\s*$",
            message  = "status must be at least 2 non-blank characters"
    )
    private String status;
}
