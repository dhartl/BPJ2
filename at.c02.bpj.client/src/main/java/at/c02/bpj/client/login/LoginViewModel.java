package at.c02.bpj.client.login;

import com.google.common.base.Strings;

import at.c02.bpj.client.service.LoginService;
import at.c02.bpj.client.service.ServiceException;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginViewModel implements ViewModel {
	private StringProperty username = new SimpleStringProperty();
	private StringProperty password = new SimpleStringProperty();

	private LoginService loginService;

	public LoginViewModel(LoginService loginService) {
		super();
		this.loginService = loginService;
	}

	public boolean login() {
		if (Strings.isNullOrEmpty(getUsername())) {
			showErrorDialog("Benutzername erforderlich", "Bitte geben Sie einen Benutzernamen ein!");
			return false;
		}
		try {
			loginService.login(getUsername(), getPassword());
			return true;
		} catch (ServiceException ex) {
			showErrorDialog("Login fehlgeschlagen", ex.getMessage());
			return false;
		}
	}

	private void showErrorDialog(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(title);
		alert.setContentText(message);
		alert.showAndWait();
		password.set(null);
	}

	public final StringProperty usernameProperty() {
		return this.username;
	}

	public final java.lang.String getUsername() {
		return this.usernameProperty().get();
	}

	public final void setUsername(final java.lang.String username) {
		this.usernameProperty().set(username);
	}

	public final StringProperty passwordProperty() {
		return this.password;
	}

	public final java.lang.String getPassword() {
		return this.passwordProperty().get();
	}

	public final void setPassword(final java.lang.String password) {
		this.passwordProperty().set(password);
	}

}
