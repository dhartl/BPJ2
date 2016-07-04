package at.c02.bpj.client.ui;

import at.c02.bpj.client.AuthContext;
import at.c02.bpj.client.api.model.Employee;
import de.saxsys.mvvmfx.Context;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class MainViewModel implements ViewModel {
	private ObjectProperty<Node> centerNode = new SimpleObjectProperty<>();
	private ObservableList<MainMenuItem> mainMenuItems = FXCollections.observableArrayList();
	private ObjectProperty<MainMenuItem> selectedMenuItem = new SimpleObjectProperty<>();
	private StringProperty username = new SimpleStringProperty();

	private Context context;

	public MainViewModel() {
		centerNode.bind(Bindings.createObjectBinding(this::updateCenterNode, selectedMenuItem));
		StringBinding usernameBinding = Bindings.selectString(AuthContext.getInstance().currentUserProperty(),
				"username");
		StringBinding firstNameBinding = Bindings.selectString(AuthContext.getInstance().currentUserProperty(),
				"firstname");
		StringBinding lastNameBinding = Bindings.selectString(AuthContext.getInstance().currentUserProperty(),
				"lastname");

		username.bind(
				Bindings.createStringBinding(this::getUserInfo, usernameBinding, firstNameBinding, lastNameBinding));
	}

	private String getUserInfo() {
		Employee currentUser = AuthContext.getInstance().getCurrentUser();
		if (currentUser == null) {
			return "";
		}
		return String.format("Angemeldet als: %s (%s %s)", currentUser.getUsername(), currentUser.getFirstname(),
				currentUser.getLastname());
	}

	private Node updateCenterNode() {
		MainMenuItem selectedItem = selectedMenuItem.get();
		if (selectedItem == null) {
			return null;
		} else {
			return FluentViewLoader.fxmlView(selectedItem.getViewModelClass()).context(context).load().getView();
		}
	}

	public ObservableList<MainMenuItem> getMainMenuItems() {
		return mainMenuItems;
	}

	public final ObjectProperty<Node> centerNodeProperty() {
		return this.centerNode;
	}

	public final javafx.scene.Node getCenterNode() {
		return this.centerNodeProperty().get();
	}

	public final void setCenterNode(final javafx.scene.Node centerNode) {
		this.centerNodeProperty().set(centerNode);
	}

	public final ObjectProperty<MainMenuItem> selectedMenuItemProperty() {
		return this.selectedMenuItem;
	}

	public final at.c02.bpj.client.ui.MainMenuItem getSelectedMenuItem() {
		return this.selectedMenuItemProperty().get();
	}

	public final void setSelectedMenuItem(final at.c02.bpj.client.ui.MainMenuItem selectedMenuItem) {
		this.selectedMenuItemProperty().set(selectedMenuItem);
	}

	public void setContext(Context context) {
		this.context = context;
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

}
