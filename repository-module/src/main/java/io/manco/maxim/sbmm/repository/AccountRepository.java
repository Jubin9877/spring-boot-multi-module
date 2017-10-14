package io.manco.maxim.sbmm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.manco.maxim.sbmm.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

  Account findByAccountId(Integer accountId);
  
  Account findByAccountName(String accountName);
}
