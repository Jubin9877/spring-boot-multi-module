package io.manco.maxim.sbmm.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import com.gjj.igden.model.Account;
//import com.gjj.igden.model.WatchListDesc;
//import com.gjj.igden.service.accountService.AccountService;
//import com.gjj.igden.service.test.daostub.AccountDaoStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import io.manco.maxim.sbmm.domain.Account;
import io.manco.maxim.sbmm.domain.UserRoles;
import io.manco.maxim.sbmm.domain.WatchListDesc;
import io.manco.maxim.sbmm.repository.AccountRepository;
import io.manco.maxim.sbmm.repository.UserRoleRepository;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ServiceTestApplication.class)
public class AccountServiceTest {

	private static Map<Integer, Account> accountDbSimulator;

	@InjectMocks
	private AccountService accountService;

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private UserRoleRepository userRoleRepository;

	@Before
	public void accountSetup() {
		accountDbSimulator = Maps.newHashMap(ImmutableMap.of(1,
		    new Account(1, "accountName_test1", "eMail_test1", "additionalInfo_test1", "password_test1",
		        Stream.of(new WatchListDesc(), new WatchListDesc(), new WatchListDesc(), new WatchListDesc())
		            .collect(Collectors.toList()),
		        "creationDate_test1"),
		    2, new Account(2, "accountName_test2", "eMail_test2", "additionalInfo_test2", "password_test2",
		        Stream.of(new WatchListDesc()).collect(Collectors.toList()), "creationDate_test2")));
		when(accountRepository.findAll())
		    .thenReturn(accountDbSimulator.values().stream().filter(Objects::nonNull).collect(Collectors.toList()));

		when(accountRepository.findOne(anyInt())).thenAnswer(new Answer<Account>() {
			@Override
			public Account answer(InvocationOnMock invocation) throws Throwable {
				Integer id = invocation.getArgumentAt(0, Integer.class);
				return accountDbSimulator.get(id);
			}
		});

		when(userRoleRepository.findByrole(anyString())).thenReturn(new UserRoles());

		when(accountRepository.save(any(Account.class))).thenAnswer(new Answer<Account>() {
			@Override
			public Account answer(InvocationOnMock invocation) throws Throwable {
				Account acc = invocation.getArgumentAt(0, Account.class);
				accountDbSimulator.put(acc.getAccountId(), acc);
				return acc;
			}
		});

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Integer id = invocation.getArgumentAt(0, Integer.class);
				accountDbSimulator.remove(id);
				return null;
			}
		}).when(accountRepository).delete(anyInt());
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

		when(accountRepository.findAll())
		    .thenReturn(accountDbSimulator.values().stream().filter(Objects::nonNull).collect(Collectors.toList()));

		int newSize = accountService.getAccountList().size();
		List<Account> accounts = accountService.getAccountList();
		Assert.assertNotEquals(oldSize, newSize);
		Assert.assertEquals(3, newSize);
	}

	@Test
	public void testSimpleDelete() {
		int oldSize = accountService.getAccountList().size();
		accountService.delete(1);

		when(accountRepository.findAll())
		    .thenReturn(accountDbSimulator.values().stream().filter(Objects::nonNull).collect(Collectors.toList()));
		int newSize = accountService.getAccountList().size();
		Assert.assertNotEquals(oldSize, newSize);
	}
}
