package at.c02.bpj.client.offer.management;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.primitives.Longs;

import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Model (Logik) für den {@link OfferManagementView}
 */
public class OfferManagementViewModel implements ViewModel {
	
	private ObservableList<Offer> offersList = FXCollections.observableArrayList();
	private ObservableList<Employee> employeesList = FXCollections.observableArrayList();
	private ObservableList<Customer> customersList = FXCollections.observableArrayList();

	private FilteredList<Offer> filteredOfferList = new FilteredList<>(offersList);

	//Search Field Properties für Bindings 
	private StringProperty searchOfferId = new SimpleStringProperty();
	private ObjectProperty<LocalDate> searchStartDate = new SimpleObjectProperty<>();
	private ObjectProperty<LocalDate> searchEndDate = new SimpleObjectProperty<>();
	private ObjectProperty<Employee> searchEmployee = new SimpleObjectProperty<>();
	private ObjectProperty<Customer> searchCustomer = new SimpleObjectProperty<>();

	
	// Service Klassen mittels Construktor-Injection setzen
	public OfferManagementViewModel(EmployeeService employeeService, CustomerService customerService,
			OfferService offerService) {
		employeesList.addAll(employeeService.getEmployee());
		customersList.addAll(customerService.getCustomer());
		offersList.addAll(offerService.getOffer());
	}

	public EventHandler<ActionEvent> quitOfferManagement() {
		Platform.exit();

		return null;
	}

	public void onSearchButtonClick() {
		
		if (Strings.isNullOrEmpty(searchOfferId.getValue()) && searchStartDate.getValue() == null && 
				searchEndDate.getValue() == null && searchCustomer.getValue() == null &&
				searchEmployee.getValue() == null) {
		
				 Alert noInputAlert= new Alert(AlertType.WARNING);
			        noInputAlert.setHeaderText("Keine Eingabe vorhanden");
			        noInputAlert.setContentText("Bitte mindestens ein Suchkriterium eingeben");
			        noInputAlert.showAndWait();
		}
		
		filteredOfferList.setPredicate(this::checkOfferMatchesSearch);
		
		if (filteredOfferList.isEmpty()) {
			Alert noMatchFoundAlert= new Alert(AlertType.INFORMATION);
	        noMatchFoundAlert.setHeaderText("Kein Angebot mit angegebenen Suchkriterien gefunden");
	        noMatchFoundAlert.setContentText("Bitte prüfen Sie die angegebenen Suchkriterien");
	        noMatchFoundAlert.showAndWait();
		}
	}

	private boolean checkOfferMatchesSearch(Offer offer) {
		String offerId = searchOfferId.getValue();
		LocalDate startDate = searchStartDate.getValue();
		LocalDate endDate = searchEndDate.getValue();
		Employee emplyoee = searchEmployee.getValue();
		Customer customer = searchCustomer.getValue();

		if (!Strings.isNullOrEmpty(offerId) && !Objects.equal(offer.getOfferId(), Longs.tryParse(offerId))) {
			return false;
		}
		
		if (startDate != null && endDate != null) {
			System.out.println("test");
			if (!startDate.isBefore(offer.getCreatedDt().toLocalDate())) {
				return false;
			}
			if (!endDate.isAfter(offer.getCreatedDt().toLocalDate())) {
					System.out.print(offer.getCreatedDt().toLocalDate());
					return false;
			}
		}

		if (emplyoee != null && !Objects.equal(emplyoee.getEmployeeId(), offer.getEmployee().getEmployeeId())) {
			return false;
		}
		if (customer != null && !Objects.equal(customer.getCustomerId(), offer.getCustomer().getCustomerId())) {
			return false;
		}

		return true;
	}

	public ObservableList<Customer> customerListProperty() {
		return customersList;
	}

	public ObservableList<Employee> emplyoeeListProperty() {
		return employeesList;
	}

	public ObservableList<Offer> offerListProperty() {
		return filteredOfferList;
	}

	public final StringProperty searchOfferIdProperty() {
		return this.searchOfferId;
	}

	public final java.lang.String getSearchOfferId() {
		return this.searchOfferIdProperty().get();
	}

	public final void setSearchOfferId(final java.lang.String searchOfferId) {
		this.searchOfferIdProperty().set(searchOfferId);
	}

	public final ObjectProperty<LocalDate> searchStartDateProperty() {
		return this.searchStartDate;
	}

	public final LocalDate getSearchStartDate() {
		return this.searchStartDateProperty().get();
	}

	public final void setSearchStartDate(final LocalDate searchStartDate) {
		this.searchStartDateProperty().set(searchStartDate);
	}

	public final ObjectProperty<LocalDate> searchEndDateProperty() {
		return this.searchEndDate;
	}

	public final LocalDate getSearchEndDate() {
		return this.searchEndDateProperty().get();
	}

	public final void setSearchEndDate(final LocalDate searchEndDate) {
		this.searchEndDateProperty().set(searchEndDate);
	}

	public final ObjectProperty<Employee> searchEmployeeProperty() {
		return this.searchEmployee;
	}

	public final at.c02.bpj.client.api.model.Employee getSearchEmployee() {
		return this.searchEmployeeProperty().get();
	}

	public final void setSearchEmployee(final at.c02.bpj.client.api.model.Employee searchEmployee) {
		this.searchEmployeeProperty().set(searchEmployee);
	}

	public final ObjectProperty<Customer> searchCustomerProperty() {
		return this.searchCustomer;
	}

	public final at.c02.bpj.client.api.model.Customer getSearchCustomer() {
		return this.searchCustomerProperty().get();
	}

	public final void setSearchCustomer(final at.c02.bpj.client.api.model.Customer searchCustomer) {
		this.searchCustomerProperty().set(searchCustomer);
	}

}
