package io.manco.maxim.sbmm.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.manco.maxim.sbmm.core.domain.WatchList;
import io.manco.maxim.sbmm.core.repository.WatchListRepository;

public class WatchListService {

  @Autowired
  private WatchListRepository watchListDescDao;

  public List<WatchList> getWatchListForAccount(int id) {
    return watchListDescDao.getDataSetsAttachedToAcc(id);
  }

  public List<String> getStockSymbolsList(int id) {
    return watchListDescDao.getAllStockSymbols(id);
  }

  public boolean delete(int accId, int watchListId) {
    return watchListDescDao.deleteWatchListDesc(watchListId, accId);
  }

  public void delete(WatchList watchListDesc) {
    watchListDescDao.delete(watchListDesc);
  }

  public boolean create(WatchList watchListDesc) {
    watchListDesc.setStockSymbolsListFromOperationList(watchListDesc.getOperationParameterses());
    return watchListDescDao.createWatchListDesc(watchListDesc);
  }

  public WatchList update(WatchList watchListDesc) {
    return watchListDescDao.save(watchListDesc);
  }

  public WatchList getWatchListDesc(int dsId, int accId) {
    return watchListDescDao.getWatchListDesc(dsId, accId);
  }

}
