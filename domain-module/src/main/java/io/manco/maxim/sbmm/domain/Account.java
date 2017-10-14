package io.manco.maxim.sbmm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "account")
public class Account implements Serializable, UserDetails {

	public Account(String accountName, String eMail, String additionalInfo, String password,
	    List<WatchListDesc> dataSets, String creationDate) {
		this.accountName = accountName;
		this.email = eMail;
		this.additionalInfo = additionalInfo;
		this.password = password;
		this.dataSets = dataSets;
		this.creationDate = creationDate;
	}

	public Account(int id, String accountName, String eMail, String additionalInfo, String password,
	    List<WatchListDesc> dataSets, String creationDate) {
		this.accountId = id;
		this.accountName = accountName;
		this.email = eMail;
		this.additionalInfo = additionalInfo;
		this.password = password;
		this.dataSets = dataSets;
		this.creationDate = creationDate;
	}

	private static final long serialVersionUID = 6733182399245205238L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Integer accountId;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "email")
	private String email;

	@Column(name = "additional_info")
	private String additionalInfo;

	@Column(name = "password")
	private String password;

	@Column(name = "image")
	@Lob
	private byte[] image;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "creation_date")
	private String creationDate;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<WatchListDesc> dataSets;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "account_role", joinColumns = {
	    @JoinColumn(name = "user_id", referencedColumnName = "account_id") }, inverseJoinColumns = {
	        @JoinColumn(name = "role_id", referencedColumnName = "user_role_id") })
	private Set<UserRoles> roles = new HashSet<UserRoles>(0);

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<WatchListDesc> getDataSets() {
		return dataSets;
	}

	public void setDataSets(List<WatchListDesc> watchLists) {
		this.dataSets = watchLists;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRoles> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRoles> roles) {
		this.roles = roles;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
		for (UserRoles role : roles) {
			grantedAuths.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return grantedAuths;
	}

	@Override
	public String getUsername() {
		return accountName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}
