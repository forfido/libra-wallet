package com.palibra.walletapi.domain.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<TempTable, Long> {

    Optional<TempTable> findByName(String name);
}
