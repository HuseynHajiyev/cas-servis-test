package com.cas.casdemo.casservis.controller;

import com.cas.casdemo.casservis.dto.customer.CustomerDeleteResponseDTO;
import com.cas.casdemo.casservis.dto.customer.CustomerPostRequestDTO;
import com.cas.casdemo.casservis.dto.customer.CustomerUpdateRequestDTO;
import com.cas.casdemo.casservis.entity.Customer;
import com.cas.casdemo.casservis.repository.CustomerRepository;
import com.cas.casdemo.casservis.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    public CustomerController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody CustomerPostRequestDTO customer) {
        Customer savedCustomer = customerService.save(customer);
        return ResponseEntity.ok(customerRepository.save(savedCustomer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateRequestDTO dto) {
        Customer updated = customerService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            CustomerDeleteResponseDTO cdr = customerService.mapFromEntity(customer.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
