package com.hossein.FlightBooking.models.infrastructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class UserModel {
	
	@Id
	@SequenceGenerator(
			sequenceName = "seq_user",
			name = "seq_user",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "seq_user"
			)
	private Long id;
	
	@Column(name = "EMAIL", unique = true)
	private String email;
	
	@Column(name = "USERNAME", unique = true)
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID")
	private Roles role;

	public UserModel() {
		super();
	}
	
	public UserModel(String email, String username, String password, Roles role) {
		this.email = email;
		this.password = password;
		this.role = role;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}
	
}
