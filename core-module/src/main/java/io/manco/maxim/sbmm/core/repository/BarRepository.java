package io.manco.maxim.sbmm.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.manco.maxim.sbmm.core.domain.Bar;

public interface BarRepository extends JpaRepository<Bar, Integer> {
  
  List<Bar> findByStockId(Integer stockId);

  Bar findByMdIdAndStockId(Integer barId, Integer stockId);

}
