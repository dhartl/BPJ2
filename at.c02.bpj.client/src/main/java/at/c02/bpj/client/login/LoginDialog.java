package at.c02.bpj.client.login;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class LoginDialog extends Dialog<Boolean> {

	private ViewTuple<LoginView, LoginViewModel> viewTuple;
	private Boolean loginSuccessful;

	public LoginDialog() {
		setTitle("BPJ Login");
		viewTuple = FluentViewLoader.fxmlView(LoginView.class).load();
		getDialogPane().setContent(viewTuple.getView());
		// Speichern-Button
		final ButtonType saveType = new ButtonType("Login", ButtonData.OK_DONE);
		// Dialog hat Buttons "Speichern" und "Abbrechen"
		getDialogPane().getButtonTypes().addAll(saveType);

		Button saveButton = (Button) getDialogPane().lookupButton(saveType);
		saveButton.setOnAction((event) -> {
			boolean loginSuccessful = viewTuple.getViewModel().login();
			if (loginSuccessful) {
				this.loginSuccessful = true;
				close();
			}
		});
		this.setOnCloseRequest(event -> {
			if (loginSuccessful == null) {
				// verhindern des Schließens des Dialoges, wenn Login nicht
				// erfolgreich war
				event.consume();
			}
		});

		setResultConverter(buttonType -> {
			return loginSuccessful;
		});
	}

}
