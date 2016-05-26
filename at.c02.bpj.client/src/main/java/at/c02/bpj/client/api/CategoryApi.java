package at.c02.bpj.client.api;

import java.util.List;

import at.c02.bpj.client.api.model.Category;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryApi {

	@GET("category")
	Call<List<Category>> getCategories();

}
