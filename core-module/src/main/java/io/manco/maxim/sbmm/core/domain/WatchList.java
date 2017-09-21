package io.manco.maxim.sbmm.core.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class WatchList implements Serializable {

	private static final long serialVersionUID = -913486816874736372L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	private Account account;

	private String name;

	private String description;

	private Integer marketDataFrequency;

	private String dataProvider;
	
	@OneToMany(mappedBy = "watchList", cascade = CascadeType.ALL)
	private List<WatchListTicker> watchListTickers;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMarketDataFrequency() {
		return marketDataFrequency;
	}

	public void setMarketDataFrequency(Integer marketDataFrequency) {
		this.marketDataFrequency = marketDataFrequency;
	}

	public String getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(String dataProvider) {
		this.dataProvider = dataProvider;
	}

	public List<WatchListTicker> getWatchListTickers() {
		return watchListTickers;
	}

	public void setWatchListTickers(List<WatchListTicker> watchListTickers) {
		this.watchListTickers = watchListTickers;
	}

	@Override
	public String toString() {
		return "DataSet [id=" + id + ", account=" + account + ", name=" + name + ", description=" + description
				+ ", marketDataFrequency=" + marketDataFrequency + ", dataProvider=" + dataProvider + "]";
	}

}
