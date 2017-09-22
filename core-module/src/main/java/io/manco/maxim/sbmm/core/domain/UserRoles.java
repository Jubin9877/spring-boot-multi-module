package io.manco.maxim.sbmm.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class UserRoles implements Serializable {

  private static final long serialVersionUID = 4880880282266891718L;

  @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_role_id")
	private Integer userRoleId;
	
	@ManyToOne
	@JoinColumn(name="account_id",referencedColumnName="account_id")
	private Account account;
	
	@Column(name="role")
	private String role;

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
