package at.c02.bpj.client.api;

import java.util.List;

import at.c02.bpj.client.api.model.Employee;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeApi {

	@GET("employee")
	Call<List<Employee>> getEmployee();

}
