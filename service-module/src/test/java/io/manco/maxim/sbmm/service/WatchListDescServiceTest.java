package io.manco.maxim.sbmm.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import com.gjj.igden.service.test.daostub.WatchListDescDaoStub;
//import com.gjj.igden.model.IWatchListDesc;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import io.manco.maxim.sbmm.domain.Account;
import io.manco.maxim.sbmm.domain.WatchListDesc;
import io.manco.maxim.sbmm.repository.WatchListRepository;
import io.manco.maxim.sbmm.service.stub.AccountRepositoryStub;
import io.manco.maxim.sbmm.service.stub.WatchListDescRepositoryStub;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceTestConfiguration.class)
public class WatchListDescServiceTest {
  @Autowired
  private WatchListService watchListDescService;

  @Autowired
  private WatchListRepository watchListRepository;
  
  @Before
  public void watchListSetup() {
  	AccountRepositoryStub.accountDbSimulator = Maps.newHashMap(ImmutableMap.of(
  			1, new Account(1, "accountName_test1", "eMail_test1", "additionalInfo_test1", "password_test1",
		        Stream.of(new WatchListDesc(1, "wl 2"), new WatchListDesc(2, "wl 1"), new WatchListDesc(3, "wl 3"), new WatchListDesc(4, "wl 4"))
		            .collect(Collectors.toList()), "creationDate_test1"),
		    2, new Account(2, "accountName_test2", "eMail_test2", "additionalInfo_test2", "password_test2",
		        Stream.of(new WatchListDesc()).collect(Collectors.toList()), "creationDate_test2")));
  	
  	List<WatchListDesc> watchListDescsAttachedToAccWithIdOne = Stream
		    .of(new WatchListDesc(1, "wl 2"), new WatchListDesc(2, "wl 1"), new WatchListDesc(3, "wl 3"), new WatchListDesc(4, "wl 4"))
		    .collect(Collectors.toList());

		WatchListDesc theWatchListD = new WatchListDesc(5, "test-aapl-5minBar-preMarketdata");
		
		List<WatchListDesc> watchListDescsAttachedToAccWithIdTwo = Lists.newArrayList(theWatchListD);

		WatchListDescRepositoryStub.watchListDescsDb = Maps
		    .newHashMap(ImmutableMap.of(1, watchListDescsAttachedToAccWithIdOne, 2, watchListDescsAttachedToAccWithIdTwo));
	}

  @Test
  public void simpleReadTest() throws Exception {
    List<WatchListDesc> watchListDescs = watchListDescService.getWatchListForAccount(1);
    Assert.assertEquals(4, watchListDescs.size());
  }

  @Test
  public void testCreateH2DataBaseTest() {
    List<WatchListDesc> dataSetList = watchListDescService.getWatchListForAccount(1);
    final int expectedDataSetsAmount = 4;
    Assert.assertEquals(expectedDataSetsAmount, dataSetList.size());
  }

  @Test
  public void testReturnBarList() {
    WatchListDesc dataSet = watchListDescService.getWatchListForAccount(2).get(0);
    System.out.println(dataSet.getWatchListName());
    Assert.assertNotNull(dataSet);
    Assert.assertEquals("test-aapl-5minBar-preMarketdata", dataSet.getWatchListName());
  }

  @Test
  public void testDelete02() throws Exception {
    List<WatchListDesc> watchLists = watchListDescService.getWatchListForAccount(1);
    Assert.assertEquals(4, watchLists.size());
    watchLists.forEach(p -> System.out.println(p.getWatchListId()));
    watchListDescService.delete(watchLists.get(0));

    watchLists = watchListDescService.getWatchListForAccount(1);
    Assert.assertEquals(3, watchLists.size());
  }

  @Test
  public void testCreateDataSet() throws Exception {
    int accId = 1;
    WatchListDesc newWatchList = watchListDescService.getWatchListDesc(1, accId);
    Assert.assertNotNull(newWatchList);
    newWatchList.setWatchListId(111);
    newWatchList.setAccount(newWatchList.getAccount());
    newWatchList.setWatchListName("just testing around");

    WatchListDesc watchListDesc = watchListDescService.create(newWatchList, accId);
    Assert.assertNotNull(watchListDesc);
  }

  @Test
  public void testUpdate() throws Exception {
    final int accId = 1;
    WatchListDesc dataSet = watchListDescService.getWatchListDesc(1, accId);
    // Account account = accountRepository.findByAccountId(accId);
    dataSet.setWatchListName("test update");
    // dataSet.setAccount(account);
    watchListDescService.update(dataSet);
    final String dataSetNameDirect = watchListDescService.getWatchListDesc(1, 1).getWatchListName();
    Assert.assertEquals("test update", dataSetNameDirect);
  }

  @Test
  public void test01Read() throws Exception {
    List<WatchListDesc> watchListDescs = watchListDescService.getWatchListForAccount(1);
    final int size = 4;
    Assert.assertEquals(size, watchListDescs.size());
  }
}
