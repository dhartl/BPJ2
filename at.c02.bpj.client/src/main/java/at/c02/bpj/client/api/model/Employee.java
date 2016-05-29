package at.c02.bpj.client.api.model;

import java.sql.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {
	
	private ObjectProperty<Long> employeeId = new SimpleObjectProperty<>();
	private StringProperty username = new SimpleStringProperty();
	private StringProperty password = new SimpleStringProperty();
	private StringProperty firstname = new SimpleStringProperty();
	private StringProperty lastname = new SimpleStringProperty();
	private StringProperty email = new SimpleStringProperty();
	private ObjectProperty<Date> lastLoginDt = new SimpleObjectProperty<>();
	

	public Employee() {
		
	}


	// RM - Generate Java FX - Getter and Setters 
	public final ObjectProperty<Long> employeeIdProperty() {
		return this.employeeId;
	}
	


	public final Long getEmployeeId() {
		return this.employeeIdProperty().get();
	}
	


	public final void setEmployeeId(final Long employeeId) {
		this.employeeIdProperty().set(employeeId);
	}
	


	public final StringProperty usernameProperty() {
		return this.username;
	}
	


	public final String getUsername() {
		return this.usernameProperty().get();
	}
	


	public final void setUsername(final String username) {
		this.usernameProperty().set(username);
	}
	


	public final StringProperty passwordProperty() {
		return this.password;
	}
	


	public final String getPassword() {
		return this.passwordProperty().get();
	}
	


	public final void setPassword(final String password) {
		this.passwordProperty().set(password);
	}
	


	public final StringProperty firstnameProperty() {
		return this.firstname;
	}
	


	public final String getFirstname() {
		return this.firstnameProperty().get();
	}
	


	public final void setFirstname(final String firstname) {
		this.firstnameProperty().set(firstname);
	}
	


	public final StringProperty lastnameProperty() {
		return this.lastname;
	}
	


	public final String getLastname() {
		return this.lastnameProperty().get();
	}
	


	public final void setLastname(final String lastname) {
		this.lastnameProperty().set(lastname);
	}
	


	public final StringProperty emailProperty() {
		return this.email;
	}
	


	public final String getEmail() {
		return this.emailProperty().get();
	}
	


	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}
	


	public final ObjectProperty<Date> lastLoginDtProperty() {
		return this.lastLoginDt;
	}
	


	public final Date getLastLoginDt() {
		return this.lastLoginDtProperty().get();
	}
	


	public final void setLastLoginDt(final Date lastLoginDt) {
		this.lastLoginDtProperty().set(lastLoginDt);
	}


	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", username=" + username + ", password=" + password
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", lastLoginDt="
				+ lastLoginDt + "]";
	}
	
	
	
	
	

}
