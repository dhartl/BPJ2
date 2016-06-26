package at.c02.bpj.client.offer;

import java.net.URL;
import java.util.ResourceBundle;

import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class OfferChooseCustomerView implements FxmlView<OfferChooseCustomerModel>, Initializable {

    @InjectViewModel
    private OfferChooseCustomerModel model;

    @FXML
    private ComboBox<Customer> cbxCustomer;

    @FXML
    private ComboBox<Employee> cbxEmployees;

    @FXML
    private Label lblOfferNumber;

    @FXML
    private Label lblOfferDate;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblCustomerCityPostCode;

    @FXML
    private Label lblCustomerStreetHouseNr;

    @FXML
    private Label lblCustomerContactPartner;

    @FXML
    private Button btnOfferCreate;

    @FXML
    private Button btnShowChoosenCustomer;

    @FXML
    private Button btnChoosenCustomer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	// model.newOffer();
	//
	// lblOfferNumber.setText(model.offerProperty().getValue().getOfferId().toString());
	//
	// lblOfferDate.setText(model.offerProperty().getValue().getCreatedDt().toString());

	Bindings.bindContent(cbxCustomer.itemsProperty().get(), model.customerListProperty());
	cbxCustomer.valueProperty().bindBidirectional(model.customerProperty());

	cbxCustomer.setConverter(new StringConverter<Customer>() {

	    @Override
	    public String toString(Customer customer) {
		return customer.getCompanyName();
	    }

	    @Override
	    public Customer fromString(String string) {
		return null;
	    }

	});

	Bindings.bindContent(cbxEmployees.itemsProperty().get(), model.employeeListProperty());
	cbxEmployees.valueProperty().bindBidirectional(model.employeeProperty());

	cbxEmployees.setConverter(new StringConverter<Employee>() {

	    @Override
	    public String toString(Employee employee) {
		return employee.getLastname() + " " + employee.getFirstname();
	    }

	    @Override
	    public Employee fromString(String string) {
		return null;
	    }

	});

    }

    // Speichert Angebot und öffnet Bearbeitungsfenster
    public void onbtnChoosenCustomer() {
	if (cbxCustomer.getValue() == null || cbxEmployees.getValue() == null) {
	    Alert noInputAlert = new Alert(AlertType.WARNING);
	    noInputAlert.setHeaderText("Eingabe fehlerhaft");
	    noInputAlert.setContentText("Bitte Mitarbeiter und Kunde angeben");
	    noInputAlert.showAndWait();
	}

	else {
	    // model.newOffer();

	    Parent root;
	    ViewTuple<OfferCreateView, OfferCreateViewModel> viewTuple = FluentViewLoader
		    .fxmlView(OfferCreateView.class).load();
	    root = viewTuple.getView();
	    Stage stage = new Stage();
	    stage.setTitle("Positionen zuweisen");
	    stage.setScene(new Scene(root, 450, 450));
	    stage.show();

	}
    }

    // Zeigt die Details des Kunden
    public void onbtnShowChoosenCustomerClick() {

	if (cbxCustomer.getValue() == null) {
	    Alert noInputAlert = new Alert(AlertType.WARNING);
	    noInputAlert.setHeaderText("Eingabe fehlerhaft");
	    noInputAlert.setContentText("Bitte Kunde angeben");
	    noInputAlert.showAndWait();
	} else {
	    lblCustomerStreetHouseNr.setText(model.customerProperty().getValue().getStreet() + " "
		    + model.customerProperty().getValue().getHouseNr());
	    lblCustomerCityPostCode.setText(model.customerProperty().getValue().getPostCode() + " "
		    + model.customerProperty().getValue().getCity());

	    lblCustomerContactPartner.setText(model.customerProperty().getValue().getContactFirstName() + " "
		    + model.customerProperty().getValue().getContactLastName());
	}
    }

}
