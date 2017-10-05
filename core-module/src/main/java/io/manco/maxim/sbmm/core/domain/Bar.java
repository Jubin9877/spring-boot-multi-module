package io.manco.maxim.sbmm.core.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "market_data")
public class Bar implements Serializable {

	private static final long serialVersionUID = 8603664627443524436L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mdId;

//	@ManyToOne
//	@JoinColumn(name="inst_id", nullable=false)
//	private WatchListTicker watchListTicker;
	
	@ManyToOne
	@JoinColumn(name="stock_id")
	private Stock stock;

	@Transient
	private Integer barSize;

	private String ticker;

	private Float open;

	private Float high;

	private Float low;

	private Float close;

	@Column(name = "vol")
	private Integer volume;

	@Column(name = "additional_info")
	private String logInfo;

	private Timestamp date;

	public Integer getMdId() {
		return mdId;
	}

	public void setMdId(Integer mdId) {
		this.mdId = mdId;
	}

//	public WatchListTicker getWatchListTicker() {
//		return watchListTicker;
//	}
//
//	public void setWatchListTicker(WatchListTicker watchListTicker) {
//		this.watchListTicker = watchListTicker;
//	}

	public Integer getBarSize() {
		return barSize;
	}

	public void setBarSize(Integer barSize) {
		this.barSize = barSize;
	}

	public float getOpen() {
		return open;
	}

	public void setOpen(Float open) {
		this.open = open;
	}

	public float getHigh() {
		return high;
	}

	public void setHigh(Float high) {
		this.high = high;
	}

	public float getLow() {
		return low;
	}

	public void setLow(Float low) {
		this.low = low;
	}

	public float getClose() {
		return close;
	}

	public void setClose(Float close) {
		this.close = close;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public String getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	

	public Stock getStock() {
    return stock;
  }

  public void setStock(Stock stock) {
    this.stock = stock;
  }

  @Override
	public String toString() {
		return "MarketData [id=" + mdId + ", barSize=" + barSize + ", open=" + open + ", high=" + high + ", low=" + low
		    + ", close=" + close + ", volume=" + volume + ", logInfo=" + logInfo + "]";
	}

}
