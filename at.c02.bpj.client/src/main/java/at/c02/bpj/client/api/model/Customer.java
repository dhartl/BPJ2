package at.c02.bpj.client.api.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {

    private ObjectProperty<Long> customerID = new SimpleObjectProperty<>();
    private StringProperty companyName = new SimpleStringProperty();
    private StringProperty contactFirstName = new SimpleStringProperty();
    private StringProperty contactLastName = new SimpleStringProperty();
    private StringProperty city = new SimpleStringProperty();
    private StringProperty street = new SimpleStringProperty();
    private StringProperty houseNr = new SimpleStringProperty();
    private StringProperty postCode = new SimpleStringProperty();

    // kein Argument-Konstruktor UNBEDINGT erforderlich
    public Customer() {
    }

    public final ObjectProperty<Long> customerIdProperty() {
	return this.customerID;
    }

    public Long getCustomerID() {
	return this.customerIdProperty().get();
    }

    public void setCustomerID(final Long customerID) {
	this.customerIdProperty().set(customerID);
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
	return "Customer [customerID=" + customerID + ", companyName=" + companyName + ", contactFirstName="
		+ contactFirstName + ", contactLastName=" + contactLastName + "]";
    }

}
