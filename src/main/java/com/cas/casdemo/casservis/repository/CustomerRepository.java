package com.cas.casdemo.casservis.repository;

import com.cas.casdemo.casservis.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
