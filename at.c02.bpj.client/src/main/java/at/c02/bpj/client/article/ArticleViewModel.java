package at.c02.bpj.client.article;

import java.util.List;
import java.util.Optional;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.service.ArticleService;
import at.c02.bpj.client.service.ServiceException;
import de.saxsys.mvvmfx.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ArticleViewModel implements ViewModel {
	private ObservableList<Article> articles = FXCollections.observableArrayList();

	private ArticleService articleService;

	public ArticleViewModel(ArticleService articleService) {
		this.articleService = articleService;

		loadArticles();
	}

	private void loadArticles() {
		try {
			List<Article> artciles = articleService.getArticles();
			setArticles(artciles);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
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

	public void newArticle() {
		ArticleEditDialog dialog = new ArticleEditDialog();
		dialog.setArticle(new Article());
		Optional<Article> article = dialog.showAndWait();
		if (article.isPresent()) {
			Article newArticle = articleService.saveArticle(article.get());
			articles.add(newArticle);
		}
	}

	public void editArticle(Article article) {
		ArticleEditDialog dialog = new ArticleEditDialog();
		dialog.setArticle(article);
		Optional<Article> newArticle = dialog.showAndWait();
		if (newArticle.isPresent()) {
			articles.set(articles.indexOf(article), articleService.saveArticle(newArticle.get()));
		}
	}

	public void deleteArticle(Article article) {
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
}
