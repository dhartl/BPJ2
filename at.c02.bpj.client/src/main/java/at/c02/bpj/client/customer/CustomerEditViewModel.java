package at.c02.bpj.client.customer;


import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Gender;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Model für {@link CustomerEditView}
 */
public class CustomerEditViewModel implements ViewModel {

	private SimpleObjectProperty<Long> id = new SimpleObjectProperty<>();
	private StringProperty firstName = new SimpleStringProperty();
	private StringProperty lastName = new SimpleStringProperty();
	private StringProperty companyName = new SimpleStringProperty();
	private StringProperty street = new SimpleStringProperty();
	private StringProperty houseNr = new SimpleStringProperty();
	private StringProperty postCode = new SimpleStringProperty();
	private StringProperty city = new SimpleStringProperty();
	private StringProperty phoneNr = new SimpleStringProperty();
	private StringProperty email = new SimpleStringProperty();
	
	private ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
	private ObservableList<Gender> genderList =  FXCollections.observableArrayList();

	public CustomerEditViewModel() {
		genderList.addAll(Gender.values());
	}
	
	public ObservableList<Gender> genderListProperty() {
		return genderList;
	}
	
	public ObjectProperty<Gender> genderProperty() {
		return gender;
	}
	
	public SimpleObjectProperty<Long> idProperty() {
		return id;
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}
	public StringProperty lastNameProperty() {
		return lastName;
	}
	public StringProperty companyNameProperty() {
		return companyName;
	}
	public StringProperty streetProperty() {
		return street;
	}
	public StringProperty houseNrProperty() {
		return houseNr;
	}
	public StringProperty postCodeProperty() {
		return postCode;
	}
	public StringProperty cityProperty() {
		return city;
	}
	public StringProperty phoneNrProperty() {
		return phoneNr;
	}
	public StringProperty emailProperty() {
		return email;
	}

	/**
	 * setzt den zu bearbeitenden Artikel
	 * 
	 * @param customer
	 */
	public void editCustomer(Customer customer) {
		id.set(customer.getCustomerId());
		firstName.set(customer.getContactFirstName());
		lastName.set(customer.getContactLastName());
		companyName.set(customer.getCompanyName());
		city.set(customer.getCity());
		street.set(customer.getStreet());
		houseNr.set(customer.getHouseNr());
		postCode.set(customer.getPostCode());
		phoneNr.set(customer.getContactPhoneNr());
		email.set(customer.getContactEmail());
		gender.set(customer.getContactGender());
	}

	public Customer getCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId(id.get());
		customer.setContactFirstName(firstName.get());
		customer.setContactLastName(lastName.get());
		customer.setCompanyName(companyName.get());
		customer.setStreet(street.get());
		customer.setHouseNr(houseNr.get());
		customer.setPostCode(postCode.get());
		customer.setCity(city.get());
		customer.setContactPhoneNr(phoneNr.get());
		customer.setContactEmail(email.get());
		customer.setContactGender(gender.get());
		return customer;
	}
}
