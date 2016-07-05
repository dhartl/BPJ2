package at.c02.bpj.client.ui;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class MultiStepViewModel implements ViewModel {
	private StringProperty breadcrumbs = new SimpleStringProperty();
	private ObjectProperty<Node> viewProperty = new SimpleObjectProperty<>();
	private ObservableList<StepAction> stepActions = FXCollections.observableArrayList();

	public final StringProperty breadcrumbsProperty() {
		return this.breadcrumbs;
	}

	public final java.lang.String getBreadcrumbs() {
		return this.breadcrumbsProperty().get();
	}

	public final void setBreadcrumbs(final java.lang.String breadcrumbs) {
		this.breadcrumbsProperty().set(breadcrumbs);
	}

	public final ObjectProperty<Node> viewPropertyProperty() {
		return this.viewProperty;
	}

	public final javafx.scene.Node getViewProperty() {
		return this.viewPropertyProperty().get();
	}

	public final void setViewProperty(final javafx.scene.Node viewProperty) {
		this.viewPropertyProperty().set(viewProperty);
	}

	public ObservableList<StepAction> getStepActions() {
		return stepActions;
	}
}
