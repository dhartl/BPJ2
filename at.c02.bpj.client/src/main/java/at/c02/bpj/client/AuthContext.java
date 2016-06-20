package at.c02.bpj.client;

import at.c02.bpj.client.api.model.Employee;

public class AuthContext {
	private static AuthContext INSTANCE;
	private Employee currentUser;

	public static AuthContext getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AuthContext();
		}
		return INSTANCE;
	}

	public void setCurrentUser(Employee currentUser) {
		this.currentUser = currentUser;
	}

	public Employee getCurrentUser() {
		return currentUser;
	}
}
