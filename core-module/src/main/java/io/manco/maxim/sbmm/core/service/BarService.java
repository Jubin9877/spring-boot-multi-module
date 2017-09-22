package io.manco.maxim.sbmm.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.manco.maxim.sbmm.core.domain.Bar;
import io.manco.maxim.sbmm.core.repository.BarRepository;

@Service
public class BarService {

  @Autowired
  private BarRepository barDao;

  public List<Bar> getBarList(String watchListTickerId) {
    return barDao.findByWatchListTickerInstId(watchListTickerId);
  }

  public Bar getSingleBar(Integer barId, String instId) {
    return barDao.findByMdIdAndWatchListTickerInstId(barId, instId);
  }

  public Bar update(Bar bar) {
    return barDao.save(bar);
  }

  public Bar createBar(Bar bar) {
    return barDao.save(bar);
  }

  public void deleteBar(Bar bar) {
    barDao.delete(bar);
  }

  public List<String> searchTickersByChars(String tickerNameId) {
    List<Bar> barData = barDao.findByWatchListTickerInstIdContaining(tickerNameId);
    List<String> stockSymbols = new ArrayList<>();
    for (Bar bar : barData) {
      stockSymbols.add(bar.getWatchListTicker().getInstId());
    }
    return stockSymbols;
  }

}
