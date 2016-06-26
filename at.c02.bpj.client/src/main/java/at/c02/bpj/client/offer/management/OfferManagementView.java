package at.c02.bpj.client.offer.management;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.google.common.base.Strings;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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

		exportButton.disableProperty().bind(Bindings.isNull(offerTable.getSelectionModel().selectedItemProperty()));
		model.selectedOfferProperty().bind(offerTable.getSelectionModel().selectedItemProperty());
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

	@FXML
	public void onExportButtonClick() throws DocumentException, IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				// richtigen extension filter wählen für pdf
				new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
				new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"), new ExtensionFilter("All Files", "*.*"));
		// hier noch den automatischen filevorschlag implementieren
		fileChooser.setInitialFileName(model.getSelectedOffer().getOfferId().toString());
		//fileChooser.setSelectedFile(new File("lin_cfg.c"));

		//File selectedFile = fileChooser.showOpenDialog(exportButton.getScene().getWindow());
		File selectedFile = fileChooser.showSaveDialog(exportButton.getScene().getWindow());
		if (selectedFile != null) {
			model.onExportButtonClick(selectedFile);
			//fileChooser.setInitialFileName(model.getSelectedOffer().getOfferId().toString());
			//createPdf("D:\\Campus02\\Offers\\test1");
			//createPdf("D:\\Campus02\\Offers\\" + selectedFile.getName());
			//createPdf("D:\\Campus02\\Offers\\" + model.getSelectedOffer().getOfferId().toString());
			createPdf("1");
		}
		//createPdf("test");
		//createPdf(model.getSelectedOffer().getOfferId().toString());
	}
	public void createPdf(String filename)
			throws DocumentException, IOException {
		        // step 1
		        Document document = new Document();
		        // step 2
		        PdfWriter.getInstance(document, new FileOutputStream(filename));
		        // step 3
		        document.open();
		        // step 4
		        document.add(new Paragraph("Hello World!"));
		        // step 5
		        document.close();
		    }
	public void createPdf()
			throws DocumentException, IOException {
		        // step 1
		        Document document = new Document();
		        // step 2
		       // PdfWriter.getInstance(document, new FileOutputStream(filename));
		       ///PdfWriter.getInstance(document, new File();
		        // step 3
		        document.open();
		        // step 4
		        document.add(new Paragraph("Hello World!"));
		        // step 5
		        document.close();
		    }

}
