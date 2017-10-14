package io.manco.maxim.sbmm.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.manco.maxim.sbmm.domain.Account;
import io.manco.maxim.sbmm.domain.UserRoles;
import io.manco.maxim.sbmm.domain.WatchListDesc;
import io.manco.maxim.sbmm.repository.AccountRepository;
import io.manco.maxim.sbmm.repository.UserRoleRepository;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountDao;

  @Autowired
  private UserRoleRepository userRoleRepository;

  public List<Account> getAccountList() {
    return accountDao.findAll();
  }

  private static final String ROLE = "ROLE_ADMIN";

  public Account createAccount(Account account) {
    account.setCreationDate(LocalDate.now().toString());

    UserRoles userroles = userRoleRepository.findByrole(ROLE);

    Set<UserRoles> roles = new HashSet<>();
    roles.add(userroles);

    account.setRoles(roles);
    return accountDao.save(account);
  }

  public Account updateAccount(Account account) {
    Account managedAccount = accountDao.findOne(account.getAccountId());
    managedAccount.setAccountName(account.getAccountName());
    managedAccount.setAdditionalInfo(account.getAdditionalInfo());
    managedAccount.setEmail(account.getEmail());
    return accountDao.save(managedAccount);
  }

  public Account retrieveAccount(int accId) {
    Account user = accountDao.findOne(accId);
    List<WatchListDesc> dataSetList = user.getDataSets();
    user.setDataSets(dataSetList);
    return user;
  }

  public void delete(int id) {
    accountDao.delete(id);
  }

  public void setImage(int accId, InputStream is) throws IOException {
    Account account = accountDao.findOne(accId);
    if (account == null) {
      // Throw error for 404
      return;
    }
    account.setImage(IOUtils.toByteArray(is));
    accountDao.save(account);
  }

  public byte[] getImage(int accId) {
    Account account = accountDao.findOne(accId);
    if (account == null) {
      // Throw error for 404
      return null;
    }
    return account.getImage();
  }

}
