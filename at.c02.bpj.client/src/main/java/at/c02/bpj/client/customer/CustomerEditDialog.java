package at.c02.bpj.client.customer;


import com.google.common.base.Strings;

import at.c02.bpj.client.api.model.Customer;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Dialog zum Erstellen oder Bearbeiten eines Kunden
 */
public class CustomerEditDialog extends Dialog<Customer> {

	private ViewTuple<CustomerEditView, CustomerEditViewModel> viewTuple;
	private boolean correctValues = false;

	public CustomerEditDialog() {
		// Laden von CustomerEditView
		viewTuple = FluentViewLoader.fxmlView(CustomerEditView.class).load();
		getDialogPane().setContent(viewTuple.getView());
		// Speichern-Button
		final ButtonType saveType = new ButtonType("Speichern", ButtonData.OK_DONE);
		// Dialog hat Buttons "Speichern" und "Abbrechen"
		getDialogPane().getButtonTypes().addAll(saveType);

		Button saveButton = (Button) getDialogPane().lookupButton(saveType);
		saveButton.setOnAction((event) -> {
			correctValues = validateCustomer(viewTuple.getViewModel().getCustomer());
			if (correctValues) {
				close();
			} else {
				Alert noInputAlert = new Alert(AlertType.WARNING);
				noInputAlert.setHeaderText("Eingabe fehlerhaft");
				noInputAlert.setContentText("Bitte Pflichtfelder ausfüllen!");
				noInputAlert.showAndWait();

			}
		});

		this.setOnCloseRequest(event -> {
			if (!correctValues) {
				event.consume();
			}
		});

		setResultConverter(buttonType -> {
			if (saveType.equals(buttonType) && validateCustomer(viewTuple.getViewModel().getCustomer())) {
				// Nur wenn Speichern gedrückt wurde, wird der Artikel
				// zurückgeliefert
				return viewTuple.getViewModel().getCustomer();
			}
			// sonst NULL
			return null;
		});
	}

	private boolean validateCustomer(Customer customer) {
		return customer != null && !Strings.isNullOrEmpty(customer.getCompanyName());
	}

	/**
	 * Setzt den zu bearbeitenden Artikel
	 * 
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		viewTuple.getViewModel().editCustomer(customer);
	}

}
