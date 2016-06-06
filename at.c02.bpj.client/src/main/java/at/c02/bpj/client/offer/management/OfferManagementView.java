package at.c02.bpj.client.offer.management;


import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.management.modelmbean.ModelMBean;
import javax.sound.midi.ControllerEventListener;

import org.controlsfx.control.table.TableFilter;

import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferPosition;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import javafx.util.StringConverter;

/**
 * Controller für OfferManagement.fxml.
 */
public class OfferManagementView implements FxmlView<OfferManagementViewModel>, Initializable {

	@InjectViewModel
	private OfferManagementViewModel model;
	
	// Grid Suchfelder
	@FXML
	private GridPane searchGridPane;
	@FXML
	private Label offerField;
	@FXML 
	private DatePicker dateStartField;
	@FXML 
	private DatePicker dateEndField;
	@FXML 
	private ComboBox<Customer> customerField;
	@FXML 
	private ComboBox<Employee> employeeField;

	@FXML 
	private Button searchButton; 
	
	//TableView: Spalten Tabellen für Angebotsmanagment
//---> Daniel: brauche Table View mit mehreren Datentypen von Offer, Employee, Customer ... ???
	@FXML
	public TableView<Offer> offerTable;
	@FXML
	private TableColumn<Offer, Long> offerNumberColumn;
	@FXML
	private TableColumn<Offer, Date> createdDateColumn;
	@FXML
	private TableColumn<Offer, Double> completedDateColumn;
	@FXML
	private TableColumn<Employee, String> employeeColumn;
	@FXML
	private TableColumn<Customer, String> customerColumn;
	@FXML
	private TableColumn<Offer, String> statusColumn;
	@FXML
	private TableColumn<OfferPosition, DecimalFormat> priceColumn;
//-- > wie/wo berechnen Menge * Preis ? 
	
	//FilteredList erstellen für alle SuchMethoden
	public FilteredList<Offer> filteredData = new FilteredList<>(model.offerPropertyList(), p -> true);
	

	// initialize --> Tabellen Werte wo bereits bei Aufruf/Beginn UC Werte enthalten sein sollen
	@Override
	public void initialize(URL location, ResourceBundle resources) { 
		
		Bindings.bindContent(offerTable.itemsProperty().get(), model.offerPropertyList());
		
		//Alle involvierten Spalten binden -- Syntax: Bindings (List<> , ObservableList<>)
		offerNumberColumn.setCellValueFactory(new PropertyValueFactory<>("offerId"));
		createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
		completedDateColumn.setCellValueFactory(new PropertyValueFactory<>("completedDate"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
	
		//für ComboBox Employee (Binding zwischen ComboBox Feld und aus Modell mit der Employee Property 
		Bindings.bindContent(employeeField.itemsProperty().get(), model.employeesPropertyList());
		employeeField.valueProperty().bindBidirectional(model.employeeProperty());	
		
		employeeField.setConverter(new StringConverter<Employee>() {

			@Override
			public String toString(Employee employee) {
				return employee.getLastname();
			}
			@Override
			public Employee fromString(String string) {
				return null;
			}
		});
		
		//Binding ComboBox Customer
		Bindings.bindContent(customerField.itemsProperty().get(), model.customerPropertyList());
		customerField.valueProperty().bindBidirectional(model.customerProperty());	
		
		customerField.setConverter(new StringConverter<Customer>() {

			@Override
			public String toString(Customer customer) {
				return customer.getCompanyName();
			}
			@Override
			public Customer fromString(String string) {
				return null;
			}
		});
		
	    }
		 
		//Getter für SuchfunktionenFelder UI
		public final Label getOfferField() {
			return offerField;
		}
		public final DatePicker getDateStartField() {
			return dateStartField;
		}
		public final DatePicker getDateEndField() {
			return dateEndField;
		}
		public final ComboBox<Customer> getCustomerField() {
			return customerField;
		}
		public final ComboBox<Employee> getEmployeeField() {
			return employeeField;
		}


		// wenn Suchen klickt, soll 1. ANR: logik aufrufen, dann Zeitraum etc...(immer nur mit filteredList danach weiter) 
		//FilterTable aufrufen
		public void onSearchButtonClick() {
			
			//wenn kein Suchfeld befüllt wurde (=alle leer)
			if (offerField.toString().isEmpty() && dateStartField.equals(null) && dateEndField.equals(null) &&
					customerField.toString().isEmpty() && employeeField.toString().isEmpty()) {
		
				 Alert noInputAlert= new Alert(AlertType.INFORMATION);
			        noInputAlert.setHeaderText("Keine Eingabe");
			        noInputAlert.setContentText("Bitte mindestens ein Suchkriterium eingeben");
			        noInputAlert.showAndWait();	
			}
	
			//wenn mindestens 1 Feld Input hat 
			else {
				
				//fügt Ergebnisse aus Suchfeld ANr. der FilteredList hinzu 
				searchButton.setOnAction(model.FindByOfferNumber());
				searchButton.setOnAction(model.FindByDate());
				searchButton.setOnAction(model.FindByCustomer());
				searchButton.setOnAction(model.FindByEmployee());
				
				SortedList<Offer> sortedData = new SortedList<>(filteredData);
			     
			        // SortedList binden, comparator zum TableView comparator.
			        sortedData.comparatorProperty().bind(offerTable.comparatorProperty());
			       
			        // Sortierte und gefilterte Daten in Tabelle einfügen
			        offerTable.setItems(sortedData); 
			}
			
			// Filterliste löschen 
			filteredData.clear();
		}

	}
