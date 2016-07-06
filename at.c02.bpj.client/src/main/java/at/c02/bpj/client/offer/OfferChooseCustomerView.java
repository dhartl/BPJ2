package at.c02.bpj.client.offer;

import java.net.URL;
import java.util.ResourceBundle;

import at.c02.bpj.client.api.model.Customer;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

public class OfferChooseCustomerView implements FxmlView<OfferChooseCustomerModel>, Initializable {

    @InjectViewModel
    private OfferChooseCustomerModel model;

    @FXML
    private ComboBox<Customer> cbxCustomer;

    @FXML
    private Label lblEmployee;

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

	// Bindings für Kundendaten
	lblCustomerStreet.textProperty()
		.bind(Bindings.selectString(cbxCustomer.getSelectionModel().selectedItemProperty(), "street"));

	lblCustomerHouseNr.textProperty()
		.bind(Bindings.selectString(cbxCustomer.getSelectionModel().selectedItemProperty(), "houseNr"));

	lblCustomerCity.textProperty()
		.bind(Bindings.selectString(cbxCustomer.getSelectionModel().selectedItemProperty(), "city"));

	lblCustomerPostCode.textProperty()
		.bind(Bindings.selectString(cbxCustomer.getSelectionModel().selectedItemProperty(), "postCode"));

	lblCustomerContactPartnerFN.textProperty().bind(
		Bindings.selectString(cbxCustomer.getSelectionModel().selectedItemProperty(), "contactFirstName"));

	lblCustomerContactPartnerLN.textProperty()
		.bind(Bindings.selectString(cbxCustomer.getSelectionModel().selectedItemProperty(), "contactLastName"));

    }

}
