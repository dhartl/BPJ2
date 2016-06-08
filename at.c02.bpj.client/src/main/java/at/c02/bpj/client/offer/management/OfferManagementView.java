package at.c02.bpj.client.offer.management;


import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

/**
 * Controller (Darstellung) für OfferManagement.fxml.
 */
public class OfferManagementView implements FxmlView<OfferManagementViewModel>, Initializable {

	@InjectViewModel
	private OfferManagementViewModel model;
	
	// Grid Suchfelder
	@FXML
	private GridPane searchGridPane;
	@FXML
	private TextField offerField;
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
	@FXML 
	private Button exportButton;
	
	// TableView: Spalten Tabellen für Angebotsmanagment
	@FXML
	public TableView<Offer> offerTable;
	@FXML
	private TableColumn<Offer, Long> offerNumberColumn;
	@FXML
	private TableColumn<Offer, Date> createdDateColumn;
	@FXML
	private TableColumn<Offer, Date> completedDateColumn;
	
//--> Typ STring Foreign Keys ???? 
	@FXML
	private TableColumn<Offer, Employee> employeeColumn;
	@FXML
	private TableColumn<Offer, Customer> customerColumn;
	@FXML
	private TableColumn<Offer, String> statusColumn;
	@FXML
	private TableColumn<OfferPosition, DecimalFormat> priceColumn;
//-- > wie/wo berechnen Menge * Preis ? 
	
	
	@FXML
	private MenuItem closeMenuItem;
	
	
	// Initialize --> Tabellen Werte wo bereits bei Aufruf/Beginn UC Werte enthalten sein sollen
	@Override
	public void initialize(URL location, ResourceBundle resources) { 
		
		
		Bindings.bindContent(offerTable.itemsProperty().get(), model.offerListProperty());
		//offersPositionsList? binding??? 
		
//---> Daniel Filtered Liste an OfferTable binden
		//Bindings.bindContent(model.getFilteredData(), offerTable.itemsProperty().get());
		
		
		//Alle involvierten Spalten binden -- Syntax: Bindings (List<> , ObservableList<>)
		offerNumberColumn.setCellValueFactory(new PropertyValueFactory<>("offerId"));
		createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("createdDt"));
		completedDateColumn.setCellValueFactory(new PropertyValueFactory<>("completedDt"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
	
		// für ComboBox Employee (Binding zwischen ComboBox Feld und aus Modell
		// mit der Employee Property
		Bindings.bindContent(employeeField.itemsProperty().get(), model.employeeListProperty());
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
		Bindings.bindContent(customerField.itemsProperty().get(), model.customerListProperty());
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
		 
	// Getter für SuchfunktionenFelder UI
	public TextField getOfferField() {
			return offerField;
		}
	public DatePicker getDateStartField() {
			return dateStartField;
	}
	public DatePicker getDateEndField() {
			return dateEndField;
	}
	public ComboBox<Customer> getCustomerField() {
			return customerField;
	}
	public ComboBox<Employee> getEmployeeField() {
			return employeeField;
	}

		
		// wenn Suchen klickt, soll 1. ANR: logik aufrufen, dann Zeitraum etc...(immer nur mit filteredList danach weiter) 
		//FilterTable aufrufen
	public void onSearchButtonClick() {
		
		// wenn kein Suchfeld befüllt wurde (=alle leer)
			if (offerField.getText().trim().equals("") && dateStartField.getValue() == null && 
					dateEndField.getValue() == null && customerField.getValue() == null &&
						employeeField.getValue() == null) {
		
				 Alert noInputAlert= new Alert(AlertType.WARNING);
			        noInputAlert.setHeaderText("Keine Eingabe vorhanden");
			        noInputAlert.setContentText("Bitte mindestens ein Suchkriterium eingeben");
			        noInputAlert.showAndWait();
			   
			}
	
			//wenn mindestens 1 Feld Input hat 
			else {
				searchButton.setOnAction(model.FindByEmployee());
	/*			searchButton.setOnAction(model.FindByDate());
			
				searchButton.setOnAction(model.FindByOfferNumber());
				searchButton.setOnAction(model.FindByCustomer());
				
				
				SortedList<Offer> sortedData = new SortedList<>(model.getFilteredData());
			     
			        // SortedList binden, comparator zum TableView comparator.
			        sortedData.comparatorProperty().bind(offerTable.comparatorProperty());
			       
			// Sortierte und gefilterte Daten in Tabelle einfügen
			        offerTable.setItems(sortedData); 
	*/
			}
			
		// Filterliste löschen
			model.getFilteredData().clear();

		}
	
	// Beende 
	public void closeOfferManagement() {
		closeMenuItem.setOnAction(model.quitOfferManagement());
	}


	}
