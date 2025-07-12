package com.cas.casdemo.casservis.controller;

import com.cas.casdemo.casservis.dto.customer.CustomerGetResponseDTO;
import com.cas.casdemo.casservis.dto.customer.CustomerPostRequestDTO;
import com.cas.casdemo.casservis.dto.customer.CustomerPutRequestDTO;
import com.cas.casdemo.casservis.service.customer.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = { "", "/" })
    public ResponseEntity<Long> create(@Valid @RequestBody CustomerPostRequestDTO dto) {
        Long id = customerService.save(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).body(id);
    }

    @GetMapping(path = { "", "/" })
    public ResponseEntity<List<CustomerGetResponseDTO>> list() {
        List<CustomerGetResponseDTO> all = customerService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerGetResponseDTO> getOne(@PathVariable Long id) {
            CustomerGetResponseDTO dto = customerService.findById(id);
            return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerGetResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerPutRequestDTO dto) {
            CustomerGetResponseDTO updatedEntity = customerService.update(id, dto);
            return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
