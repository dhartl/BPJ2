package at.c02.bpj.client.offer.management;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.management.modelmbean.ModelMBean;

import org.apache.log4j.lf5.util.DateFormatManager;
import org.junit.experimental.theories.Theories;

import com.google.common.base.Strings;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.fonts.FontsResourceAnchor;

import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferStatus;
import at.c02.bpj.client.offer.OfferCreateViewModel;
import at.c02.bpj.client.offer.OfferScope;
import at.c02.bpj.client.service.ArticleService;
import at.c02.bpj.client.service.OfferService;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;

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
	
	private ArticleService articleService;
	private OfferService offerService;
	public int positionNumber;

	@InjectScope
	private OfferScope offerScope;

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


	public void closeOfferManagement() {
		closeMenuItem.setOnAction(model.quitOfferManagement());
	}


	public void onExportButtonClick() throws DocumentException, IOException {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save File To");
		
		// richtigen extension filter wählen für pdf
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PDF Files", "*.pdf"));

		// hier noch den automatischen filevorschlag implementieren	
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd_HH_mm_ss" );
		df.setTimeZone( TimeZone.getDefault() );

		fileChooser.setInitialFileName("Angebot_" + model.getSelectedOffer().getOfferId().toString() + "_" + model.getSelectedOffer().getCustomer().getCompanyName().toString()+ "_" + df.format(dt));
		File selectedFile = fileChooser.showSaveDialog(exportButton.getScene().getWindow());
		
		
		if (selectedFile != null) {
			
			
//			Offer offer = offerScope.offerProperty().get();
//			Offer offer = offerScope.offerProperty().;
			Offer offer = model.getSelectedOffer();
			offer.setCompletedDt(new Date());			
			offer.setStatus(OfferStatus.COMPLETED);
			offerScope.setOffer(offer);
//			Offer completedOffer = offerService.saveOffer(offer);
//			offerScope.setOffer(completedOffer);

//			completeOffer();
			model.onExportButtonClick(selectedFile);	
			createPdf(selectedFile.toString());				

			Alert noInputAlert = new Alert(AlertType.INFORMATION);
		    noInputAlert.setHeaderText("Information");
		    noInputAlert.setContentText("Ihr Angebot wurde gespeichert!");
		    noInputAlert.showAndWait();
			}
		
			else
			{
				Alert noInputAlert = new Alert(AlertType.WARNING);
			    noInputAlert.setHeaderText("Warnung");
			    noInputAlert.setContentText("Ihr Angebot wurde nicht gespeichert!");
			    noInputAlert.showAndWait();
			}
	}
	
	public void createPdf(String filename)
			throws DocumentException, IOException {

		        Document document = new Document();
		        PdfWriter.getInstance(document, new FileOutputStream(filename));
		        document.open();		        
		        Font bold = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);

		        Image img = Image.getInstance("AVl_Logo.jpg");
		        img.setAbsolutePosition(428f, 765f);
		        img.scaleToFit(150, 150);
		        
		        Date dt = new Date();
				SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd");
				df.setTimeZone( TimeZone.getDefault() );
				SimpleDateFormat dg = new SimpleDateFormat( "HH:mm:ss");
				dg.setTimeZone( TimeZone.getDefault() );
				
		        //model.getSelectedOffer().setCompletedDt(dt);

		        document.add(img);
		        document.add(new Paragraph("\n"));
		        document.add(new Paragraph("Sehr geehrter Herr " + model.getSelectedOffer().getCustomer().getContactLastName() + ","));
		        document.add(new Paragraph("\n"));
		        document.add(new Paragraph("wie vereinbart finden Sie untenstehend unser Angebot"));
		        document.add(new Paragraph("zu Ihrer Bestellung mit der Nummer " + 
		        		model.getSelectedOffer().getOfferId().toString() + ":"));
		        document.add(new Paragraph("\n\n"));
		        document.add(new Paragraph("Auftraggeber:" , bold));
		        document.add(new Paragraph(model.getSelectedOffer().getCustomer().getContactFirstName() + " " + model.getSelectedOffer().getCustomer().getContactLastName()));
		        document.add(new Paragraph(model.getSelectedOffer().getCustomer().getCompanyName()));
		        document.add(new Paragraph(model.getSelectedOffer().getCustomer().getStreet() + " " + model.getSelectedOffer().getCustomer().getHouseNr()));
		        document.add(new Paragraph(model.getSelectedOffer().getCustomer().getPostCode() + " " + model.getSelectedOffer().getCustomer().getCity()));
		        document.add(new Paragraph("\n\n"));
		        
		        document.add(new Paragraph("Auftragnehmer:" ,bold));
		        document.add(new Paragraph(model.getSelectedOffer().getEmployee().getFirstname() + " " + model.getSelectedOffer().getEmployee().getLastname()));
		        document.add(new Paragraph(model.getSelectedOffer().getEmployee().getEmail()));
		        document.add(new Paragraph("\n\n"));
		        
		        document.add(new Paragraph("Das Angebot setzt sich aus folgender(n) Position(en) zsammen:"));
		        document.add(new Paragraph("\n"));

		        ArrayList<Offer>Anbot = new ArrayList<Offer>();

		        Anbot.add(model.getSelectedOffer());
		        float gesamtpreis = 0;
		       
		        for (Offer offer : Anbot) 
		        {
//		        	PdfPTable table = new PdfPTable(5);
//			        PdfPCell cell = new PdfPCell();
//			        cell.setRowspan(offer.getOfferPositions().size());
//			        cell.setColspan(3);
//			        document.add(table);
//			        document.add(cell);
//			        document.(table.addCell("row 1; cell 1"));
//			        table.addCell("row 1; cell 2");
//			        table.addCell("row 2; cell 1");
//			        table.addCell("row 2; cell 2");
			        int j = 0;
		        	for(int i=0; i<offer.getOfferPositions().size(); i++)
		        	{
		        		j=i+1;
//		        		document.add(new PdfPCell(table) table.addCell("Artikel " + i + ": " + offer.getOfferPositions().get(i).getArticle().getName()
//			        			+ ", Preis: " + offer.getOfferPositions().get(i).getArticle().getPrice()));
		        		document.add(new Paragraph("Position " + j + ", Artikel: " + offer.getOfferPositions().get(i).getArticle().getName()
		        				+ ", Preis: " + offer.getOfferPositions().get(i).getPrice() + ", Anzahl: " + offer.getOfferPositions().get(i).getAmount()));  	
//		        		document.add(new Paragraph("Artikel " + j + ": " + offer.getOfferPositions().get(i).getArticle().getName()
//			        			+ ", Preis: " + offer.getOfferPositions().get(i).getArticle().getPrice() + ", Anzahl: " + offer.getOfferPositions().get(i).getAmount()));
			        	gesamtpreis+=offer.getOfferPositions().get(i).getPrice() * offer.getOfferPositions().get(i).getAmount();
//		        	document.add(new Paragraph("Artikel " + i + ": " + offer.getOfferPositions().get(i).getArticle().getName()
//		        			+ ", Preis: " + offer.getOfferPositions().get(i).getArticle().getPrice()));
//		        	gesamtpreis+=offer.getOfferPositions().get(i).getArticle().getPrice();
		        	System.out.println("Gesamtpreis: " + gesamtpreis);
		        	System.out.println(offer.getOfferPositions().get(i).getArticle().getName() + offer.getOfferPositions().get(i).getArticle().getPrice()+offer.getOfferPositions().get(i).getOfferPositionId().toString());
		        	System.out.println(offer.getOfferPositions().get(i).getArticle().getName() + offer.getOfferPositions().get(i).getArticle().getPrice()+offer.getOfferPositions().get(i).getOfferPositionId().toString());
		        	System.out.println(offer.getOfferPositions().size());
		        	}
		    	}
		        document.add(new Paragraph("\n"));
		        document.add(new Paragraph("Wir erlauben uns, Ihnen für diese Bestellung " + gesamtpreis + " Euro in Rechnung zu stellen.", bold));
		        document.add(new Paragraph("\n\n"));
		        document.add(new Paragraph("Vielen Dank für Ihr Vertrauen!"));//		       
		        document.add(new Paragraph("\n\n"));
//   		    document.add(new Paragraph("Die Bestellung wurde abgeschlossen am " + df.format(dt) + " um " + dg.format(dt) + " Uhr."));
		        document.add(new Paragraph("Die Bestellung wurde abgeschlossen am " + model.getSelectedOffer().getCompletedDt() + " um " + dg.format(dt) + " Uhr."));
		        document.add(new Paragraph("\n"));
//		        Paragraph p = new Paragraph("Hochachtungsvoll,");
//		        p.setAlignment(Element.ALIGN_BOTTOM);		        
//		        document.add(p);
		        document.add(new Paragraph("Hochachtungsvoll,"));
		        document.add(new Paragraph(model.getSelectedOffer().getEmployee().getFirstname() + " " + model.getSelectedOffer().getEmployee().getLastname()));
	        
		        document.close();		        
		    }
	
	public void completeOffer() {
		Offer offer =  offerScope.offerProperty().get();
		offer.setCompletedDt(new Date());
		offer.setStatus(OfferStatus.COMPLETED);
		Offer completedOffer = offerService.saveOffer(offer);
		offerScope.setOffer(completedOffer);
	}
}
