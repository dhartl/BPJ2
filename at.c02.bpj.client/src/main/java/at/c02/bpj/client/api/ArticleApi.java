package at.c02.bpj.client.api;

import java.util.List;

import at.c02.bpj.api.ArticleDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ArticleApi {

	@GET("articles")
	Call<List<ArticleDTO>> getArticles();

	@DELETE("articles/{articleId}")
	Call<Void> deleteArticle(@Path("articleId") long articleId);

	@POST("articles")
	Call<ArticleDTO> saveArticle(@Body ArticleDTO article);
}
