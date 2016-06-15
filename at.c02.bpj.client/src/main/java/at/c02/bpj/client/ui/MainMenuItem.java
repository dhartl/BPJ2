package at.c02.bpj.client.ui;

import de.saxsys.mvvmfx.FxmlView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainMenuItem {
	private StringProperty title = new SimpleStringProperty();
	private Class<? extends FxmlView<?>> viewModelClass;

	public final StringProperty titleProperty() {
		return this.title;
	}

	public MainMenuItem(String title, Class<? extends FxmlView<?>> viewModelClass) {
		super();
		setTitle(title);
		this.viewModelClass = viewModelClass;
	}

	public final java.lang.String getTitle() {
		return this.titleProperty().get();
	}

	public final void setTitle(final java.lang.String title) {
		this.titleProperty().set(title);
	}

	public Class<? extends FxmlView<?>> getViewModelClass() {
		return viewModelClass;
	}

	public void setViewModelClass(Class<? extends FxmlView<?>> viewModelClass) {
		this.viewModelClass = viewModelClass;
	}

}
