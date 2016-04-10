package at.c02.bpj.client.api.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model-Objekt für Fehlermeldungen vom Server
 */
public class ServerError {
	private StringProperty message = new SimpleStringProperty();

	public ServerError() {
		super();
	}

	public final StringProperty messageProperty() {
		return this.message;
	}

	public final java.lang.String getMessage() {
		return this.messageProperty().get();
	}

	public final void setMessage(final java.lang.String message) {
		this.messageProperty().set(message);
	}

}