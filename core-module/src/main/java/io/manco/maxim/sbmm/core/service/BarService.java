package io.manco.maxim.sbmm.core.service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.manco.maxim.sbmm.core.domain.Bar;

@Service
public class BarService {

  @Autowired
  private BarRepository barDao;

  public List<Bar> getBarList(String instId) {
    return null;
    // return barDao.getBarList(instId);
  }

  public Bar getSingleBar(long barId, String instId) {
    return null;
    // return barDao.getSingleBar(barId, instId);
  }

  public boolean update(Bar bar) {
    return false;
    // return barDao.updateBar(bar);
  }

  public boolean createBar(Bar bar) throws ServiceException {
    return false;
    // return barDao.createBar(bar);
  }

  public boolean deleteBar(Bar bar) {
    return false;
    // return barDao.deleteBar(bar);
  }

  public List<String> searchTickersByChars(String tickerNamePart) {
    return null;
    // return barDao.searchTickersByChars(tickerNamePart);
  }

}
