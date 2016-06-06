package at.c02.bpj.client.offer.management;

import java.awt.Checkbox;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Category;
import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferPosition;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	
	// Grid Suchparameter Parameter + Suchfelder
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
	
	
	//Suchbutton
	@FXML 
	private Button searchButton; 
	
	//Spalten Tabelle für Angebotsicht (Inkl. MA, Kunden ... s.h. UC006) 
	
//---> Daniel !!!!!!brauchte Table View mit mehreren Datentypen von Offer, Employee, Customer ... ??? (bindings)
	@FXML
	private TableView<Offer> offerTable;
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
	@FXML
	private TableColumn<CheckBox, Boolean> choiceColumn; 

	
	
	//Zu Beginn sichtbar: alle Daten von Angeboten in DAtenbank (inkl. MA, Kunde....UC006) 
	// initialize --> Tabellen Werte wo bereits bei Aufruf/Beginn UC Werte enthalten sein sollen
	@Override
	public void initialize(URL location, ResourceBundle resources) { 
		
//----> Daniel -- hier Tabelle binden? 
		Bindings.bindContent(offerTable.itemsProperty().get(), model.offerPropertyList());
		
		//Alle involvierten Spalten binden -- Syntax: Bindings (List<> , ObservableList<>)
		offerNumberColumn.setCellValueFactory(new PropertyValueFactory<>("offerId"));
		createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
		completedDateColumn.setCellValueFactory(new PropertyValueFactory<>("completedDate"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		choiceColumn.setCellValueFactory(new PropertyValueFactory<>("choice"));

		//für ComboBox Employee (Binding zwischen ComboBox Feld und aus Modell mit der Employee Property 
		Bindings.bindContent(employeeField.itemsProperty().get(), model.employeesPropertyList());
		employeeField.valueProperty().bindBidirectional(model.employeeProperty());
// --> verstehe ich nicht !!! 	
		
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
			
		
		// Einfügen der CheckBox (choiceColumn) in jeder Zeile 
		offerTable.setRowFactory(table -> {
			final TableRow<Offer> row = new TableRow<>();
//------> Daniel wie kann in jeder Zeiel CheckBox einfügen??? 
			return row;
		});
		
		};
		
		
}
