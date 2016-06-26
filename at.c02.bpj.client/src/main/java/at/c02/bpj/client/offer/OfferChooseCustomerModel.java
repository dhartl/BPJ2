package at.c02.bpj.client.offer;

import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.service.CustomerService;
import at.c02.bpj.client.service.EmployeeService;
import at.c02.bpj.client.service.OfferService;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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

    public SimpleObjectProperty<Long> idProperty() {
	return id;
    }
    //
    // public ObjectProperty<Date> createdDtProperty() {
    // return createdDt;
    // }

    /**
     * Erstellt ein neues Angebot
     */
    public void newOffer() {

	Offer newOffer = new Offer();
	// java.util.Date date = new java.util.Date();
	// java.sql.Date d = new Date(date.getTime());
	// newOffer.setCreatedDt(d);
	// newOffer.setStatus("CREATED");
	newOffer.setEmployee(employee.get());
	newOffer.setCustomer(customer.get());

	// newOffer.setOfferId(id.get());
	// Offer offer = new Offer();
	Offer createdOffer = offerService.saveOffer(newOffer);
	// // id.set(offer.getOfferId());
	// // createdDt.set(offer.getCreatedDt());

    }

    // Mitarbeiterwahl
    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    private ObjectProperty<Employee> employee = new SimpleObjectProperty<>();

    // Properties
    public ObjectProperty<Employee> employeeProperty() {
	return employee;
    }

    public ObservableList<Employee> employeeListProperty() {
	return employeeList;
    }

    // Kundenauswahl
    private ObservableList<Customer> customersList = FXCollections.observableArrayList();

    private ObjectProperty<Customer> customer = new SimpleObjectProperty<>();

    // Properties
    public ObjectProperty<Customer> customerProperty() {
	return customer;
    }

    public ObservableList<Customer> customerListProperty() {
	return customersList;
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
