package at.c02.bpj.client.offer.management;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.function.Predicate;

import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferPosition;
import at.c02.bpj.client.service.CustomerService;
import at.c02.bpj.client.service.EmployeeService;
import at.c02.bpj.client.service.OfferService;
import de.saxsys.mvvmfx.ViewModel;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

/**
 * Model (Logik) für den {@link OfferManagementView}
 */
public class OfferManagementViewModel implements ViewModel {

	private ObservableList<Offer> offersList = FXCollections.observableArrayList();
	private ObservableList<Employee> employeesList = FXCollections.observableArrayList();
	private ObservableList<Customer> customersList = FXCollections.observableArrayList();
	
//Daniel -->Properties von Offer Tabelle - noch nicht in Verwendung!
	private ObjectProperty<Long> offerId = new SimpleObjectProperty<>();
	private ObjectProperty<Date> createdDt = new SimpleObjectProperty<>();
	private ObjectProperty<Date> completedDt = new SimpleObjectProperty<>();
	private StringProperty status = new SimpleStringProperty();
	private ObjectProperty<Employee> employee = new SimpleObjectProperty<>();
	private ObjectProperty<Customer> customer = new SimpleObjectProperty<>();
	
	private ObservableList<OfferPosition> offerPositionsList = FXCollections.observableArrayList();

	
	// Service Klassen mittels Construktor-Injection setzen
	public OfferManagementViewModel(EmployeeService employeeService, CustomerService customerService,
			OfferService offerService) {
		employeesList.addAll(employeeService.getEmployee());
		customersList.addAll(customerService.getCustomer());
		offersList.addAll(offerService.getOffer());
	}

	
	// Properties für Bindings (1x Object; 1x ObservableList)
	public ObjectProperty<Employee> employeeProperty() {
		return employee;
	}
	public ObservableList<Employee> employeeListProperty() {
		return employeesList;
	}
	public ObjectProperty<Customer> customerProperty() {
		return customer;
	}
	public ObservableList<Customer> customerListProperty() {
		return customersList;
	}
	public ObservableList<Offer> offerListProperty() {
		return offersList;
	}
	public ObservableList<OfferPosition> offerPositionListProperty() {
		return offerPositionsList;
	}
	public ObjectProperty<Long> offerIdProperty() {
		return offerId;
	}
	public ObjectProperty<Date> createdDtProperty() {
		return createdDt;
	}
	public ObjectProperty<Date> completedDtProperty() {
		return completedDt;
	}
	public StringProperty statusProperty() {
		return status;
	}

	
//--> DAniel - wie implementieren?	
	//OfferPosition: Menge * Preis
	public double calcOfferPrice() {

		return (Double) null;
	}

	public EventHandler<ActionEvent> quitOfferManagement() {
		Platform.exit();
		
		return null;
	}
	
	
}
