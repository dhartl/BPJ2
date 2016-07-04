package at.c02.bpj.client.api;

import java.util.List;

import at.c02.bpj.client.api.model.Offer;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OfferApi {

	@GET("offer")
	Call<List<Offer>> getOffer();

	@POST("offer")
	Call<Offer> saveOffer(@Body Offer offer);

}
