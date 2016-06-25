package at.c02.bpj.client.offer;

import java.net.URL;
import java.util.ResourceBundle;

import at.c02.bpj.client.api.model.Customer;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

public class OfferChooseCustomerView implements FxmlView<OfferChooseCustomerModel>, Initializable {

    @InjectViewModel
    private OfferChooseCustomerModel model;

    @FXML
    private ComboBox<Customer> cbxCustomer;

    @FXML
    private Label lblOfferNumber;

    @FXML
    private Label lblOfferDate;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblCustomerCityPLZ;

    @FXML
    private Label lblCustomerCountry;

    @FXML
    private Label lblCustomerStreet;

    @FXML
    private Button btnOfferCreate;

    @FXML
    private Button btnShowChoosenCustomer;

    @FXML
    private Button btnChoosenCustomer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    }

    public void onbtnShowChoosenCustomerClick() {

	String s = model.customerProperty().getValue().getStreet();

	if (s == null) {
	    Alert noInputAlert = new Alert(AlertType.WARNING);
	    noInputAlert.setHeaderText("Keine Eingabe vorhanden");
	    noInputAlert.setContentText("Bitte mindestens ein Suchkriterium eingeben");
	    noInputAlert.showAndWait();

	}
    }

    public ComboBox<Customer> getCustomerField() {
	return cbxCustomer;
    }

}
