package at.c02.bpj.client.ui;

import de.saxsys.mvvmfx.Context;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class MainViewModel implements ViewModel {
	private ObjectProperty<Node> centerNode = new SimpleObjectProperty<>();
	private ObservableList<MainMenuItem> mainMenuItems = FXCollections.observableArrayList();
	private ObjectProperty<MainMenuItem> selectedMenuItem = new SimpleObjectProperty<>();

	private Context context;

	public MainViewModel() {
		centerNode.bind(Bindings.createObjectBinding(this::updateCenterNode, selectedMenuItem));
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

}
