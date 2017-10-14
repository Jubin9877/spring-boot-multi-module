package io.manco.maxim.sbmm.service;

import java.util.List;

//import com.gjj.igden.service.test.daostub.WatchListDescDaoStub;
//import com.gjj.igden.model.IWatchListDesc;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import io.manco.maxim.sbmm.domain.WatchListDesc;
import io.manco.maxim.sbmm.repository.WatchListRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceTestConfiguration.class)
public class WatchListDescServiceTest {
  @Autowired
  private WatchListService watchListDescService;

  @Autowired
  private WatchListRepository watchListRepository;

  @Test
  public void simpleReadTest() throws Exception {
    watchListDescService.getStockSymbolsList(1).forEach(System.out::println);
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
    List<WatchListDesc> dataSetList = watchListDescService.getWatchListForAccount(1);
    final int expectedDataSetsAmount = dataSetList.size();
    dataSetList.forEach(p -> System.out.println(p.getWatchListId()));
    watchListDescService.delete(dataSetList.get(0));
    WatchListDesc watchlistid = dataSetList.get(0);
    WatchListDesc watchListDesc = watchListRepository.findByAccountAccountIdAndWatchListId(1,
        watchlistid.getWatchListId());
    Assert.assertNull(watchListDesc);
    System.out.println("after deletion ");
    dataSetList = watchListDescService.getWatchListForAccount(1);
    dataSetList.forEach(p -> System.out.println(p.getWatchListId()));
    Assert.assertNotEquals(expectedDataSetsAmount, dataSetList.size());
  }

  @Test
  public void testCreateDataSet() throws Exception {
    int accId = 1;
    WatchListDesc newWatchList = watchListDescService.getWatchListDesc(1, accId);
    // TODO:: delete a watchList.
    List<WatchListDesc> dataSetList = watchListDescService.getWatchListForAccount(1);

    dataSetList.forEach(p -> System.out.print(p.getWatchListId() + " ; "));
    watchListDescService.delete(dataSetList.get(0));
    int expectedDataSetsAmountAfterDeletion = 4;
    Assert.assertEquals(expectedDataSetsAmountAfterDeletion, dataSetList.size());
    Assert.assertNotNull(newWatchList);
    newWatchList.setWatchListId(111);
    newWatchList.setAccount(newWatchList.getAccount());
    // newWatchList.setAccountId(accId);
    newWatchList.setWatchListName("just testing around");

    WatchListDesc watchListDesc = (watchListDescService.create(newWatchList, accId));
    Assert.assertNotNull(watchListDesc);
    dataSetList = watchListDescService.getWatchListForAccount(1);
    dataSetList.forEach(p -> System.out.print(p.getWatchListId() + " ; "));
    expectedDataSetsAmountAfterDeletion = 5;
    Assert.assertEquals(expectedDataSetsAmountAfterDeletion, dataSetList.size());
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
