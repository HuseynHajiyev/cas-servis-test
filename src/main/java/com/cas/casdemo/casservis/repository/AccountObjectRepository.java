package com.cas.casdemo.casservis.repository;

import com.cas.casdemo.casservis.entity.accountObject.AccountObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountObjectRepository extends JpaRepository<AccountObject, Long> {
    List<AccountObject> findAllByCustomerId(Long customerId);
    Optional<AccountObject> findByIdAndCustomerId(Long id, Long customerId);
}