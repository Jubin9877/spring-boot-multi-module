package io.manco.maxim.sbmm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.manco.maxim.sbmm.domain.Account;
import io.manco.maxim.sbmm.repository.AccountRepository;

@Service
public class UserAuthenticationService implements UserDetailsService{

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
