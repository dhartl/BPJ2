package at.c02.bpj.client.offer;

import java.sql.Date;

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
    private OfferService offerService;
    private CustomerService customerService;
    private EmployeeService employeeService;

    // OfferService wird mittels Construktor-Injection gesetzt

    private ObjectProperty<Offer> offer = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Long> id = new SimpleObjectProperty<>();

    // private ObjectProperty<Date> createdDt = new SimpleObjectProperty<>();

    public ObjectProperty<Offer> offerProperty() {
	return offer;
    }

    // public SimpleObjectProperty<Long> idProperty() {
    // return id;
    // }
    //
    // public ObjectProperty<Date> createdDtProperty() {
    // return createdDt;
    // }

    /**
     * Erstellt ein neues Angebot
     */
    public void newOffer() {

	Offer newOffer = new Offer();
	java.util.Date date = new java.util.Date();
	java.sql.Date d = new Date(date.getTime());
	newOffer.setCreatedDt(d);
	newOffer.setStatus(OfferStatus.CREATED);
	newOffer.setEmployee(selectedEmployee.get());
	newOffer.setCustomer(selectedCustomer.get());
	newOffer.setOfferId(id.get());
	// Offer offer = new Offer();
	// offer.setValue(offerService.saveOffer(newOffer));
	// // id.set(offer.getOfferId());
	// // createdDt.set(offer.getCreatedDt());
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
	this.offerService = offerService;
	this.customerService = customerService;
	this.employeeService = employeeService;
	employeeList.addAll(employeeService.getEmployee());
	customersList.addAll(customerService.getCustomer());
    }

}
