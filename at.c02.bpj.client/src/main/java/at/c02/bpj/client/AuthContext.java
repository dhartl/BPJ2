package at.c02.bpj.client;

import at.c02.bpj.client.api.model.Employee;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class AuthContext {
	private static AuthContext INSTANCE;
	private ObjectProperty<Employee> currentUser = new SimpleObjectProperty<>();

	public static AuthContext getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AuthContext();
		}
		return INSTANCE;
	}

	public final ObjectProperty<Employee> currentUserProperty() {
		return this.currentUser;
	}

	public final at.c02.bpj.client.api.model.Employee getCurrentUser() {
		return this.currentUserProperty().get();
	}

	public final void setCurrentUser(final at.c02.bpj.client.api.model.Employee currentUser) {
		this.currentUserProperty().set(currentUser);
	}

}
