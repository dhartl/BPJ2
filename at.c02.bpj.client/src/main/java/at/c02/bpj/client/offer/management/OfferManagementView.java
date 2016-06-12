package at.c02.bpj.client.offer.management;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
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
	@FXML
	private TableColumn<Offer, String> employeeColumn;
	@FXML
	private TableColumn<Offer, String> customerColumn;
	@FXML
	private TableColumn<Offer, String> statusColumn;
	@FXML
	private TableColumn<Offer, Double> priceColumn; 
	
	@FXML
	private MenuItem closeMenuItem;

	@FXML
	private FilteredList<Offer> filteredData;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) { 
		
		//OfferTable binden Syntax: Bindings (List<> , ObservableList<>)
		Bindings.bindContent(offerTable.itemsProperty().get(), model.offerListProperty());	
		
		//Alle involvierten Spalten der OfferTable binden
		offerNumberColumn.setCellValueFactory(new PropertyValueFactory<>("offerId"));
		createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("createdDt"));
		completedDateColumn.setCellValueFactory(new PropertyValueFactory<>("completedDt"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		priceColumn.setCellValueFactory(param -> {
			ObjectProperty<Double> priceProperty = new SimpleObjectProperty<Double>();
			priceProperty.bind(Bindings.createObjectBinding(
					() -> param.getValue().getOfferPositions().stream()
							.mapToDouble(pos -> pos.getPrice() * pos.getAmount()).sum(),
					param.getValue().offerPositionsProperty()));
			return priceProperty;
		});
		// Bindings.bindContent(priceColumn.getText(),
		// model.offerPositionListProperty());
		
		//laden von ForeignKey Felder der OfferTable (Employee und Customer) 
		customerColumn.setCellValueFactory(new Callback<CellDataFeatures<Offer, String>,ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Offer, String> param) {
                return new SimpleStringProperty(param.getValue().getCustomer().getCompanyName());
            }
        });
		
		employeeColumn.setCellValueFactory(new Callback<CellDataFeatures<Offer, String>,ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<Offer, String> param) {
                return new SimpleStringProperty(param.getValue().getEmployee().getLastname());
            }
        });
		

		// für ComboBox Employee (Binding zwischen ComboBox Feld und EmployeeProperty
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
	
		
		// OfferTableObservable liste in FilteredList wandeln
        filteredData = new FilteredList<>(model.offerListProperty());
    
        // Alle Daten anzeigen zu Beginn; Predicate anzeigen = true 
        Predicate<Offer> predicate = new Predicate<Offer>() {
			@Override
			public boolean test(Offer t) {
			    return true;
			}
        };
        //filteredData.setPredicate(predicate);
        filteredData.setPredicate(predicate);
	
	    // OfferTable mit gefilterterList aus Suche befüllen
        //offerTable.setItems(filteredData);
        offerTable.setItems(filteredData);
	}


	
	//Getter/Setter SearchFields 
	public final TextField getOfferField() {
		return offerField;
	}
	public final void setOfferField(TextField offerField) {
		this.offerField = offerField;
	}
	public final DatePicker getDateStartField() {
		return dateStartField;
	}
	public final void setDateStartField(DatePicker dateStartField) {
		this.dateStartField = dateStartField;
	}
	public final DatePicker getDateEndField() {
		return dateEndField;
	}
	public final void setDateEndField(DatePicker dateEndField) {
		this.dateEndField = dateEndField;
	}
	public final ComboBox<Customer> getCustomerField() {
		return customerField;
	}
	public final void setCustomerField(ComboBox<Customer> customerField) {
		this.customerField = customerField;
	}
	public final ComboBox<Employee> getEmployeeField() {
		return employeeField;
	}
	public final void setEmployeeField(ComboBox<Employee> employeeField) {
		this.employeeField = employeeField;
	}


	@FXML
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
			
//--->Daniel -- wie /wo Predicate definieren, dass nicht alle Fälle aufrufen muss?
		
			/// 4x if wenn suchfeld jeweils alleine nicht leer
		if (offerField.getText() != null) {
				//FindByOfferNumber();
				FindByOfferNumber();
		}
		if (dateStartField.getValue() != null && dateEndField != null) {
				FindByDate();
		}
		if (customerField.getValue() != null) {
				FindByCustomer();
		}
		if (employeeField.getValue() != null) {
				FindByEmployee();
		}					
	}
			
	
public void FindByOfferNumber() {
		
		String newValue = offerField.getText();
	
		Predicate<Offer> predicate = new Predicate<Offer>() {

			@Override
			public boolean test(Offer offerNumber) {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (offerNumber.getOfferId().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matched OfferNumber
				} 
				return false; // Kein Match
			}
			
		};
		filteredData.setPredicate(predicate);	
	}		
	

	public void FindByDate() {
		
		// werte von ComboBox
		LocalDate startDate = dateStartField.getValue();
		LocalDate endDate = dateEndField.getValue();
		
		Date Sdate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date Edate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		Predicate<Offer> predicate = new Predicate<Offer>() {

			@Override
			public boolean test(Offer offerDate) {
				//wenn leer, alle anzeigen
				if (Sdate == null || Edate == null) {
					return true;
				}
				
				int compareDateFrom = offerDate.getCreatedDt().compareTo(Sdate);
				if (compareDateFrom >=0) {
					 // Filter matches OfferNumber
					int compareDateTo = offerDate.getCompletedDt().compareTo(Edate);
					
					if (compareDateTo <=0) {
						return true;
					}
				} 
				return false; // Kein Match
			}
			
		};
		filteredData.setPredicate(predicate);
	}
	
	
	
	private void FindByCustomer() {

		String cust = (String) customerField.getValue().getCompanyName();
		
		Predicate<Offer> predicate = new Predicate<Offer>() {
		
			@Override
			public boolean test(Offer customer) {
				if (cust == null || cust.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = cust.toLowerCase();
				
				if (customer.getCustomer().getCompanyName().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					
					return true; // Filter matches OfferNumber
				} 
				return false; // Kein Match
			}
			
		};
		filteredData.setPredicate(predicate);	
	}
	
	
	public void FindByEmployee() {
		
		String empl = (String) employeeField.getValue().getLastname();
		
		Predicate<Offer> predicate = new Predicate<Offer>() {
		
			@Override
			public boolean test(Offer employee) {
				if (empl == null || empl.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = empl.toLowerCase();
				
				if (employee.getEmployee().getLastname().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					
					return true; // Filter matches OfferNumber
				} 
				return false; // Kein Match
			}
			
		};
		filteredData.setPredicate(predicate);	
	}
	
	
	
	// Beende 
	public void closeOfferManagement() {
		closeMenuItem.setOnAction(model.quitOfferManagement());
	}


}
