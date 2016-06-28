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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private Label lblCustomerCity;

    @FXML
    private Label lblCustomerStreet;

    @FXML
    private Label lblCustomerHouseNr;

    @FXML
    private Label lblCustomerPostCode;

    @FXML
    private Label lblCustomerContactPartnerFN;

    @FXML
    private Label lblCustomerContactPartnerLN;

    @FXML
    private Button btnOfferCreate;

    @FXML
    private Button btnShowChoosenCustomer;

    @FXML
    private Button btnChoosenCustomer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	Bindings.bindContent(cbxCustomer.itemsProperty().get(), model.customerListProperty());
	cbxCustomer.valueProperty().bindBidirectional(model.selectedCustomerProperty());
	cbxCustomer.getSelectionModel().selectFirst();

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
	cbxEmployees.valueProperty().bindBidirectional(model.selectedEmployeeProperty());
	cbxEmployees.getSelectionModel().selectFirst();

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

	Bindings.selectString(model.selectedCustomerProperty(),
		model.selectedCustomerProperty().get().streetProperty().toString());

	Bindings.bindBidirectional(lblCustomerStreet.textProperty(),
		model.selectedCustomerProperty().get().streetProperty());

	Bindings.bindBidirectional(lblCustomerHouseNr.textProperty(),
		model.selectedCustomerProperty().get().houseNrProperty());

	Bindings.bindBidirectional(lblCustomerCity.textProperty(),
		model.selectedCustomerProperty().get().cityProperty());

	Bindings.bindBidirectional(lblCustomerPostCode.textProperty(),
		model.selectedCustomerProperty().get().postCodeProperty());

	Bindings.bindBidirectional(lblCustomerContactPartnerFN.textProperty(),
		model.selectedCustomerProperty().get().contactFirstName());

	Bindings.bindBidirectional(lblCustomerContactPartnerLN.textProperty(),
		model.selectedCustomerProperty().get().contactLasttName());

	cbxCustomer.valueProperty().addListener(new ChangeListener<Customer>() {

	    @Override
	    public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer newValue) {

		Bindings.selectString(model.selectedCustomerProperty(),
			model.selectedCustomerProperty().get().streetProperty().toString());

		Bindings.bindBidirectional(lblCustomerStreet.textProperty(),
			model.selectedCustomerProperty().get().streetProperty());

		Bindings.bindBidirectional(lblCustomerHouseNr.textProperty(),
			model.selectedCustomerProperty().get().houseNrProperty());

		Bindings.bindBidirectional(lblCustomerCity.textProperty(),
			model.selectedCustomerProperty().get().cityProperty());

		Bindings.bindBidirectional(lblCustomerPostCode.textProperty(),
			model.selectedCustomerProperty().get().postCodeProperty());

		Bindings.bindBidirectional(lblCustomerContactPartnerFN.textProperty(),
			model.selectedCustomerProperty().get().contactFirstName());

		Bindings.bindBidirectional(lblCustomerContactPartnerLN.textProperty(),
			model.selectedCustomerProperty().get().contactLasttName());

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

	    model.newOffer();

	    Parent root;
	    ViewTuple<OfferCreateView, OfferCreateViewModel> viewTuple = FluentViewLoader
		    .fxmlView(OfferCreateView.class).load();
	    // Übergabe des erstellten Angebotes an das neue Fenster
	    viewTuple.getViewModel().offerProperty().set(model.offerProperty().get());
	    root = viewTuple.getView();
	    Stage stage = new Stage();
	    stage.setTitle("Positionen zuweisen");
	    stage.setScene(new Scene(root, 800, 600));
	    stage.show();

	}
    }

}
