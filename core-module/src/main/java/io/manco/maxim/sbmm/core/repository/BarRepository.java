package io.manco.maxim.sbmm.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.manco.maxim.sbmm.core.domain.Bar;

public interface BarRepository extends JpaRepository<Bar, Integer> {

  List<Bar> findByWatchListTickerInstId(String watchListTickerId);

  List<Bar> findByWatchListTickerInstIdContaining(String tickerNameId);

  Bar findByMdIdAndWatchListTickerInstId(Integer barId, String instId);

}
