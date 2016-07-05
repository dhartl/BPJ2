package at.c02.bpj.client.api;

import java.util.List;

import at.c02.bpj.client.api.model.Customer;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CustomerApi {

	@GET("customer")
	Call<List<Customer>> getCustomer();

	@POST("customer")
	Call<Customer> saveCustomer(@Body Customer customer);

}
