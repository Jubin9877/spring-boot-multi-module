package io.manco.maxim.sbmm.core.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WatchListTicker {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private WatchList watchList;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public WatchList getWatchList() {
    return watchList;
  }

  public void setWatchList(WatchList watchList) {
    this.watchList = watchList;
  }

  @Override
  public String toString() {
    return "WatchListTicker [id=" + id + ", watchList=" + watchList + "]";
  }

}
