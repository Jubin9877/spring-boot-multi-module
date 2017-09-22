package io.manco.maxim.sbmm.core.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.manco.maxim.sbmm.core.domain.Account;
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
		return accountDao.save(account);
	}

	public Account updateAccount(Account account) {
		return accountDao.save(account);
	}

	public Account retrieveAccount(int accId) {
		Account user = accountDao.getOne(accId);
		List<WatchListDesc> dataSetList = watchListDescDao.getWatchListForAccount(accId);
		user.setDataSets(dataSetList);
		return user;
	}

	public void delete(int id) {
		accountDao.delete(id);
	}

	public void setImage(int accId, InputStream is) throws IOException {
		Account account = accountDao.getOne(accId);
		if (account == null) {
			// Throw error for 404
			return;
		}
		account.setImage(IOUtils.toByteArray(is));
		accountDao.save(account);
	}

	public byte[] getImage(int accId) {
		Account account = accountDao.getOne(accId);
		if (account == null) {
			// Throw error for 404
			return null;
		}
		return account.getImage();
	}
}
