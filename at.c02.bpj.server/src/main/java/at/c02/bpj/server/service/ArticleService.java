package at.c02.bpj.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.c02.bpj.server.entity.Article;
import at.c02.bpj.server.repository.ArticleRepository;

/**
 * Business-Logik für den Zugriff auf Artikel
 */
@Service
public class ArticleService {

	private ArticleRepository articleRepository;

	// Spring managed diese Klasse. @Autowired-Felder werden automatisch befüllt
	@Autowired
	public void setArticleRepository(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	/**
	 * findet alle Artikel
	 * 
	 * @return Liste aller Artikel
	 */
	public List<Article> getAllArticles() {
		return articleRepository.findAllFetchCategory();
	}

	/**
	 * erstellt oder aktualisiert den Artikel. Ob ein Artikel bereits existiert
	 * wird an der ArtikelId bestimmt
	 * 
	 * @param article
	 * @return der gespeicherte Artikel
	 */
	public Article createOrUpdateArticle(Article article) {
		return articleRepository.save(article);
	}

	public Article getArticleById(Long articleId) {
		return articleRepository.findOne(articleId);
	}

}
