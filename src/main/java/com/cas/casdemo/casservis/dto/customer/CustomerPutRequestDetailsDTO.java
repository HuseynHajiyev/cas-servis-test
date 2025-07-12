package com.cas.casdemo.casservis.dto.customer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPutRequestDetailsDTO {
    @Pattern(
            regexp   = "^\\s*\\S.{0,}\\S\\s*$",
            message  = "name must be at least 2 non-blank characters"
    )
    private String name;
    @Pattern(
            regexp   = "^\\s*\\S.{0,}\\S\\s*$",
            message  = "surname must be at least 2 non-blank characters"
    )
    private String surname;

    @Pattern(
            regexp = "^\\+994(50|51|55|70|77)\\s\\d{3}\\s\\d{2}\\s\\d{2}$",
            message = "Phone number must be in the following format +994XX XXX XX XX"
    )
    private String phoneNumber;
}
