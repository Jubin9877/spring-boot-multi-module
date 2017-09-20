package io.manco.maxim.sbmm.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.manco.maxim.sbmm.core.domain.WatchList;

public interface WatchListRepository extends JpaRepository<WatchList, Integer> {

}
