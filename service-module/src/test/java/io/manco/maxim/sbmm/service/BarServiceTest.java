package io.manco.maxim.sbmm.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
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

import io.manco.maxim.sbmm.domain.Bar;
import io.manco.maxim.sbmm.domain.Stock;
import io.manco.maxim.sbmm.service.stub.BarRepositoryStub;
import io.manco.maxim.sbmm.service.stub.StockRepositoryStub;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceTestConfiguration.class)
public class BarServiceTest {
  @Autowired
  private BarService barService;
  
  @Before
  public void BarSetup(){
  	
  	Stock stock1 = new Stock(1, "AAPL@NASDAQ");
  	Stock stock2 = new Stock(2, "GOOG@NASDAQ");
  	Stock stock3 = new Stock(3, "ORCL@NASDAQ");

  	StockRepositoryStub.stockSimulatorDB = Maps.newHashMap(ImmutableMap.of("AAPL@NASDAQ", stock1, "GOOG@NASDAQ", stock2, "ORCL@NASDAQ", stock3));

  	List<Bar> barList1 = Stream.of(
  			new Bar(1L, stock1),
        new Bar(2L, stock1), 
        new Bar(3L, stock1))
        .collect(Collectors.toList());

      List<Bar> barList2 = Stream.of(
    		new Bar(1L, stock2),
        new Bar(2L, stock2),
        new Bar(3L, stock2))
        .collect(Collectors.toList());

      List<Bar> barListORCL = Stream.of(
    		new Bar(1L, stock3),
        new Bar(2L, stock3),
        new Bar(3L, stock3))
        .collect(Collectors.toList());

      Map<Integer, List<Bar>> barCollectionMap1 = Maps.newHashMap(ImmutableMap.of(1, barList1, 2, barList2, 3, barListORCL));
      Map<Integer, List<Bar>> barCollectionMap2 = Maps.newHashMap(ImmutableMap.of(3, barListORCL));
      int simulateDataSetId01 = 1;
      int simulateDataSetId02 = 2;
      BarRepositoryStub.marketDataDbSimulator = Maps.newHashMap(ImmutableMap.of(simulateDataSetId01, barCollectionMap1, simulateDataSetId02, barCollectionMap2));
  	
  }

  @Test
  public void simpleReadTest01() {
    List<Bar> accounts = barService.getBarListStock("AAPL@NASDAQ");
    accounts.forEach(System.out::println);
  }

  @Test
  public void testCreateH2DataBaseTest() {
    Bar bar = barService.getSingleBar(1L, "AAPL@NASDAQ");
    Assert.assertNotNull(bar);
    Assert.assertEquals("AAPL@NASDAQ", bar.getStock().getName());
  }

  @Test
  public void testUpdate() {
    Bar bar = barService.getSingleBar(1L, "AAPL@NASDAQ");
    Bar barCopy = new Bar(bar);
    barCopy.setLogInfo("test update");
    Bar barcheck = barService.update(barCopy);
    Assert.assertNotNull(barcheck);
    Assert.assertEquals("test update", barcheck.getLogInfo());
  }

  @Test
  public void testCreateBar() throws Exception {
    Bar bar = barService.getSingleBar(2L, "AAPL@NASDAQ");
    System.out.println(bar);
    Assert.assertNotNull(bar);
    bar.setMdId(111L);
    System.out.println(bar);
    Bar bardata = (barService.createBar(bar));
    Assert.assertNotNull(bardata);
    List<Bar> barList = barService.getBarListStock("AAPL@NASDAQ");
    Bar newBar = barList.stream().filter(p -> Objects.equals(p.getMdId(), 111L)).findAny().get();
    System.out.println(newBar);
    Assert.assertEquals(Long.valueOf(111), newBar.getMdId());
  }

  @Test
  public void testDelete() throws Exception {
    Bar bar = barService.getSingleBar(3L, "AAPL@NASDAQ");
    Assert.assertNotNull(bar);
    barService.deleteBar(bar);
    Bar barcheck = barService.getSingleBar(3L, "AAPL@NASDAQ");
    Assert.assertNull(barcheck);
  }
}