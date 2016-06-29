package at.c02.bpj.client.api;

import java.util.List;

import at.c02.bpj.client.api.model.OfferPosition;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OfferPositionApi {

    /**
     * holt alle OfferPositionen vom Server
     */
    @GET("offerPosition")
    Call<List<OfferPosition>> getOfferPosition();

    /**
     * löscht die OfferPosition zu der OfferPositionID
     * 
     * @param offerPositionID
     */
    @DELETE("offerPositions/{offerPositionID}")
    Call<Void> deleteOfferPosition(@Path("offerPositionID") long offerPositionID);

    /**
     * Speichert einen neue oder aktualisierte OfferPosition
     * 
     * @param offerPosition
     * @return die gespeicherte OfferPosition
     */
    @POST("offerPosition")
    Call<OfferPosition> saveOfferPosition(@Body OfferPosition offerPosition);

}
