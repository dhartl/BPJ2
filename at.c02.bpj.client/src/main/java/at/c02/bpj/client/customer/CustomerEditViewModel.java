package at.c02.bpj.client.customer;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Category;
import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.service.CategoryService;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
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


	/**
	 * setzt den zu bearbeitenden Artikel
	 * 
	 * @param customer
	 */
	public void editCustomer(Customer customer) {
		id.set(customer.getCustomerId());
		firstName.set(customer.getContactFirstName());
		firstName.set(customer.getContactLastName());
	}

	public Customer getCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId(id.get());
		customer.setContactFirstName(firstName.get());
		customer.setContactLastName(lastName.get());
		customer.setCompanyName(companyName.get());
		return customer;
	}
}
