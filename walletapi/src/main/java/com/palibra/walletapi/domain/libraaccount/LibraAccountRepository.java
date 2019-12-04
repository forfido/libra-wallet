package com.palibra.walletapi.domain.libraaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraAccountRepository extends JpaRepository<LibraAccount, Long> {
    Optional<LibraAccount> findByUserId(Long userId);
}
