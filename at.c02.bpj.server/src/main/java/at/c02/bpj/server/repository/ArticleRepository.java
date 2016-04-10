package at.c02.bpj.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import at.c02.bpj.server.entity.Article;

/**
 * Repository, das den Zugriff auf Artikel in der Datenbank managt
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

	/**
	 * findet alle Artikel, deren Name gleich dem Parameter name ist
	 * 
	 * @param name
	 * @return Liste der gefundenen Artikel
	 */
	List<Article> findByName(@Param("name") String name);

}
