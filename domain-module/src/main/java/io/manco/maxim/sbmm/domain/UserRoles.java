package io.manco.maxim.sbmm.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class UserRoles implements Serializable {

  private static final long serialVersionUID = 4880880282266891718L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_role_id")
  private Integer userRoleId;

  @ManyToMany
  @JoinTable(name = "account_role", joinColumns = {
      @JoinColumn(name = "role_id", referencedColumnName = "user_role_id") }, inverseJoinColumns = {
          @JoinColumn(name = "user_id", referencedColumnName = "account_id") })
  private Set<Account> account = new HashSet<Account>(0);;

  @Column(name = "role")
  private String role;

  public Integer getUserRoleId() {
    return userRoleId;
  }

  public void setUserRoleId(Integer userRoleId) {
    this.userRoleId = userRoleId;
  }

  public Set<Account> getAccount() {
    return account;
  }

  public void setAccount(Set<Account> account) {
    this.account = account;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

}
