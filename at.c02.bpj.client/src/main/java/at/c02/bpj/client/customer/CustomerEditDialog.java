package at.c02.bpj.client.customer;



import at.c02.bpj.client.api.model.Customer;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert.AlertType;

/**
 * Dialog zum Erstellen oder Bearbeiten eines Kunden
 */
public class CustomerEditDialog extends Dialog<Customer> {

	private ViewTuple<CustomerEditView, CustomerEditViewModel> viewTuple;
	private Boolean correctValues;
	private Customer customer;
	
	public CustomerEditDialog() {
		// Laden von CustomerEditView
		viewTuple = FluentViewLoader
				.fxmlView(CustomerEditView.class).load();
		getDialogPane().setContent(viewTuple.getView());
		// Speichern-Button
		final ButtonType saveType = new ButtonType("Speichern", ButtonData.OK_DONE);
		// Dialog hat Buttons "Speichern" und "Abbrechen"
		getDialogPane().getButtonTypes().addAll(saveType, ButtonType.CANCEL);

		
		
		Button saveButton = (Button) getDialogPane().lookupButton(saveType);
		saveButton.setOnAction((event) -> {
			customer = viewTuple.getViewModel().getCustomer();
			 correctValues = (customer.getCompanyName().length()>0);
			if (correctValues) {
				this.correctValues = true;
				close();
			}
		});	
		
		this.setOnCloseRequest(event -> {

			correctValues = (customer.getCompanyName().length()>0);
			if (correctValues==false) {
				event.consume();
			}
			
		});
		
		setResultConverter(buttonType -> {
			if (saveType.equals(buttonType)) {
				// Nur wenn Speichern gedrückt wurde, wird der Artikel
				// zurückgeliefert
				customer = viewTuple.getViewModel().getCustomer();
				correctValues = (customer.getCompanyName().length()>0);

				if (correctValues== false)
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
