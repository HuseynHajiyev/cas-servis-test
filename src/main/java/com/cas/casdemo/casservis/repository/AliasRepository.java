package com.cas.casdemo.casservis.repository;

import com.cas.casdemo.casservis.entity.alias.Alias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AliasRepository extends JpaRepository<Alias,Long> {
    List<Alias> findAllByAccountObjectId(Long accountObjectId);
}
