package io.manco.maxim.sbmm.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.manco.maxim.sbmm.core.domain.Account;
import io.manco.maxim.sbmm.core.domain.WatchListDesc;
import io.manco.maxim.sbmm.core.domain.WatchListTicker;
import io.manco.maxim.sbmm.core.repository.AccountRepository;
import io.manco.maxim.sbmm.core.repository.WatchListRepository;
import io.manco.maxim.sbmm.core.repository.WatchListTickerRepository;

@Service
public class WatchListService {

  @Autowired
  private WatchListRepository watchListDescDao;

  @Autowired
  private WatchListTickerRepository tickerRepository;

  @Autowired
  private AccountRepository accountRepository;

  public List<WatchListDesc> getWatchListForAccount(Integer accountId) {
    return watchListDescDao.findByAccountAccountId(accountId);
  }

  public List<WatchListDesc> getWatchListForAccount(Account account) {
    return watchListDescDao.findByAccount(account);
  }

  public List<String> getAllStockSymbols(Integer watchListDescId) {
    List<WatchListTicker> tickers = tickerRepository.findByWatchListWatchListId(watchListDescId);
    List<String> stockSymbols = new ArrayList<>();
    for (WatchListTicker watchListTicker : tickers) {
      stockSymbols.add(watchListTicker.getInstId());
    }

    return stockSymbols;
  }

  public List<String> getStockSymbolsList(Integer id) {
    return getAllStockSymbols(id);
  }

  public WatchListDesc getWatchListDesc(Integer watchlistId, Integer accountId) {
    return watchListDescDao.findByAccountAccountIdAndWatchListId(watchlistId, accountId);
  }

  public void delete(Integer accId, Integer watchListId) {
    WatchListDesc watchListDesc = getWatchListDesc(watchListId, accId);
    watchListDescDao.delete(watchListDesc);
  }

  public void delete(WatchListDesc watchListDesc) {
    watchListDescDao.delete(watchListDesc);
  }

  public WatchListDesc create(WatchListDesc watchListDesc, Integer accId) {
  	
  	Account account = accountRepository.findOne(accId);
  	watchListDesc.setAccount(account);
  	List<String> stockSymbols = watchListDesc.getStockSymbolsList();
    watchListDesc = watchListDescDao.save(watchListDesc);
    for (String stocks : stockSymbols) {
      WatchListTicker ticker = new WatchListTicker();
      ticker.setInstId(stocks);
      ticker.setWatchList(watchListDesc);
      tickerRepository.save(ticker);
    }
    return watchListDesc;
  }

  public WatchListDesc update(WatchListDesc watchListDesc) {
    return watchListDescDao.save(watchListDesc);
  }

}
