package at.c02.bpj.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import at.c02.bpj.server.entity.Article;

/**
 * Repository, das den Zugriff auf Artikel in der Datenbank managt
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

	@Query("select article from Article article join fetch article.category")
	List<Article> findAllFetchCategory();

}
