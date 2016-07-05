package at.c02.bpj.client.login;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginView implements FxmlView<LoginViewModel>, Initializable {
	@FXML
	private TextField tfUsername;
	@FXML
	private PasswordField tfPassword;

	@InjectViewModel
	private LoginViewModel model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model.usernameProperty().bindBidirectional(tfUsername.textProperty());
		model.passwordProperty().bindBidirectional(tfPassword.textProperty());
		Platform.runLater(() -> tfUsername.requestFocus());
	}

}
