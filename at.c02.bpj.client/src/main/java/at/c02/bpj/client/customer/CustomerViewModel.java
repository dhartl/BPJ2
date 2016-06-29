package at.c02.bpj.client.customer;


import java.util.List;
import java.util.Optional;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.primitives.Longs;

import at.c02.bpj.client.Async;
import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.offer.management.OfferManagementDialog;
import at.c02.bpj.client.service.CustomerService;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * Model für den {@link CustomerView}
 */
public class CustomerViewModel implements ViewModel {
	/**
	 * Liste aller Artikel
	 */
	
	private ObservableList<Customer> customers = FXCollections.observableArrayList();
	private CustomerService customerService;

	private FilteredList<Customer> filteredCustomerList = new FilteredList<>(customers);
	
	// Search Field Properties für Bindings
	private StringProperty searchCustomerID = new SimpleStringProperty();
	private StringProperty searchCustomerFirstName = new SimpleStringProperty();
	private StringProperty searchCustomerLastName = new SimpleStringProperty();
	private StringProperty searchCompanyName = new SimpleStringProperty();
	
	// Service Klassen mittels Construktor-Injection setzen
	public CustomerViewModel(CustomerService customerService) {
		Async.executeUILoad(customerService::getCustomer, customers::setAll);
		this.customerService = customerService;

		//loadArticles();
	}
	


	/**
	 * Lädt die Artikel
	 */
	public void loadCustomers() {
		Async.executeUILoad(customerService::getCustomer, customers::setAll);
	}

	public ObservableList<Customer> customerProperty() {
		return filteredCustomerList;
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers.setAll(customers);
	}

	/**
	 * Erstellt einen neuen Kunden
	 */
	public void newCustomer() {
		CustomerEditDialog dialog = new CustomerEditDialog();
		dialog.setCustomer(new Customer());
		Optional<Customer> customer = dialog.showAndWait();
		if (customer.isPresent()) {
			Customer newCustomer = customerService.saveCustomer(customer.get());
			customers.add(newCustomer);
		}
	}

	/**
	 * Bearbeitet den Kunden
	 * 
	 * @param customer
	 */
	public void editCustomer(Customer customer) {
		CustomerEditDialog dialog = new CustomerEditDialog();
		dialog.setCustomer(customer);
		Optional<Customer> newCustomer = dialog.showAndWait();
		if (newCustomer.isPresent()) {
			// Speichert den Kunden
			Customer savedCustomer = customerService.saveCustomer(newCustomer.get());
			// aktualisiert den Artikel in der Articles-Liste
			customers.set(customers.indexOf(customer), savedCustomer);
		}
	}

	/**
	 * Löscht den Artikel
	 * 
	 * @param customer
	 */

//	public void deleteArticle(Article article) {
//		// Sicherheitsabfrage
//		Alert alert = new Alert(AlertType.WARNING);
	// alert.setTitle("Artikel löschen");
//		alert.setHeaderText(null);
	// alert.setContentText(String.format("Möchten Sie den Artikel '%s' wirklich
	// löschen?", article.getName()));
//
//		Optional<ButtonType> result = alert.showAndWait();
//		if (result.orElse(ButtonType.CANCEL) == ButtonType.OK) {
//			articleService.deleteArticle(article);
//			loadArticles();
//		}
//	}
		public void onSearchButtonClick() {
		
		if (Strings.isNullOrEmpty(searchCustomerID.getValue()) &&  searchCustomerFirstName.getValue() == null &&
				searchCustomerID.getValue() == null &&  searchCustomerLastName.getValue() == null &&
				searchCompanyName.getValue() == null) {
		
				 Alert noInputAlert= new Alert(AlertType.WARNING);
			        noInputAlert.setHeaderText("Keine Eingabe vorhanden");
			        noInputAlert.setContentText("Bitte mindestens ein Suchkriterium eingeben");
			        noInputAlert.showAndWait();
		}
		
		filteredCustomerList.setPredicate(this::checkCustomerMatchesSearch);
		
		if (filteredCustomerList.isEmpty()) {
			Alert noMatchFoundAlert= new Alert(AlertType.INFORMATION);
	        noMatchFoundAlert.setHeaderText("Kein Angebot mit angegebenen Suchkriterien gefunden");
			noMatchFoundAlert.setContentText("Bitte prüfen Sie die angegebenen Suchkriterien");
	        noMatchFoundAlert.showAndWait();
		}
	}
		
		private boolean checkCustomerMatchesSearch(Customer customer) {
			String customerId = searchCustomerID.getValue();
			String customerFirstName = searchCustomerFirstName.getValue();
			String customerLastName = searchCustomerLastName.getValue();
			String companyName = searchCompanyName.getValue();

			if (!Strings.isNullOrEmpty(customerId) && !Objects.equal(customer.getCustomerId(), Longs.tryParse(customerId))) {
				return false;
			}
			
			if (!Strings.isNullOrEmpty(customerFirstName) && !Objects.equal(customer.getContactFirstName(), customerFirstName)) {
				return false;
			}
			if (!Strings.isNullOrEmpty(customerLastName) && !Objects.equal(customer.getContactLastName(), customerLastName)) {
				return false;
			}
			if (!Strings.isNullOrEmpty(companyName) && !Objects.equal(customer.getCompanyName(), companyName)) {
				return false;
			}


			return true;
		}
		

		public ObservableList<Customer> customerListProperty() {
			return filteredCustomerList;
		}
		public final StringProperty searchCustomerIdProperty() {
			return this.searchCustomerID;
		}
		public final java.lang.String getSearchCustomerId() {
			return this.searchCustomerIdProperty().get();
		}
		public final StringProperty searchCustomerFirstNameProperty() {
			return this.searchCustomerFirstName;
		}
		public final StringProperty searchCustomerLastNameProperty() {
			return this.searchCustomerLastName;
		}
		public final StringProperty searchCompanyNameProperty() {
			return this.searchCompanyName;
		}
		public final java.lang.String getSearchCustomerFirstName() {
			return this.searchCustomerFirstNameProperty().get();
		}
		public final java.lang.String getSearchCustomerLastName() {
			return this.searchCustomerLastNameProperty().get();
		}
		public final java.lang.String getSearchCompanyName() {
			return this.searchCompanyNameProperty().get();
		}
		public final void setSearchCustomerId(final java.lang.String searchCustomerId) {
			this.searchCustomerIdProperty().set(searchCustomerId);
		}


		
		
		
		
		
		
		
		
	// UC006 öffnen des OfferManagements
	public void openOfferManagement() {
		OfferManagementDialog dialog = new OfferManagementDialog();
		dialog.show();
	}

}
