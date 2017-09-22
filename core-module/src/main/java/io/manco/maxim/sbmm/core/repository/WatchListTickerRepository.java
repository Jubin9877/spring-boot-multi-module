package io.manco.maxim.sbmm.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.manco.maxim.sbmm.core.domain.WatchListTicker;

public interface WatchListTickerRepository extends JpaRepository<WatchListTicker, String> {

  List<WatchListTicker> findByWatchListWatchListId(Integer watchListId);

}
