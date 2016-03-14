package at.c02.bpj.client.service;

import java.util.List;
import java.util.stream.Collectors;

import at.c02.bpj.client.api.ArticleApi;
import at.c02.bpj.client.service.converter.ArticleConverter;
import at.c02.bpj.client.service.model.Article;

public class ArticleService {
	private ArticleConverter converter = new ArticleConverter();
	private ArticleApi articleApi;

	public ArticleService(ArticleApi articleApi) {
		this.articleApi = articleApi;
	}

	public List<Article> getArticles() throws ServiceException {
		return Services.executeCall(articleApi.getArticles()).stream().map(converter::convertToModel)
				.collect(Collectors.toList());
	}

	public void deleteArticle(Article article) throws ServiceException {
		Services.executeCall(articleApi.deleteArticle(article.getArticleId()));

	}

	public Article saveArticle(Article article) throws ServiceException {
		return converter.convertToModel(Services.executeCall(articleApi.saveArticle(converter.convertToDto(article))));
	}

}
