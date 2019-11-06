package com.palibra.walletapi.domain.libraaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraAccountRepository extends JpaRepository<LibraAccount, Long> {

}
