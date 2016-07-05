package at.c02.bpj.client.offer;

import at.c02.bpj.client.Async;
import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.service.CustomerService;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class OfferChooseCustomerModel implements ViewModel {

	// Angebot - Properties
	private CustomerService customerService;

	@InjectScope
	private OfferScope offerScope;

	// Kundenauswahl

	private ObservableList<Customer> customersList = FXCollections.observableArrayList();

	private ObjectProperty<Customer> selectedCustomer = new SimpleObjectProperty<>();

	// Properties

	public StringProperty streetProperty() {
		return selectedCustomer.get().streetProperty();
	}

	public ObservableList<Customer> customerListProperty() {
		return customersList;
	}

	public ObjectProperty<Customer> selectedCustomerProperty() {
		return selectedCustomer;
	}

	public OfferChooseCustomerModel(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void initialize() {
		Async.executeUILoad(customerService::getCustomer, customers -> {
			customersList.setAll(customers);
		});

		offerScope.offerProperty().addListener((observable, oldValue, newValue) -> {
			unbindOffer(oldValue);
			bindOffer(newValue);
		});
		bindOffer(offerScope.offerProperty().get());

	}

	private void bindOffer(Offer newValue) {
		if (newValue != null) {
			selectedCustomer.bindBidirectional(newValue.customerProperty());
		}
	}

	private void unbindOffer(Offer oldValue) {
		if (oldValue != null) {
			Bindings.unbindBidirectional(oldValue, selectedCustomer);
		}
	}

	public boolean validateSelectedCustomer() {
		if (selectedCustomer.get() == null) {
			Alert noInputAlert = new Alert(AlertType.WARNING);
			noInputAlert.setHeaderText("Eingabe fehlerhaft");
			noInputAlert.setContentText("Bitte Mitarbeiter und Kunde angeben");
			noInputAlert.showAndWait();
			return false;
		}
		return true;
	}

}
