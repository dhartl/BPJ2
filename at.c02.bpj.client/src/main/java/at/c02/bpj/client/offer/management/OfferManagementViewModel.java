package at.c02.bpj.client.offer.management;



import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferPosition;
import at.c02.bpj.client.service.CustomerService;
import at.c02.bpj.client.service.EmployeeService;
import at.c02.bpj.client.service.OfferPositionService;
import at.c02.bpj.client.service.OfferService;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;




/**
 * Model für den {@link OfferManagementView}
 */
public class OfferManagementViewModel implements ViewModel {
	

	// Liste aller Angebote + Mitarbeiter + Kunden + A.Positionen (kann von Datenbank hier Daten in Observ.List hinein geben)
	private ObservableList<Offer> offersList = FXCollections.observableArrayList();
	private ObservableList<Employee> employeesList = FXCollections.observableArrayList();
	private ObservableList<Customer> customersList = FXCollections.observableArrayList();
	private ObservableList<OfferPosition> offerPositionsList = FXCollections.observableArrayList();


	// Verbindung zu Service-Klassen herstellen
	private OfferService offerService;
	private EmployeeService employeeService;
	private OfferPositionService offerPositionService;
	private CustomerService customerService;

	//Objekt Properties für einzelne Klassen in Verwendung (comboBox)
	private ObjectProperty<Employee> employee = new SimpleObjectProperty<>();
	private ObjectProperty<Customer> customer = new SimpleObjectProperty<>();
	
	// Service Klassen mittels Construktor-Injection setzen
	//Alle Mitarbeiter aus Datenbank (vom Service Klasse Zugriff) der Observable List hinzufügen (ladet)
	public OfferManagementViewModel(EmployeeService employeeService, OfferService offerService, 
			OfferPositionService offerPositionService, CustomerService customerService) {
				employeesList.addAll(employeeService.getEmployee());
				customersList.addAll(customerService.getCustomer());
				offersList.addAll(offerService.getOffer());
				offerPositionsList.addAll(offerPositionService.getOfferPosition());
	}
	
	// Zugriff auf Controler Class
	private OfferManagementView controler; 
	
	// für alle ObservableLists eine Methode mit RückgabeWert schaffen (um in Bindings im View anzugeben)
	public ObservableList<Offer> offerPropertyList() {
		return offersList;
	}
	
	public ObservableList<Employee> employeesPropertyList() {
		return employeesList;
	}
	
	public ObservableList<Customer> customerPropertyList() {
		return customersList;
	}
	
	public ObservableList<OfferPosition> offerPostionPropertyList() {
		return offerPositionsList;
	}
	
	
	// ObjectProperties Getter (für Bindings)
	public ObjectProperty<Employee> employeeProperty() {
		return employee;
	}
	
	public ObjectProperty<Customer> customerProperty() {
		return customer;
	}

	
	//Getter/Setter von ServiceKlassen
	public OfferService getOfferService() {
		return offerService;
	}

	public void setOfferService(OfferService offerService) {
		this.offerService = offerService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public OfferPositionService getOfferPositionService() {
		return offerPositionService;
	}

	public void setOfferPositionService(OfferPositionService offerPositionService) {
		this.offerPositionService = offerPositionService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	
	// Search: Offer-Number
	 public EventHandler<ActionEvent> FindByOfferNumber() {
	   
		//auf Filteränderung achten
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
	        
	     
			return null;
	}

	 
	 // Search: DatePicker Period
	public EventHandler<ActionEvent> FindByDate() {
		
		//Datum mit Strings vergleichen
		String startDate = controler.getDateStartField().toString();
		String endDate = controler.getDateEndField().toString();
		
		Offer date = new Offer();
		int compareDateFrom = date.getCreatedDt().toString().compareTo(startDate);
		
		if (compareDateFrom >= 0) {
			int compareDateTo = date.completedDtProperty().toString().compareTo(endDate);
			if (compareDateTo <= 0 ) {
				
				// hinzufügen der passenden Daten in filteredData
				controler.filteredData.addAll(offersList);
			}
		}	
		return null;
	}

	//Search: Customer 
	public EventHandler<ActionEvent> FindByCustomer() {
		
		String cust = controler.getCustomerField().toString();
		
		Customer customer = new Customer();
	
		int compCustomer = customer.getCompanyName().toString().compareTo(cust);
			if (compCustomer == 0 ) {
			
				// hinzufügen der passenden Daten in filteredData
				controler.filteredData.addAll(offersList);
			}
	// müsste hier eigentlich noch wenn buchstaben übereinstimmen auch schon finden - nicht nur gesamter String

		return null;
	}

	// Search: Employee
	public EventHandler<ActionEvent> FindByEmployee() {
		
		String emp = controler.getCustomerField().toString();
		
		Employee employee = new Employee();
	
		int compEmployee = employee.getLastname().toString().compareTo(emp);
			if (compEmployee == 0 ) {
			
				// hinzufügen der passenden Daten in filteredData
				controler.filteredData.addAll(offersList);
			}
	// müsste hier eigentlich noch wenn buchstaben übereinstimmen auch schon finden - nicht nur gesamter String

		return null;
	}
	
}


