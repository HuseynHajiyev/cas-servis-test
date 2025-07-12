package com.cas.casdemo.casservis.repository;

import com.cas.casdemo.casservis.entity.accountObject.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}