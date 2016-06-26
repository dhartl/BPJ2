package at.c02.bpj.client.article;

import java.util.List;
import java.util.Optional;

import at.c02.bpj.client.Async;
import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.offer.management.OfferManagementDialog;
import at.c02.bpj.client.service.ArticleService;
import de.saxsys.mvvmfx.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Model für den {@link ArticleView}
 */
public class ArticleViewModel implements ViewModel {
	/**
	 * Liste aller Artikel
	 */
	private ObservableList<Article> articles = FXCollections.observableArrayList();

	private ArticleService articleService;

	// ArticleService wird mittels Construktor-Injection gesetzt
	public ArticleViewModel(ArticleService articleService) {
		this.articleService = articleService;

		loadArticles();
	}

	/**
	 * Lädt die Artikel
	 */
	public void loadArticles() {
		Async.executeUILoad(articleService::getArticles, articles::setAll);
	}

	public ObservableList<Article> articlesProperty() {
		return articles;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles.setAll(articles);
	}

	/**
	 * Erstellt einen neuen Artikel
	 */
	public void newArticle() {
		ArticleEditDialog dialog = new ArticleEditDialog();
		dialog.setArticle(new Article());
		Optional<Article> article = dialog.showAndWait();
		if (article.isPresent()) {
			Article newArticle = articleService.saveArticle(article.get());
			articles.add(newArticle);
		}
	}

	/**
	 * Bearbeitet den Artikel
	 * 
	 * @param article
	 */
	public void editArticle(Article article) {
		ArticleEditDialog dialog = new ArticleEditDialog();
		dialog.setArticle(article);
		Optional<Article> newArticle = dialog.showAndWait();
		if (newArticle.isPresent()) {
			// Speichert den Artikel
			Article savedArticle = articleService.saveArticle(newArticle.get());
			// aktualisiert den Artikel in der Articles-Liste
			articles.set(articles.indexOf(article), savedArticle);
		}
	}

	/**
	 * Löscht den Artikel
	 * 
	 * @param article
	 */
	public void deleteArticle(Article article) {
		// Sicherheitsabfrage
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Artikel löschen");
		alert.setHeaderText(null);
		alert.setContentText(String.format("Möchten Sie den Artikel '%s' wirklich löschen?", article.getName()));

		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(ButtonType.CANCEL) == ButtonType.OK) {
			articleService.deleteArticle(article);
			loadArticles();
		}
	}

	// UC006 öffnen des OfferManagements
	public void openOfferManagement() {
		OfferManagementDialog dialog = new OfferManagementDialog();
		dialog.show();
	}

}
