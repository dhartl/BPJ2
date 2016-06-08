package at.c02.bpj.client.offer.management;



import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.sun.glass.events.WindowEvent;

import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferPosition;
import at.c02.bpj.client.service.CustomerService;
import at.c02.bpj.client.service.EmployeeService;
import at.c02.bpj.client.service.OfferPositionService;
import at.c02.bpj.client.service.OfferService;
import de.saxsys.mvvmfx.ViewModel;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;




/**
 * Model (Logik) für den {@link OfferManagementView}
 */
public class OfferManagementViewModel implements ViewModel {
	

	// Liste aller Angebote + Mitarbeiter + Kunden + A.Positionen (kann von Datenbank hier Daten in Observ.List hinein geben)
	private ObservableList<Offer> offersList = FXCollections.observableArrayList();
	private ObservableList<Employee> employeesList = FXCollections.observableArrayList();
	private ObservableList<Customer> customersList = FXCollections.observableArrayList();
	private ObservableList<OfferPosition> offerPositionsList = FXCollections.observableArrayList();

	
	// FilteredList erstellen für alle SuchMethoden (=gefilterte Tabelle nach Suche)
	private FilteredList<Offer> filteredData;
		


	// Objekt Properties für einzelne Klassen in Verwendung (comboBox)
	private ObjectProperty<Employee> employee = new SimpleObjectProperty<>();
	private ObjectProperty<Customer> customer = new SimpleObjectProperty<>();
	
	// Service Klassen mittels Construktor-Injection setzen
	public OfferManagementViewModel(EmployeeService employeeService /*, CustomerService customerService,
			OfferService offerService, OfferPositionService offerPositionService*/) {
		employeesList.addAll(employeeService.getEmployee());
		/*customersList.addAll(customerService.getCustomer());
		offersList.addAll(offerService.getOffer());
		offerPositionsList.addAll(offerPositionService.getOfferPosition());*/
	}
	
	
	//Propertyies für Bindings (1x Object; 1x ObservableList)
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
	
	
	// Getter/Setter Filtered List
	public FilteredList<Offer> getFilteredData() {
		return filteredData;
	}
	public void setFilteredData(FilteredList<Offer> filteredData) {
		this.filteredData = filteredData;
	}

	
	
	// Search: Offer-Number
	 public EventHandler<ActionEvent> FindByOfferNumber() {
/*
		// auf Filteränderung achten
	        controler.getOfferField().textProperty().addListener((observable, oldValue, newValue) -> {
	            controler.filteredData.setPredicate(offer -> {
	            	 // wenn nichts im Filter, dann alle angezeigt 
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }
	                
	             // Compare ANr. 
	                String lowerCaseFilter = newValue.toLowerCase();
	                               
	                for (int i=0; i <= offersList.size(); i++) {
	                	if (offer.getOfferId().toString().toLowerCase().contains(lowerCaseFilter)) {
	                    controler.filteredData.addAll(offersList);
	                	return true; // Filter matches ANr..
	                	}
	                }
	                	return false; // Kein Match
	            });
	  
	        });
	        
*/  
			return null;
	}

	 
	 // Search: DatePicker Period
	public EventHandler<ActionEvent> FindByDate() {
	
/* --> Problem: localDate convert in Date format! 
 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		
		//Datum mit Strings vergleichen
		LocalDate startDate = controler.getDateStartField().getValue();
		LocalDate endDate = controler.getDateEndField().getValue();
	
		Offer date = new Offer();
		
		int compareDateFrom = date.getCreatedDt().compareTo(formatter.format(startDate));
		
		if (compareDateFrom >= 0) {
			
			int compareDateTo = date.completedDtProperty().compareTo(endDate);
				if (compareDateTo <= 0 ) {
					// hinzufügen der passenden Daten in filteredData
					filteredData.addAll(offersList);	
			}
		}	
*/
		return null;
	}

	//Search: Customer 
	public EventHandler<ActionEvent> FindByCustomer() {
/*		
		String cust = controler.getCustomerField().toString();
		
		Customer customer = new Customer();
	
		int compCustomer = customer.getCompanyName().toString().compareTo(cust);
			if (compCustomer == 0 ) {
			
			// hinzufügen der passenden Daten in filteredData
				controler.filteredData.addAll(offersList);
			}
		// müsste hier eigentlich noch wenn buchstaben übereinstimmen auch schon
		// finden - nicht nur gesamter String
*/
		return null;

	}

	// Search: Employee
	public EventHandler<ActionEvent> FindByEmployee() {
	
		// wenn Bedingung true = match 
		filteredData = new FilteredList<>(offersList, p -> true);
		
		
		 OfferManagementView omv = new OfferManagementView();
		 omv.getOfferField().textProperty().addListener((observable, oldValue, newValue) -> {
	            filteredData.setPredicate(offer -> {
	                // if filter text leer = alle anzeigen
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }
	                String lowerCaseFilter = newValue.toLowerCase();

	                if (offer.getEmployee().getLastname().toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter matched MA-Nachname.
	                }
	                return false; // Kein match.
	            });
	        });

	        SortedList<Offer> sortedData = new SortedList<>(filteredData);

	        // Binden der SortedList zur OfferTable
	        sortedData.comparatorProperty().bind(omv.offerTable.comparatorProperty());

	        // Daten in Tabelle laden 
	        omv.offerTable.setItems(sortedData);
	        
	        return null;
	    }


	
/*Variante
		
		String emp = controler.getCustomerField().getValue().toString();
		
		Employee employee = new Employee();
	
	for (Offer list : offersList) {
		int compEmployee = employee.getLastname().toString().compareTo(emp);
			if (compEmployee == 0 ) {
			filteredData.add(offersList);
			// hinzufügen der passenden Daten in filteredData
				filteredData.addAll(offersList);
			}
		// müsste hier eigentlich noch wenn buchstaben übereinstimmen auch schon
		// finden - nicht nur gesamter String

		return null;

	}
*/
	
	public EventHandler<ActionEvent> quitOfferManagement() {
		Platform.exit();
		return null;
	}
		
		
	
	
}




