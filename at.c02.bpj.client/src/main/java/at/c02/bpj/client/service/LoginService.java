package at.c02.bpj.client.service;

import at.c02.bpj.client.AuthContext;
import at.c02.bpj.client.api.Api;
import at.c02.bpj.client.api.LoginApi;
import at.c02.bpj.client.api.model.Employee;

public class LoginService {

	private LoginApi loginApi;

	public LoginService(LoginApi loginApi) {
		super();
		this.loginApi = loginApi;
	}

	public void login(String username, String password) {
		Api.setUserCredientials(username, password);
		Employee currentUser = Services.executeCall(loginApi.login());
		AuthContext.getInstance().setCurrentUser(currentUser);
	}
}
