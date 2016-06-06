package at.c02.bpj.client.offer.management;

import java.util.List;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Category;
import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferPosition;
import at.c02.bpj.client.service.ArticleService;
import at.c02.bpj.client.service.CategoryService;
import at.c02.bpj.client.service.CustomerService;
import at.c02.bpj.client.service.EmployeeService;
import at.c02.bpj.client.service.OfferPositionService;
import at.c02.bpj.client.service.OfferService;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableRow;


/**
 * Model für den {@link OfferManagementView}
 */
public class OfferManagementViewModel implements ViewModel {
	

	// Liste aller Angebote + Mitarbeiter + Kunden + APositionen (kann von Datenbank hier Daten in Observ.List hinein geben)
	private ObservableList<Offer> offersList = FXCollections.observableArrayList();
	private ObservableList<Employee> employeesList = FXCollections.observableArrayList();
	private ObservableList<Customer> customersList = FXCollections.observableArrayList();
	private ObservableList<OfferPosition> offerPositionsList = FXCollections.observableArrayList();
	//CheckBox  (hat eigene Klasse CheckBoxCellFactory 
	private SimpleBooleanProperty checked = new SimpleBooleanProperty(false);

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
	
	//CheckBox 
	public SimpleBooleanProperty checkedProperty() {
	        return this.checked;
	    }

	public java.lang.Boolean getChecked() {
	        return this.checkedProperty().get();
	}

	public void setChecked(final java.lang.Boolean checked) {
	        this.checkedProperty().set(checked);
	}
	
	
	// ObjectProperties Getter (für Bindings)
	public ObjectProperty<Employee> employeeProperty() {
		return employee;
	}
	
	


	

	
	
}


