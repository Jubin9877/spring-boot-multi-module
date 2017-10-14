package io.manco.maxim.sbmm.service.stub;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import io.manco.maxim.sbmm.domain.Account;
import io.manco.maxim.sbmm.domain.WatchListDesc;
import io.manco.maxim.sbmm.repository.WatchListRepository;

public class WatchListDescRepositoryStub implements WatchListRepository {

	private static Map<Integer, List<WatchListDesc>> watchListDescsDb;

	static {
		List<WatchListDesc> watchListDescsAttachedToAccWithIdOne = Stream
		    .of(new WatchListDesc(), new WatchListDesc(), new WatchListDesc(), new WatchListDesc())
		    .collect(Collectors.toList());

		WatchListDesc theWatchListD = new WatchListDesc();
		theWatchListD.setWatchListName("test-aapl-5minBar-preMarketdata");

		List<WatchListDesc> watchListDescsAttachedToAccWithIdTwo = Lists.newArrayList(theWatchListD);

		watchListDescsDb = Maps
		    .newHashMap(ImmutableMap.of(1, watchListDescsAttachedToAccWithIdOne, 2, watchListDescsAttachedToAccWithIdTwo));
	}

	@Override
	public List<WatchListDesc> findAll() {

		return null;
	}

	@Override
	public List<WatchListDesc> findAll(Sort sort) {

		return null;
	}

	@Override
	public List<WatchListDesc> findAll(Iterable<Integer> ids) {

		return null;
	}

	@Override
	public <S extends WatchListDesc> List<S> save(Iterable<S> entities) {

		return null;
	}

	@Override
	public void flush() {
	}

	@Override
	public <S extends WatchListDesc> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<WatchListDesc> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public WatchListDesc getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends WatchListDesc> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends WatchListDesc> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<WatchListDesc> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Integer arg0) {
	}

	@Override
	public void delete(WatchListDesc watchListDesc) {
		watchListDescsDb.entrySet().stream().anyMatch(e -> e.getValue().removeIf(p -> p.equals(watchListDesc)));

	}

	@Override
	public void delete(Iterable<? extends WatchListDesc> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(Integer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WatchListDesc findOne(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends WatchListDesc> S save(S watchlistDesc) {
		List<WatchListDesc> watchlist = watchListDescsDb.get(watchlistDesc.getAccount().getAccountId());
		if (watchlist != null) {
			watchlist.add(watchlistDesc);
		}else{
			watchListDescsDb.put(watchlistDesc.getAccount().getAccountId(), watchlist);
		}
		return null;
	}

	@Override
	public <S extends WatchListDesc> long count(Example<S> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends WatchListDesc> boolean exists(Example<S> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends WatchListDesc> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends WatchListDesc> S findOne(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WatchListDesc> findByAccount(Account account) {
		return findByAccountAccountId(account.getAccountId());
	}

	@Override
	public List<WatchListDesc> findByAccountAccountId(Integer accountId) {
		return watchListDescsDb.get(accountId);
	}

	@Override
	public WatchListDesc findByAccountAccountIdAndWatchListId(Integer accountId, Integer watchListId) {
		// TODO Auto-generated method stub
		return null;
	}

}
