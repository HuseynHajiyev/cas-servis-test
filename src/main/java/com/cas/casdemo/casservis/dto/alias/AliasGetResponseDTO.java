package com.cas.casdemo.casservis.dto.alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliasGetResponseDTO {
    private Long id;
    private String type;
    private String pin;
    private String startDate;
    private String expirationDate;
    private String status;
}
