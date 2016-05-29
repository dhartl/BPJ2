package at.c02.bpj.client.api;

import java.util.List;

import at.c02.bpj.client.api.model.OfferPosition;
import retrofit2.Call;
import retrofit2.http.GET;

public interface OfferPositionApi {

	@GET("offerPosition")
	Call<List<OfferPosition>> getOfferPosition();

	
}
