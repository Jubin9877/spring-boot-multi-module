package io.manco.maxim.sbmm.core.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.manco.maxim.sbmm.core.domain.Account;
import io.manco.maxim.sbmm.core.domain.UserRoles;
import io.manco.maxim.sbmm.core.repository.AccountRepository;

@Service
public class UserAuthenticationService implements UserDetailsService{

  @Autowired
  private AccountService accountService;
  
  @Autowired
  private AccountRepository accountRepository;

  
  public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException, DataAccessException {
    Account user = accountRepository.findByAccountName(accountName);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid username or password.");
    }
    return user;
  }
}
