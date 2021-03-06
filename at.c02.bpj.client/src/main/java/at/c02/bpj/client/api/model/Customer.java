package at.c02.bpj.client.api.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {

	private ObjectProperty<Long> customerId = new SimpleObjectProperty<>();
	private StringProperty companyName = new SimpleStringProperty();
	private StringProperty street = new SimpleStringProperty();
	private StringProperty houseNr = new SimpleStringProperty();
	private StringProperty postCode = new SimpleStringProperty();
	private StringProperty city = new SimpleStringProperty();
	private StringProperty contactFirstName = new SimpleStringProperty();
	private StringProperty contactLastName = new SimpleStringProperty();
	private StringProperty contactEmail = new SimpleStringProperty();
	private ObjectProperty<Gender> contactGender = new SimpleObjectProperty<>();
	private StringProperty contactPhoneNr = new SimpleStringProperty();

	// kein Argument-Konstruktor UNBEDINGT erforderlich
	public Customer() {
	}

	public final ObjectProperty<Long> customerIdProperty() {
		return this.customerId;
	}

	public final java.lang.Long getCustomerId() {
		return this.customerIdProperty().get();
	}

	public final void setCustomerId(final java.lang.Long customerId) {
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

	public final StringProperty contactFirstName() {
		return this.contactFirstName;
	}

	public final java.lang.String getContactFirstName() {
		return this.contactFirstName().get();
	}

	public final void setContactFirstName(final java.lang.String contactFirstName) {
		this.contactFirstName().set(contactFirstName);
	}

	public final StringProperty contactLasttName() {
		return this.contactLastName;
	}

	public final java.lang.String getContactLastName() {
		return this.contactLastName.get();
	}

	public final void setContactLastName(final java.lang.String contactLastName) {
		this.contactLastName.set(contactLastName);
	}

	// Property für Straße

	public final StringProperty streetProperty() {
		return this.street;
	}

	public final java.lang.String getStreet() {
		return this.streetProperty().get();
	}

	public final void setStreet(final java.lang.String street) {
		this.streetProperty().set(street);
	}

	// Property für Stadt

	public final StringProperty cityProperty() {
		return this.city;
	}

	public final java.lang.String getCity() {
		return this.cityProperty().get();
	}

	public final void setCity(final java.lang.String city) {
		this.cityProperty().set(city);
	}

	// Property für Hausnummer

	public final StringProperty houseNrProperty() {
		return this.houseNr;
	}

	public final java.lang.String getHouseNr() {
		return this.houseNrProperty().get();
	}

	public final void setHouseNr(final java.lang.String houseNr) {
		this.houseNrProperty().set(houseNr);
	}

	// Property für PLZ

	public final StringProperty postCodeProperty() {
		return this.postCode;
	}

	public final java.lang.String getPostCode() {
		return this.postCodeProperty().get();
	}

	public final void setPostCode(final java.lang.String postCode) {
		this.postCodeProperty().set(postCode);
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerId + ", companyName=" + companyName + ", contactFirstName="
				+ contactFirstName + ", contactLastName=" + contactLastName + "]";
	}

	public final StringProperty contactEmailProperty() {
		return this.contactEmail;
	}

	public final java.lang.String getContactEmail() {
		return this.contactEmailProperty().get();
	}

	public final void setContactEmail(final java.lang.String contactEmail) {
		this.contactEmailProperty().set(contactEmail);
	}

	public final ObjectProperty<Gender> contactGenderProperty() {
		return this.contactGender;
	}

	public final at.c02.bpj.client.api.model.Gender getContactGender() {
		return this.contactGenderProperty().get();
	}

	public final void setContactGender(final at.c02.bpj.client.api.model.Gender contactGender) {
		this.contactGenderProperty().set(contactGender);
	}

	public final StringProperty contactPhoneNrProperty() {
		return this.contactPhoneNr;
	}

	public final java.lang.String getContactPhoneNr() {
		return this.contactPhoneNrProperty().get();
	}

	public final void setContactPhoneNr(final java.lang.String contactPhoneNr) {
		this.contactPhoneNrProperty().set(contactPhoneNr);
	}

}
