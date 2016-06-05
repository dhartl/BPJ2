package at.c02.bpj.client.api;

import java.util.List;

import at.c02.bpj.client.api.model.Customer;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CustomerApi {


	
	@GET("customer")
	Call<List<Customer>> getCustomer();
	
	@DELETE("customer")
	Call<Void> deleteCustomer(@Path("articleId") long customerID);

	
}