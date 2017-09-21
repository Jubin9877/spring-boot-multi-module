package io.manco.maxim.sbmm.core.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class WatchListTicker {

  @Id
  private String instId;

  @ManyToOne
  private WatchListDesc watchList;

  public String getInstId() {
    return instId;
  }

  public void setInstId(String instId) {
    this.instId = instId;
  }

  public WatchListDesc getWatchList() {
    return watchList;
  }

  public void setWatchList(WatchListDesc watchList) {
    this.watchList = watchList;
  }

}
