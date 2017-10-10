package io.manco.maxim.sbmm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.manco.maxim.sbmm.domain.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

  Stock findByName(String name);

  List<Stock> findByNameContaining(String name);
}
