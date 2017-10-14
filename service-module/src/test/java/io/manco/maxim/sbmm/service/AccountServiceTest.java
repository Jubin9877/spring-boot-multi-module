package io.manco.maxim.sbmm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import io.manco.maxim.sbmm.domain.Account;
import io.manco.maxim.sbmm.domain.WatchListDesc;
import io.manco.maxim.sbmm.service.stub.AccountRepositoryStub;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceTestConfiguration.class)
public class AccountServiceTest {

	@Autowired
	private AccountService accountService;

	@Before
	public void accountSetup() {
		AccountRepositoryStub.accountDbSimulator = Maps.newHashMap(ImmutableMap.of(1,
		    new Account(1, "accountName_test1", "eMail_test1", "additionalInfo_test1", "password_test1",
		        Stream.of(new WatchListDesc(), new WatchListDesc(), new WatchListDesc(), new WatchListDesc())
		            .collect(Collectors.toList()),
		        "creationDate_test1"),
		    2, new Account(2, "accountName_test2", "eMail_test2", "additionalInfo_test2", "password_test2",
		        Stream.of(new WatchListDesc()).collect(Collectors.toList()), "creationDate_test2")));
	}

	@Test
	public void simpleReadTest01() {
		List<Account> accounts = accountService.getAccountList();
		Assert.assertEquals("eMail_test1", accounts.get(0).getEmail());
		Assert.assertEquals("accountName_test1", accounts.get(0).getAccountName());
	}

	@Test
	public void simpleReadTest02() {
		List<Account> accounts = accountService.getAccountList();

		List<WatchListDesc> watchListDescs = new ArrayList<>(1);

		accounts.stream().filter(p -> p.getAccountName().equals("accountName_test2")).findAny()
		    .ifPresent(p -> watchListDescs.addAll(p.getDataSets()));
		Assert.assertEquals(1, watchListDescs.size());
		Assert.assertTrue(accounts.stream().anyMatch(p -> p.getAccountName().equals("accountName_test1")));
	}

	@Test
	public void testEditData() {
		Account account = accountService.retrieveAccount(1);
		String oldName = account.getAccountName();
		account.setAccountName("test update");
		accountService.updateAccount(account);
		String newName = accountService.retrieveAccount(1).getAccountName();
		Assert.assertEquals("test update", newName);
		Assert.assertNotEquals(newName, oldName);
	}

	@Test
	public void testCreate() {

		Account account = accountService.retrieveAccount(2);
		account.setAccountId(111);
		account.setAccountName(" ***** new Account ****** ");
		int oldSize = accountService.getAccountList().size();
		accountService.createAccount(account);

		int newSize = accountService.getAccountList().size();
		List<Account> accounts = accountService.getAccountList();
		Assert.assertNotEquals(oldSize, newSize);
		Assert.assertEquals(3, newSize);
	}

	@Test
	public void testSimpleDelete() {
		int oldSize = accountService.getAccountList().size();
		accountService.delete(1);

		int newSize = accountService.getAccountList().size();
		Assert.assertNotEquals(oldSize, newSize);
	}
}
