package at.c02.bpj.client.offer.management;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.google.common.base.Strings;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// OfferTable binden Syntax: Bindings (List<> , ObservableList<>)
		Bindings.bindContent(offerTable.itemsProperty().get(), model.offerListProperty());

		// Alle involvierten Spalten der OfferTable binden
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

		// laden von ForeignKey Felder der OfferTable (Employee und Customer)
		customerColumn.setCellValueFactory(new Callback<CellDataFeatures<Offer, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Offer, String> param) {
				return new SimpleStringProperty(param.getValue().getCustomer().getCompanyName());
			}
		});

		employeeColumn.setCellValueFactory(new Callback<CellDataFeatures<Offer, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Offer, String> param) {
				return new SimpleStringProperty(param.getValue().getEmployee().getLastname());
			}
		});

		// für ComboBox Employee (Binding zwischen ComboBox Feld und
		// EmployeeProperty
		Bindings.bindContent(employeeField.itemsProperty().get(), model.emplyoeeListProperty());
		employeeField.valueProperty().bindBidirectional(model.searchEmployeeProperty());

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

		// Binding ComboBox Customer
		Bindings.bindContent(customerField.itemsProperty().get(), model.customerListProperty());
		customerField.valueProperty().bindBidirectional(model.searchCustomerProperty());

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

		// Datepicker wert auf NULL setzen, wenn Textfeld von Datepicker leer ist
		addResetDateFieldValueListener(dateStartField);
		addResetDateFieldValueListener(dateEndField);

		offerField.textProperty().bindBidirectional(model.searchOfferIdProperty());
		dateStartField.valueProperty().bindBidirectional(model.searchStartDateProperty());
		dateEndField.valueProperty().bindBidirectional(model.searchEndDateProperty());
	}

	private void addResetDateFieldValueListener(DatePicker datePicker) {
		datePicker.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
			if (Strings.isNullOrEmpty(newValue)) {
				datePicker.setValue(null);
			}
		});
	}
	@FXML
	public void onSearchButtonClick() {
		model.onSearchButtonClick();
	}

	// Beende
	public void closeOfferManagement() {
		closeMenuItem.setOnAction(model.quitOfferManagement());
	}

}