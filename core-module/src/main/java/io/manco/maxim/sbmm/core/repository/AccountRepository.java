package io.manco.maxim.sbmm.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.manco.maxim.sbmm.core.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

  Account findByAccountName(String accountName);
}
