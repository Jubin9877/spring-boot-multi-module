package io.manco.maxim.sbmm.core.service;

import org.springframework.data.jpa.repository.JpaRepository;

import io.manco.maxim.sbmm.core.domain.Bar;

public interface BarRepository extends JpaRepository<Bar, Integer> {

}
