package io.manco.maxim.sbmm.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.manco.maxim.sbmm.repository.BarRepository;
import io.manco.maxim.sbmm.repository.StockRepository;
import io.manco.maxim.sbmm.domain.Bar;
import io.manco.maxim.sbmm.domain.Stock;

@Service
public class BarService {

  @Autowired
  private BarRepository barDao;

  @Autowired
  private StockRepository stockRepository;

  public List<Bar> getBarListStock(Integer StockId) {
    return barDao.findByStockId(StockId);
  }

  public Bar getSingleBar(Integer barId, String instId) {
    Stock stock = stockRepository.findByName(instId);
    return barDao.findByMdIdAndStockId(barId, stock.getId());
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

    if (StringUtils.isEmpty(tickerNameId)) {
      return Collections.emptyList();
    }

    List<Stock> stocklist = stockRepository.findByNameContaining(tickerNameId);
    List<String> stockSymbols = new ArrayList<>();
    for (Stock stock : stocklist) {
      stockSymbols.add(stock.getName());
    }
    return stockSymbols;
  }

}
