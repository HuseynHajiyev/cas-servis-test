package com.cas.casdemo.casservis.service;

import com.cas.casdemo.casservis.dto.customer.CustomerDeleteResponseDTO;
import com.cas.casdemo.casservis.dto.customer.CustomerPostRequestDTO;
import com.cas.casdemo.casservis.dto.customer.CustomerUpdateRequestDTO;
import com.cas.casdemo.casservis.dto.customer.DetailsDTO;
import com.cas.casdemo.casservis.entity.Customer;
import com.cas.casdemo.casservis.entity.Details;
import com.cas.casdemo.casservis.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(CustomerPostRequestDTO customerDTO) {
        validateCustomer(customerDTO);
        Customer entity = mapToEntity(customerDTO);
        return customerRepository.save(entity);
    }

    public Customer findById(Long id) throws EntityNotFoundException {
        return customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    public Customer deleteById(Long id) throws EntityNotFoundException {
        Customer entity = findById(id);
        customerRepository.delete(entity);
        return entity;
    }

    public Customer update(Long customerId, CustomerUpdateRequestDTO dto) {
        Customer existing = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        // Only update fields if provided
        if (dto.getPin() != null) {
            existing.setPin(dto.getPin());
        }
        if (dto.getIsResident() != null) {
            existing.setIsResident(dto.getIsResident());
        }

        if (dto.getDetails() != null) {
            Details details = existing.getDetails() != null
                    ? existing.getDetails()
                    : new Details();

            if (dto.getDetails().getName() != null) {
                details.setName(dto.getDetails().getName());
            }
            if (dto.getDetails().getSurname() != null) {
                details.setSurname(dto.getDetails().getSurname());
            }
            if (dto.getDetails().getPhoneNumber() != null) {
                details.setPhoneNumber(dto.getDetails().getPhoneNumber());
            }

            existing.setDetails(details);
        }

        return customerRepository.save(existing);
    }


    private void validateCustomer(CustomerPostRequestDTO customer) {
        if (customer.getPin() == null || customer.getPin().trim().isEmpty()) {
            throw new IllegalArgumentException("PIN must not be empty");
        }
        DetailsDTO details = customer.getDetails();
        if (details == null) {
            throw new IllegalArgumentException("Details must not be null");
        }
        if (details.getName() == null || details.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        if (details.getSurname() == null || details.getSurname().trim().isEmpty()) {
            throw new IllegalArgumentException("Surname must not be empty");
        }
        if (details.getPhoneNumber() == null || details.getPhoneNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number must not be empty");
        }
    }

    private Customer mapToEntity(CustomerPostRequestDTO customer) {
        Customer c = new Customer();
        Details d = new Details();
        c.setPin(customer.getPin());
        c.setIsResident(customer.getIsResident());
        d.setName(customer.getDetails().getName().trim());
        d.setSurname(customer.getDetails().getSurname().trim());
        d.setPhoneNumber(customer.getDetails().getPhoneNumber().trim());
        c.setDetails(d);
        return c;
    }

    public CustomerDeleteResponseDTO mapFromEntity(Customer customer) {
        CustomerDeleteResponseDTO cd = new CustomerDeleteResponseDTO();
        cd.setPin(customer.getPin());
        cd.setIsResident(customer.getIsResident());
        DetailsDTO d = new DetailsDTO();
        d.setName(customer.getDetails().getName());
        d.setSurname(customer.getDetails().getSurname());
        d.setPhoneNumber(customer.getDetails().getPhoneNumber());
        cd.setDetails(d);
        return cd;
    }
}
