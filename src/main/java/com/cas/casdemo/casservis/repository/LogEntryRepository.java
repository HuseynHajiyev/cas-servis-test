package com.cas.casdemo.casservis.repository;

import com.cas.casdemo.casservis.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
}