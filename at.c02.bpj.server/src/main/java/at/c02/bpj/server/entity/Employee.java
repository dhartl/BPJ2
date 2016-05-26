package at.c02.bpj.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee extends BaseEntity<Long> {
	private static final long serialVersionUID = 3146279783023185439L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employeeId")
	private Long employeeId;
	@Column(name = "username", nullable = false, length = 100)
	private String username;
	@Column(name = "password", length = 100)
	private String password;
	@Column(name = "firstname", length = 100)
	private String firstname;
	@Column(name = "lastname", length = 100)
	private String lastname;
	@Column(name = "email", length = 255)
	private String email;
	@Column(name = "lastLoginDt")
	private Date lastLoginDt;

	@Override
	public Long getId() {
		return getEmployeeId();
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLoginDt() {
		return lastLoginDt;
	}

	public void setLastLoginDt(Date lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}

}
