package at.c02.bpj.client.offer;

import java.util.Date;

import at.c02.bpj.client.Async;
import at.c02.bpj.client.AuthContext;
import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferStatus;
import at.c02.bpj.client.service.CustomerService;
import at.c02.bpj.client.service.EmployeeService;
import at.c02.bpj.client.service.OfferService;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OfferChooseCustomerModel implements ViewModel {

	// Angebot - Properties
	private CustomerService customerService;

	private ObjectProperty<Offer> offer = new SimpleObjectProperty<>();
	private SimpleObjectProperty<Long> id = new SimpleObjectProperty<>();

	public ObjectProperty<Offer> offerProperty() {
		return offer;
	}

	/**
	 * Erstellt ein neues Angebot
	 */
	public void newOffer() {

		Offer newOffer = new Offer();
		newOffer.setCreatedDt(new Date());
		newOffer.setStatus(OfferStatus.CREATED);
		newOffer.setEmployee(selectedEmployee.get());
		newOffer.setCustomer(selectedCustomer.get());
		newOffer.setOfferId(id.get());
		offer.set(newOffer);

	}

	// Mitarbeiterwahl
	private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

	private ObjectProperty<Employee> employee = new SimpleObjectProperty<>();

	private ObjectProperty<Employee> selectedEmployee = new SimpleObjectProperty<>();

	// Properties
	public ObjectProperty<Employee> employeeProperty() {
		return employee;
	}

	public ObservableList<Employee> employeeListProperty() {
		return employeeList;
	}

	public ObjectProperty<Employee> selectedEmployeeProperty() {
		return selectedEmployee;
	}

	// Kundenauswahl

	private ObservableList<Customer> customersList = FXCollections.observableArrayList();

	private ObjectProperty<Customer> customer = new SimpleObjectProperty<>();

	private ObjectProperty<Customer> selectedCustomer = new SimpleObjectProperty<>();

	// Properties

	public StringProperty streetProperty() {
		return selectedCustomer.get().streetProperty();
	}

	public ObjectProperty<Customer> customerProperty() {
		return customer;
	}

	public ObservableList<Customer> customerListProperty() {
		return customersList;
	}

	public ObjectProperty<Customer> selectedCustomerProperty() {
		return selectedCustomer;
	}

	public OfferChooseCustomerModel(EmployeeService employeeService, CustomerService customerService,
			OfferService offerService) {
		this.customerService = customerService;
		Async.executeUILoad(customerService::getCustomer, customersList::setAll);
		selectedEmployee.set(AuthContext.getInstance().getCurrentUser());

	}

}
