package at.c02.bpj.client.ui;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.Context;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectContext;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class MainView implements FxmlView<MainViewModel>, Initializable {

	@FXML
	private BorderPane mainView;
	@FXML
	private ListView<MainMenuItem> lvMenu;

	@InjectViewModel
	private MainViewModel mainViewModel;

	@InjectContext
	private Context context;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainViewModel.setContext(context);
		lvMenu.setCellFactory((listView) -> new MainMenuItemListCell());
		Bindings.bindContent(lvMenu.getItems(), mainViewModel.getMainMenuItems());
		mainViewModel.selectedMenuItemProperty().bind(lvMenu.getSelectionModel().selectedItemProperty());
		mainView.centerProperty().bind(mainViewModel.centerNodeProperty());
	}

}
