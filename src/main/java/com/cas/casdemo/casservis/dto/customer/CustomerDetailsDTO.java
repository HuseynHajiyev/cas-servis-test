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
public class CustomerDetailsDTO {
    @NotEmpty
    private String name;
    @NotNull(message = "customer surname must be provided")
    @NotEmpty(message = "customer surname must not be blank")
    private String surname;
    @NotNull(message = "Phone number must not be null")
    @NotEmpty(message = "Phone number must not be blank")
    @Pattern(
            regexp  = "^\\+994(50|51|55|70|77)\\d{7}$",
            message = "Phone number must be +994XX followed by 7 digits"
    )
    private String phoneNumber;
}
