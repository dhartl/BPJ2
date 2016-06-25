package at.c02.bpj.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends ModLogEntity<Long> {
    private static final long serialVersionUID = 897313950884616308L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerId")
    private Long customerId;
    @Column(name = "companyName", nullable = false, length = 255)
    private String companyName;
    @Column(name = "street", length = 255)
    private String street;
    @Column(name = "houseNr", length = 100)
    private String houseNr;
    @Column(name = "postCode", length = 20)
    private String postCode;
    @Column(name = "city", length = 255)
    private String city;
    @Column(name = "contactFirstName", length = 100)
    private String contatcFirstName;
    @Column(name = "contactLastName", length = 100)
    private String contactLastName;
    @Column(name = "contactEmail", length = 255)
    private String contactEmail;
    @Enumerated(EnumType.STRING)
    @Column(name = "contactGender")
    private Gender contactGender;
    @Column(name = "contactPhoneNr", length = 50)
    private String contactPhoneNr;

    @Override
    public Long getId() {
	return getCustomerId();
    }

    public Long getCustomerId() {
	return customerId;
    }

    public void setCustomerId(Long customerId) {
	this.customerId = customerId;
    }

    public String getCompanyName() {
	return companyName;
    }

    public void setCompanyName(String companyName) {
	this.companyName = companyName;
    }

    public String getStreet() {
	return street;
    }

    public void setStreet(String street) {
	this.street = street;
    }

    public String getHouseNr() {
	return houseNr;
    }

    public void setHouseNr(String houseNr) {
	this.houseNr = houseNr;
    }

    public String getPostCode() {
	return postCode;
    }

    public void setPostCode(String postCode) {
	this.postCode = postCode;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getContatcFirstName() {
	return contatcFirstName;
    }

    public void setContatcFirstName(String contatcFirstName) {
	this.contatcFirstName = contatcFirstName;
    }

    public String getContactLastName() {
	return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
	this.contactLastName = contactLastName;
    }

    public String getContactEmail() {
	return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
	this.contactEmail = contactEmail;
    }

    public Gender getContactGender() {
	return contactGender;
    }

    public void setContactGender(Gender contactGender) {
	this.contactGender = contactGender;
    }

    public String getContactPhoneNr() {
	return contactPhoneNr;
    }

    public void setContactPhoneNr(String contactPhoneNr) {
	this.contactPhoneNr = contactPhoneNr;
    }

}
