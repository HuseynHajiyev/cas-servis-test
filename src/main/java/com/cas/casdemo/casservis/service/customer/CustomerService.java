package com.cas.casdemo.casservis.service.customer;

import com.cas.casdemo.casservis.dto.customer.CustomerGetResponseDTO;
import com.cas.casdemo.casservis.dto.customer.CustomerPostRequestDTO;
import com.cas.casdemo.casservis.dto.customer.CustomerPutRequestDTO;
import com.cas.casdemo.casservis.dto.customer.CustomerDetailsDTO;
import com.cas.casdemo.casservis.entity.customer.Customer;
import com.cas.casdemo.casservis.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerService(CustomerRepository customerRepository,
                           ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public Long save(CustomerPostRequestDTO dto) {
        Customer entity = modelMapper.map(dto, Customer.class);
        entity.setActive(true);
        return customerRepository.save(entity).getId();
    }


    public List<CustomerGetResponseDTO> findAll() {
        return customerRepository.findAll().stream()
                .filter(Customer::getActive)
                .map(e -> modelMapper.map(e, CustomerGetResponseDTO.class))
                .toList();
    }


    public CustomerGetResponseDTO findById(Long id) {
        Customer entity = customerRepository.findById(id)
                .filter(Customer::getActive)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return modelMapper.map(entity, CustomerGetResponseDTO.class);
    }

    public void deleteById(Long id) {
        Customer entity = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        entity.setActive(false);
        customerRepository.save(entity);
    }



    public CustomerGetResponseDTO update(Long id, CustomerPutRequestDTO dto) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(dto, existing);

        existing.setActive(true);

        Customer updated = customerRepository.save(existing);
        return modelMapper.map(updated, CustomerGetResponseDTO.class);
    }
}
