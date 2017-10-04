package io.manco.maxim.sbmm.core.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.manco.maxim.sbmm.core.domain.Account;
import io.manco.maxim.sbmm.core.domain.UserRoles;
import io.manco.maxim.sbmm.core.domain.WatchListDesc;
import io.manco.maxim.sbmm.core.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountDao;

	@Autowired
	private WatchListService watchListDescDao;

  public List<Account> getAccountList() {
    return accountDao.findAll();
  }

  public Account createAccount(Account account) {
    account.setCreationDate(LocalDate.now().toString());
    Set<UserRoles> roles = new HashSet<>();
    UserRoles role = new UserRoles();
    role.setRole("ROLE_ADMIN");
    role.setAccount(account);
    role.setAccount_name(account.getAccountName());
    roles.add(role);
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
    List<WatchListDesc> dataSetList = watchListDescDao.getWatchListForAccount(accId);
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
