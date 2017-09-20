package io.manco.maxim.sbmm.core.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MarketData implements Serializable {

  private static final long serialVersionUID = 8603664627443524436L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  private WatchList watchList;

  private int barSize;

  private double open;

  private double high;

  private double low;

  private double close;

  private long volume;

  private String logInfo;

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

  public int getBarSize() {
    return barSize;
  }

  public void setBarSize(int barSize) {
    this.barSize = barSize;
  }

  public double getOpen() {
    return open;
  }

  public void setOpen(double open) {
    this.open = open;
  }

  public double getHigh() {
    return high;
  }

  public void setHigh(double high) {
    this.high = high;
  }

  public double getLow() {
    return low;
  }

  public void setLow(double low) {
    this.low = low;
  }

  public double getClose() {
    return close;
  }

  public void setClose(double close) {
    this.close = close;
  }

  public long getVolume() {
    return volume;
  }

  public void setVolume(long volume) {
    this.volume = volume;
  }

  public String getLogInfo() {
    return logInfo;
  }

  public void setLogInfo(String logInfo) {
    this.logInfo = logInfo;
  }

  @Override
  public String toString() {
    return "MarketData [id=" + id + ", watchList=" + watchList + ", barSize=" + barSize + ", open="
        + open + ", high=" + high + ", low=" + low + ", close=" + close + ", volume=" + volume
        + ", logInfo=" + logInfo + "]";
  }

}
