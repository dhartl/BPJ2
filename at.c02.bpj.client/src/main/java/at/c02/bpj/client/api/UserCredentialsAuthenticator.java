package at.c02.bpj.client.api;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Response;

public class UserCredentialsAuthenticator implements Interceptor {
	private String credential;

	public UserCredentialsAuthenticator() {
	}

	public void setCredentials(String username, String password) {
		credential = Credentials.basic(username, password);
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		return chain.proceed(chain.request().newBuilder().header("Authorization", credential).build());
	}

}
