package at.c02.bpj.client.article;


import java.util.List;
import java.util.Optional;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.primitives.Longs;

import at.c02.bpj.client.Async;
import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Category;

import at.c02.bpj.client.offer.management.OfferManagementDialog;
import at.c02.bpj.client.service.ArticleService;

import at.c02.bpj.client.service.CategoryService;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
	private ObservableList<Category> categoryList = FXCollections.observableArrayList();
	private ArticleService articleService;

	private FilteredList<Article> filteredArticleList = new FilteredList<>(articles);
	
	// Search Field Properties für Bindings
	private ObjectProperty<Category> searchCategory = new SimpleObjectProperty<>();
	private StringProperty searchArticleID = new SimpleStringProperty();
	private StringProperty searchArticleName = new SimpleStringProperty();
	
	// Service Klassen mittels Construktor-Injection setzen
	public ArticleViewModel(CategoryService categoryService, ArticleService articleService) {
		Async.executeUILoad(categoryService::getCategories, categoryList::setAll);
		Async.executeUILoad(articleService::getArticles, articles::setAll);
		//this.articleService = articleService;

		//loadArticles();
	}
	


	/**
	 * Lädt die Artikel
	 */
	public void loadArticles() {
		Async.executeUILoad(articleService::getArticles, articles::setAll);
	}

	public ObservableList<Article> articlesProperty() {
		return filteredArticleList;
	}

	public ObservableList<Category> categoryProperty() {
		return categoryList;
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
		public void onSearchButtonClick() {
		
		if (Strings.isNullOrEmpty(searchArticleID.getValue()) &&  searchArticleName.getValue() == null &&
				searchArticleID.getValue() == null) {
		
				 Alert noInputAlert= new Alert(AlertType.WARNING);
			        noInputAlert.setHeaderText("Keine Eingabe vorhanden");
			        noInputAlert.setContentText("Bitte mindestens ein Suchkriterium eingeben");
			        noInputAlert.showAndWait();
		}
		
		filteredArticleList.setPredicate(this::checkArticleMatchesSearch);
		
		if (filteredArticleList.isEmpty()) {
			Alert noMatchFoundAlert= new Alert(AlertType.INFORMATION);
	        noMatchFoundAlert.setHeaderText("Kein Angebot mit angegebenen Suchkriterien gefunden");
			noMatchFoundAlert.setContentText("Bitte prüfen Sie die angegebenen Suchkriterien");
	        noMatchFoundAlert.showAndWait();
		}
	}
		
		private boolean checkArticleMatchesSearch(Article article) {
			String articleId = searchArticleID.getValue();
			Category category = searchCategory.getValue();
			String articleName = searchArticleName.getValue();

			if (!Strings.isNullOrEmpty(articleId) && !Objects.equal(article.getArticleId(), Longs.tryParse(articleId))) {
				return false;
			}
			
			if (!Strings.isNullOrEmpty(articleName) && !Objects.equal(article.getName(), articleName)) {
				return false;
			}
			


			if (category != null && !Objects.equal(category.getCategoryId(), article.getCategory().getCategoryId())) {
				return false;
			}

			return true;
		}
		
		public ObservableList<Category> categoryListProperty() {
			return categoryList;
		}
		public ObservableList<Article> articleListProperty() {
			return filteredArticleList;
		}
		public final StringProperty searchArticleIdProperty() {
			return this.searchArticleID;
		}
		public final java.lang.String getSearchArticleId() {
			return this.searchArticleIdProperty().get();
		}
		public final StringProperty searchArticleNameProperty() {
			return this.searchArticleName;
		}
		public final java.lang.String getSearchArticleName() {
			return this.searchArticleNameProperty().get();
		}
		public final void setSearchArticleId(final java.lang.String searchArticleId) {
			this.searchArticleIdProperty().set(searchArticleId);
		}
		public final ObjectProperty<Category> searchCategoryProperty() {
			return this.searchCategory;
		}

		public final at.c02.bpj.client.api.model.Category getSearchCategory() {
			return this.searchCategoryProperty().get();
		}

		public final void setSearchCategory(final at.c02.bpj.client.api.model.Category searchCategory) {
			this.searchCategoryProperty().set(searchCategory);
		}
		
		
		
		
		
		
		
		
	// UC006 öffnen des OfferManagements
	public void openOfferManagement() {
		OfferManagementDialog dialog = new OfferManagementDialog();
		dialog.show();
	}

}
