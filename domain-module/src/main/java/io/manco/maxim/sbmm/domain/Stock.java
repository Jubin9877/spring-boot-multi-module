package io.manco.maxim.sbmm.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "stock")
public class Stock implements Serializable {
	
  public Stock(String name) {
		super();
		this.name = name;
	}

	public Stock(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Stock() {
	}

	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(max = 45)
  @Column(name = "name", length = 45)
  private String name;

  @ManyToMany(mappedBy = "stock")
  private Set<WatchListDesc> watchListDescs = new HashSet<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<WatchListDesc> getWatchListDescs() {
    return watchListDescs;
  }

  public void setWatchListDescs(Set<WatchListDesc> watchListDescs) {
    this.watchListDescs = watchListDescs;
  }

}
