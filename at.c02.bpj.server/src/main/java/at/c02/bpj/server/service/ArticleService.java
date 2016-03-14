package at.c02.bpj.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.c02.bpj.server.entity.Article;
import at.c02.bpj.server.repository.ArticleRepository;

@Service
public class ArticleService {

	private ArticleRepository articleRepository;

	@Autowired
	public void setArticleRepository(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public List<Article> getAllArticles() {
		return articleRepository.findAll();
	}

	public void deleteArticle(long articleId) {
		articleRepository.delete(articleId);
	}

	public Article createOrUpdateArticle(Article article) {
		return articleRepository.save(article);
	}

}
