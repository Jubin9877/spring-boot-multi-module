package io.manco.maxim.sbmm.core.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections4.FactoryUtils;
import org.apache.commons.collections4.list.LazyList;
import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "watch_list")
public class WatchListDesc {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer watchListId;

  @ManyToOne
  @JoinColumn(name = "account_id", referencedColumnName = "account_id")
  private Account account;

  private String watchListName;

  @Column(name = "watch_list_description")
  private String watchListDetails;

  private int marketDataFrequency;
  private String dataProviders;

  @ManyToMany(cascade = { 
      CascadeType.PERSIST, 
      CascadeType.MERGE
  })
  @JoinTable(name = "watch_stock",
      joinColumns = @JoinColumn(name = "watch_id"),
      inverseJoinColumns = @JoinColumn(name = "stock_id")
  )
  private Set<Stock> stock = new HashSet<>();

  @Transient
  private List<String> stockSymbolsList;

  @Transient
  private List operationParameterses = LazyList.lazyList(new ArrayList<>(),
      FactoryUtils.instantiateFactory(OperationParameters.class));

  public WatchListDesc() {
  }

  // public WatchListDesc(int accountId) {
  // this.accountId = accountId;
  // }

  public WatchListDesc(int watchListId, int accountId, String watchListName, String watchListDetails,
      int marketDataFrequency, String dataProviders) {
    this.watchListId = watchListId;
    // this.accountId = accountId;
    this.watchListName = watchListName;
    this.watchListDetails = watchListDetails;
    this.marketDataFrequency = marketDataFrequency;
    this.dataProviders = dataProviders;
  }

  public List<String> getStockSymbolsList() {
    return stockSymbolsList;
  }

  public void setStockSymbolsList(List<String> stockSymbolsList) {
    this.stockSymbolsList = stockSymbolsList;
  }

  public void setStockSymbolsListFromOperationList(List<OperationParameters> stockSymbolsList) {
    List<String> stringList = stockSymbolsList.stream().map(OperationParameters::getName).collect(Collectors.toList());
    this.stockSymbolsList = stringList;
  }

  public List<OperationParameters> getOperationParameterses() {
    return operationParameterses;
  }

  public void setOperationParameterses(List<OperationParameters> operationParameterses) {
    this.operationParameterses = operationParameterses;
  }

  public Integer getWatchListId() {
    return watchListId;
  }

  public void setWatchListId(Integer watchListId) {
    this.watchListId = watchListId;
  }

  public String getWatchListName() {
    return watchListName;
  }

  public void setWatchListName(String watchListName) {
    this.watchListName = watchListName;
  }

  public String getWatchListDetails() {
    return watchListDetails;
  }

  public void setWatchListDetails(String watchListDetails) {
    this.watchListDetails = watchListDetails;
  }

  public int getMarketDataFrequency() {
    return marketDataFrequency;
  }

  public void setMarketDataFrequency(int marketDataFrequency) {
    this.marketDataFrequency = marketDataFrequency;
  }

  public String getDataProviders() {
    // TODO m it should be List<providerID> like phone in social network
    return dataProviders;
  }

  public void setDataProviders(String dataProviders) {
    this.dataProviders = dataProviders;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Set<Stock> getStock() {
    return stock;
  }

  public void setStock(Set<Stock> stock) {
    this.stock = stock;
  }

  @Override
  public String toString() {
    return String
        .valueOf(" account id =  " + this.getAccount().getAccountId() + "\n " + "data set id = " + this.getWatchListId()
            + "\n " + "market data freq = " + this.getMarketDataFrequency() + "\n " + "data set name = "
            + this.getWatchListName() + "\n " + "data set description = " + this.getWatchListDetails() + "\n ");
  }
}
