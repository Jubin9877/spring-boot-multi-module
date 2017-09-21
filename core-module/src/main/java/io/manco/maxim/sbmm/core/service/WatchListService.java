package io.manco.maxim.sbmm.core.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.manco.maxim.sbmm.core.domain.Account;
import io.manco.maxim.sbmm.core.domain.WatchList;
import io.manco.maxim.sbmm.core.domain.WatchListTicker;
import io.manco.maxim.sbmm.core.repository.AccountRepository;
import io.manco.maxim.sbmm.core.repository.WatchListRepository;
import io.manco.maxim.sbmm.core.repository.WatchListTickerRepository;

public class WatchListService {

//	private static final Logger LOGGER = LoggerFactory.getLogger(WatchListService.class);
//
//	@Autowired
//	private WatchListRepository watchListRepository;
//
//	@Autowired
//	private WatchListTickerRepository watchListTickerRepository;
//
//	@Autowired
//	private AccountRepository accountRepository;
//
//	public List<String> getStockSymbolsList(int watchLIstId) {
//		List<WatchListTicker> watchLIsts = watchListTickerRepository.findByWatchListId(watchLIstId);
//		List<String> stocks = new ArrayList<>();
//		for (WatchListTicker watchListTicker : watchLIsts) {
//			stocks.add(watchListTicker.getInstId());
//		}
//		return stocks;
//	}
//
//	public boolean create(WatchList watchList, Integer accId, String watchlistName) {
//		Account account = accountRepository.getOne(accId);
//		if (account == null) {
//			LOGGER.debug("Account with id : {} not found", accId);
//			return false;
//		}
//		List<Object> list = stockSymbolsList = watchList.getOperationParameterses();
//		List<String> stringList = stockSymbolsList.stream().map(OperationParameters::getName)
//				.collect(Collectors.toList());
//		this.stockSymbolsList = stringList;
//		return watchListRepository.createWatchListDesc(watchList);
//	}
//
//	public List<WatchList> getWatchListForAccount(int id) {
//		return watchListRepository.getDataSetsAttachedToAcc(id);
//	}
//
//	public boolean delete(int accId, int watchListId) {
//		return watchListRepository.deleteWatchListDesc(watchListId, accId);
//	}
//
//	public void delete(WatchList watchListDesc) {
//		watchListRepository.delete(watchListDesc);
//	}
//
//	public WatchList update(WatchList watchListDesc) {
//		return watchListRepository.save(watchListDesc);
//	}
//
//	public WatchList getWatchListDesc(int dsId, int accId) {
//		return watchListRepository.getWatchListDesc(dsId, accId);
//	}

}
