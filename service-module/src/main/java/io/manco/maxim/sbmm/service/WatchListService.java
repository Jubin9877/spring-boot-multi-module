package io.manco.maxim.sbmm.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.manco.maxim.sbmm.domain.Account;
import io.manco.maxim.sbmm.domain.Stock;
import io.manco.maxim.sbmm.domain.WatchListDesc;
import io.manco.maxim.sbmm.domain.WatchListDesc.OperationParameters;
import io.manco.maxim.sbmm.repository.AccountRepository;
import io.manco.maxim.sbmm.repository.StockRepository;
import io.manco.maxim.sbmm.repository.WatchListRepository;

@Service
public class WatchListService {

  @Autowired
  private WatchListRepository watchListDescDao;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private StockRepository stockRepository;

  public List<WatchListDesc> getWatchListForAccount(Integer accountId) {
    return watchListDescDao.findByAccountAccountId(accountId);
  }

  public List<WatchListDesc> getWatchListForAccount(Account account) {
    return watchListDescDao.findByAccount(account);
  }

  public List<String> getStockSymbolsList(Integer id) {
    WatchListDesc watchListDesc = watchListDescDao.findOne(id);
    Set<Stock> setStocks = watchListDesc.getStock();
    List<String> listStock = new ArrayList<>();
    for (Stock stock : setStocks) {
      listStock.add(stock.getName());
    }
    return listStock;
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

    Set<Stock> stockList = new HashSet<>();
    for (OperationParameters operationParameter : watchListDesc.getOperationParameterses()) {
      Stock stock1 = stockRepository.findByName(operationParameter.getName());

      if (stock1 != null) {
        stockList.add(stock1);
      } else {
        Stock stock = new Stock();
        stock.setName(operationParameter.getName());
        stockList.add(stock);
        stockRepository.save(stock);
      }
    }
    watchListDesc.setStock(stockList);
    watchListDesc = watchListDescDao.save(watchListDesc);
    return watchListDesc;
  }

  public WatchListDesc update(WatchListDesc watchListDesc) {
    return watchListDescDao.save(watchListDesc);
  }

}
