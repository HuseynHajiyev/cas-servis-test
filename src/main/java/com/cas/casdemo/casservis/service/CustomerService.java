package com.cas.casdemo.casservis.service;

import com.cas.casdemo.casservis.dto.customer.CustomerPostRequestDTO;
import com.cas.casdemo.casservis.dto.customer.DetailsDTO;
import com.cas.casdemo.casservis.entity.Customer;
import com.cas.casdemo.casservis.entity.Details;
import com.cas.casdemo.casservis.repository.CustomerRepository;
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
}
