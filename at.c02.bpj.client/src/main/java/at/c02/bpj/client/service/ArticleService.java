package at.c02.bpj.client.service;

import java.util.List;
import java.util.Optional;

import at.c02.bpj.client.api.ArticleApi;
import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.article.ArticleEditDialog;

public class ArticleService {
	private ArticleApi articleApi;

	public ArticleService(ArticleApi articleApi) {
		this.articleApi = articleApi;
	}

	public List<Article> getArticles() throws ServiceException {
		return Services.executeCall(articleApi.getArticles());
	}

	public void deleteArticle(Article article) throws ServiceException {
		Services.executeCall(articleApi.deleteArticle(article.getArticleId()));

	}

	public Article saveArticle(Article article) throws ServiceException {
		return Services.executeCall(articleApi.saveArticle(article));
	}

	public Optional<Article> createArticle() {
		return showArticleDialog(new Article());
	}

	public Optional<Article> editArticle(Article article) {
		return showArticleDialog(article);
	}

	public Optional<Article> showArticleDialog(Article article) {
		ArticleEditDialog dialog = new ArticleEditDialog();
		dialog.setArticle(article);
		return dialog.showAndWait();
	}

}
