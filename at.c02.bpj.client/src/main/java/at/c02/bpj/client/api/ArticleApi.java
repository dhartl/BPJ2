package at.c02.bpj.client.api;

import java.util.List;

import at.c02.bpj.client.api.model.Article;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Server-API für Artikel
 */
public interface ArticleApi {

	/**
	 * holt alle Artikel vom Server
	 */
	@GET("articles")
	Call<List<Article>> getArticles();

	/**
	 * Speichert einen neuen oder aktualisierten Artikel
	 * 
	 * @param article
	 * @return der gespeicherte Artikel
	 */
	@POST("articles")
	Call<Article> saveArticle(@Body Article article);
}
