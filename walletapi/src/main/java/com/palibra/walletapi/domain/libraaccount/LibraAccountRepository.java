package com.palibra.walletapi.domain.libraaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraAccountRepository extends JpaRepository<LibraAccount, Long> {
    List<LibraAccount> findByUserId(Long userId);

    Optional<LibraAccount> findByUserIdAndName(Long userId, String name);
}
