package at.c02.bpj.client.api;

import java.util.List;

import at.c02.bpj.client.api.model.Article;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
	 * löscht den Artikel zu der ArtikelId
	 * 
	 * @param articleId
	 */
	@DELETE("articles/{articleId}")
	Call<Void> deleteArticle(@Path("articleId") long articleId);

	/**
	 * Speichert einen neuen oder aktualisierten Artikel
	 * 
	 * @param article
	 * @return der gespeicherte Artikel
	 */
	@POST("articles")
	Call<Article> saveArticle(@Body Article article);
}
