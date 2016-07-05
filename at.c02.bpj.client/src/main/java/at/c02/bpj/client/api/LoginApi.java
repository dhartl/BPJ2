package at.c02.bpj.client.api;

import at.c02.bpj.client.api.model.Employee;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LoginApi {

	@GET("login")
	public Call<Employee> login();
}
