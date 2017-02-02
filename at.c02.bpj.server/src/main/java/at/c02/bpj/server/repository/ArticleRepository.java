package at.c02.bpj.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import at.c02.bpj.server.entity.Article;

/**
 * Repository, das den Zugriff auf Artikel in der Datenbank managt
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

	@Query("select distinct article from Article article join fetch article.category order by article.articleId")
	List<Article> findAllFetchCategory();

	@Query("select distinct article from Article article join fetch article.category "
			+ "where lower(article.name) = lower(?1) order by article.articleId")
	List<Article> findByName(String name);

	@Query("select distinct article from Article article join fetch article.category "
			+ "where lower(article.name) like lower(?1) order by article.articleId")
	List<Article> findByNameLike(String string);

}
