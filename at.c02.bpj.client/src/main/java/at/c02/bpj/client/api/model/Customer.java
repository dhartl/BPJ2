package at.c02.bpj.client.api.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {


	private ObjectProperty<Long> customerId = new SimpleObjectProperty<>();
	private StringProperty companyName = new SimpleStringProperty();
	private StringProperty contactFirstName = new SimpleStringProperty();
	private StringProperty contactLastName = new SimpleStringProperty();
	

	// kein Argument-Konstruktor UNBEDINGT erforderlich
	public Customer() {
	}

	
	public final ObjectProperty<Long> customerIdProperty() {
		return this.customerId;
	}
	
	public Long getCustomerId() {
		return this.customerIdProperty().get();
	}

	public void setCustomerId(final Long customerId) {
		this.customerIdProperty().set(customerId);
	}


	
	public final StringProperty companyNameProperty() {
		return this.companyName;
	}
	
	public final java.lang.String getCompanyName() {
		return this.companyNameProperty().get();
	}

	public final void setCompanyName(final java.lang.String companyName) {
		this.companyNameProperty().set(companyName);
	}


	
	
	public final StringProperty contactFirstNameProperty() {
		return this.contactFirstName;
	}
	
	public final java.lang.String getContactFirstName() {
		return this.contactFirstNameProperty().get();
	}

	public final void setContactFirstName(final java.lang.String contactFirstName) {
		this.contactFirstNameProperty().set(contactFirstName);
	}

	
	public final StringProperty contactLasttNameProperty() {
		return this.contactLastName;
	}
	
	public final java.lang.String getContactLastName() {
		return this.contactFirstNameProperty().get();
	}

	public final void setContactLastName(final java.lang.String contactLastName) {
		this.contactFirstNameProperty().set(contactLastName);
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerId + ", companyName=" + companyName + ", contactFirstName="
				+ contactFirstName + ", contactLastName=" + contactLastName + "]";
	}
	
}
