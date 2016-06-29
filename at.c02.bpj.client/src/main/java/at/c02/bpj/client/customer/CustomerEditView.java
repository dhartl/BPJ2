package at.c02.bpj.client.customer;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;

import at.c02.bpj.client.api.model.Category;
import at.c02.bpj.client.api.model.Gender;
import at.c02.bpj.client.converter.StringToNumberConverter;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;


/**
 * Controller von CustomerEditView.fxml
 */
public class CustomerEditView implements FxmlView<CustomerEditViewModel>, Initializable {

	@InjectViewModel
	private CustomerEditViewModel model;

	@FXML
	private TextField customerId;
	@FXML
	private TextField customerFirstName;
	@FXML
	private TextField customerLastName;
	@FXML
	private TextField companyName;
	@FXML
	private TextField street;
	@FXML
	private TextField houseNr;
	@FXML
	private TextField postCode;
	@FXML
	private TextField city;
	@FXML
	private TextField phoneNr;
	@FXML
	private TextField email;
	@FXML
	private ComboBox<Gender> gender;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DecimalFormat idFormat = new DecimalFormat();
		idFormat.setParseIntegerOnly(true);

		// tfId ist ein Ganzzahlfeld
		customerId.setTextFormatter(new TextFormatter<>(c -> {
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = idFormat.parse(c.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else {
				return c;
			}
		}));
		
		// tfPrice ist ein Feld mit 2 Nachkommazahlen
		;

		// Binden der Text-Properties der Textfelder mit den Model-Properties
		// Konversaton von Text in Zahl passiert automatisch!
		Bindings.bindBidirectional(customerId.textProperty(), model.idProperty(),
				new StringToNumberConverter<Long>(idFormat));
		customerFirstName.textProperty().bindBidirectional(model.firstNameProperty());
		customerLastName.textProperty().bindBidirectional(model.lastNameProperty());
		companyName.textProperty().bindBidirectional(model.companyNameProperty());
		street.textProperty().bindBidirectional(model.streetProperty());
		houseNr.textProperty().bindBidirectional(model.houseNrProperty());
		postCode.textProperty().bindBidirectional(model.postCodeProperty());
		city.textProperty().bindBidirectional(model.cityProperty());
		phoneNr.textProperty().bindBidirectional(model.phoneNrProperty());
		Bindings.bindContent(gender.itemsProperty().get(), model.genderListProperty());
		gender.valueProperty().bindBidirectional(model.genderProperty());
		
	}

}
