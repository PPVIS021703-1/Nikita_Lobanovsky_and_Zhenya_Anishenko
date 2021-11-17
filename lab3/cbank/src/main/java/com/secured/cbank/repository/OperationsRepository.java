package com.secured.cbank.repository;

import com.secured.cbank.entity.Operations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationsRepository extends JpaRepository<Operations, Long> {
}
