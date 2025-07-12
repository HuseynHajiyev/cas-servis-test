package com.cas.casdemo.casservis.controller;

import com.cas.casdemo.casservis.dto.accountObject.AccountObjectGetResponseDTO;
import com.cas.casdemo.casservis.dto.accountObject.AccountObjectPostRequestDTO;
import com.cas.casdemo.casservis.dto.accountObject.AccountObjectPutRequestDTO;
import com.cas.casdemo.casservis.service.accountObject.AccountObjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers/{customerId}/accounts")
@Validated
public class AccountObjectController {
    private final AccountObjectService accountObjectService;
    public AccountObjectController(AccountObjectService accountObjectService) {
        this.accountObjectService = accountObjectService;
    }

    @PostMapping(path = { "", "/" })
    public ResponseEntity<?> create(
            @PathVariable Long customerId,
            @Valid @RequestBody AccountObjectPostRequestDTO dto
    ) {
        Long id = accountObjectService.save(dto, customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountObjectGetResponseDTO> update(
            @PathVariable Long id,
            @PathVariable Long customerId,
            @Valid @RequestBody AccountObjectPutRequestDTO dto
    ) {
        AccountObjectGetResponseDTO updatedEntity = accountObjectService.update(id, customerId ,dto);
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id,
            @PathVariable Long customerId
    ) {
            accountObjectService.deleteById(id);
            return ResponseEntity.noContent().build();
    }


    @GetMapping(path = { "", "/" })
    public ResponseEntity<List<AccountObjectGetResponseDTO>> list(@PathVariable Long customerId) {
        List<AccountObjectGetResponseDTO> all = accountObjectService.findAllByCustomerId(customerId);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountObjectGetResponseDTO> getOne(
            @PathVariable Long id,
            @PathVariable Long customerId
    ) {
        AccountObjectGetResponseDTO dto = accountObjectService.findByIdAndCustomerId(id, customerId);
        return ResponseEntity.ok(dto);
    }
}
