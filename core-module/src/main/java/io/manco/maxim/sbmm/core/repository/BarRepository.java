package io.manco.maxim.sbmm.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.manco.maxim.sbmm.core.domain.Bar;

public interface BarRepository extends JpaRepository<Bar, Integer> {

  List<Bar> findByWatchListTickerId(String watchListTickerId);

  List<Bar> findByWatchListTickerIdContaining(String tickerNameId);

  Bar findByMdIdAndWatchListTickerId(Integer barId, String instId);

}
