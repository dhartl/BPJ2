package at.c02.bpj.client.customer;


import at.c02.bpj.client.api.model.Customer;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert.AlertType;

/**
 * Dialog zum Erstellen oder Bearbeiten eines Kunden
 */
public class CustomerEditDialog extends Dialog<Customer> {

	private ViewTuple<CustomerEditView, CustomerEditViewModel> viewTuple;

	public CustomerEditDialog() {
		// Laden von CustomerEditView
		viewTuple = FluentViewLoader
				.fxmlView(CustomerEditView.class).load();
		getDialogPane().setContent(viewTuple.getView());
		// Speichern-Button
		final ButtonType saveType = new ButtonType("Speichern", ButtonData.OK_DONE);
		// Dialog hat Buttons "Speichern" und "Abbrechen"
		getDialogPane().getButtonTypes().addAll(saveType, ButtonType.CANCEL);

		setResultConverter(buttonType -> {
			if (saveType.equals(buttonType)) {
				// Nur wenn Speichern gedrückt wurde, wird der Artikel
				// zurückgeliefert
				
				
				Customer customer= viewTuple.getViewModel().getCustomer();
				
				if (customer.getCompanyName().isEmpty())
				{
					Alert noInputAlert = new Alert(AlertType.WARNING);
				    noInputAlert.setHeaderText("Eingabe fehlerhaft");
				    noInputAlert.setContentText("Bitte Pflichtfelder ausfüllen!");
				    noInputAlert.showAndWait();
				}
				else
				{
					return customer;
				}
			}
			// sonst NULL
			return null;
		});
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
