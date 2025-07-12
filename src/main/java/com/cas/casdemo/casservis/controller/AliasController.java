package com.cas.casdemo.casservis.controller;

import com.cas.casdemo.casservis.dto.alias.AliasGetResponseDTO;
import com.cas.casdemo.casservis.dto.alias.AliasPostRequestDTO;
import com.cas.casdemo.casservis.dto.alias.AliasPutRequestDTO;
import com.cas.casdemo.casservis.service.alias.AliasService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers/{customerId}/accounts/{accountObjectId}/aliases")
@Validated
public class AliasController {
    private final AliasService aliasService;
    public AliasController(AliasService aliasService) {
        this.aliasService = aliasService;
    }

    @PostMapping(path = { "", "/" })
    public ResponseEntity<Long> create(
            @PathVariable Long customerId,
            @PathVariable Long accountObjectId,
            @Valid  @RequestBody AliasPostRequestDTO dto
    ) {
        Long id = aliasService.save(accountObjectId, dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).body(id);
    }

    @GetMapping(path = { "", "/" })
    public ResponseEntity<?> list(
            @PathVariable Long customerId,
            @PathVariable Long accountObjectId
    ) {
        List<AliasGetResponseDTO> all = aliasService.findAllByAccountObject(accountObjectId);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(
            @PathVariable Long customerId,
            @PathVariable Long accountObjectId,
            @PathVariable Long id
    ) {
        AliasGetResponseDTO dto = aliasService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long customerId,
            @PathVariable Long accountObjectId,
            @PathVariable Long id,
            @Valid @RequestBody AliasPutRequestDTO dto
    ) {
            AliasGetResponseDTO aliasDTO = aliasService.update(id, dto);
            return ResponseEntity.ok(aliasDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long customerId,
            @PathVariable Long accountObjectId,
            @PathVariable Long id
    ) {
            aliasService.deleteById(id);
            return ResponseEntity.noContent().build();
    }


}
