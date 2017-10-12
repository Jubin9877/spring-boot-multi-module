package io.manco.maxim.sbmm.service;

import java.util.List;
import java.util.Objects;

//import com.gjj.igden.model.Bar;
//import com.gjj.igden.service.barService.BarService;
//import com.gjj.igden.service.test.daostub.BarDaoStub;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.manco.maxim.sbmm.domain.Bar;
import io.manco.maxim.sbmm.service.BarService;

@Configuration
@ComponentScan(basePackageClasses = { BarService.class })
class SpringConfigContextBarService {
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfigContextBarService.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BarServiceTest {
  @Autowired
  private BarService barService;

  @Test
  public void simpleReadTest01() {
    List<Bar> accounts = barService.getBarListStock("AAPL@NASDAQ");
    accounts.forEach(System.out::println);
  }

  @Test
  public void testCreateH2DataBaseTest() {
    Bar bar = barService.getSingleBar(1, "AAPL@NASDAQ");
    Assert.assertNotNull(bar);
    Assert.assertEquals("AAPL@NASDAQ", bar.getStock().getName());
  }

  @Test
  public void testUpdate() {
    Bar bar = barService.getSingleBar(1, "AAPL@NASDAQ");
    Bar barCopy = new Bar(bar);
    barCopy.setLogInfo("test update");
    // Assert.assertTrue(barService.update(barCopy));
    Bar barcheck = barService.update(barCopy);
    Assert.assertNotNull(barcheck);
    bar = barService.getSingleBar(1, "AAPL@NASDAQ");
    Assert.assertEquals("test update", bar.getLogInfo());
  }

  @Test
  public void testCreateBar() throws Exception {
    Bar bar = barService.getSingleBar(2, "AAPL@NASDAQ");
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
    Bar bar = barService.getSingleBar(3, "AAPL@NASDAQ");
    System.out.println(bar);
    Assert.assertNotNull(bar);
    // Assert.assertTrue(barService.deleteBar(bar));
    barService.deleteBar(bar);
    Bar barcheck = barService.getSingleBar(3, "AAPL@NASDAQ");
    Assert.assertNull(barcheck);
    try {
      System.out.println(barService.getSingleBar(3, "AAPL@NASDAQ"));
      Assert.fail();
    } catch (NullPointerException e) {
      Assert.assertTrue(true);
    }
  }
}