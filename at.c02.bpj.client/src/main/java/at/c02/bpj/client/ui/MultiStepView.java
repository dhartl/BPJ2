package at.c02.bpj.client.ui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MultiStepView implements FxmlView<MultiStepViewModel>, Initializable {

	@InjectViewModel
	private MultiStepViewModel model;

	@FXML
	private BorderPane root;

	@FXML
	private Label lblBreadcrumbs;

	@FXML
	private HBox buttonBar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblBreadcrumbs.textProperty().bind(model.breadcrumbsProperty());
		root.centerProperty().bind(model.viewPropertyProperty());
		model.getStepActions().addListener((ListChangeListener<StepAction>) change -> {
			buttonBar.getChildren()
					.setAll(model.getStepActions().stream().map(this::createButton).collect(Collectors.toList()));
		});
	}

	private Button createButton(StepAction stepAction) {
		Button button = new Button();
		button.setText(stepAction.getTitle());
		button.setOnAction(event -> {
			if (stepAction.getAction() != null) {
				stepAction.getAction().run();
			}
		});
		return button;
	}

	public MultiStepViewModel getModel() {
		return model;
	}
}
