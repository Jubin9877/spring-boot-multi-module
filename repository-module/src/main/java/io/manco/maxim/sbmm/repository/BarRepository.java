package io.manco.maxim.sbmm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.manco.maxim.sbmm.domain.Bar;

public interface BarRepository extends JpaRepository<Bar, Integer> {
  
  List<Bar> findByStockId(Integer stockId);

  Bar findByMdIdAndStockId(Long barId, Integer stockId);

}
