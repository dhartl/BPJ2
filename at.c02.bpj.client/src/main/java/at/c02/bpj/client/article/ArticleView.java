package at.c02.bpj.client.article;

import java.net.URL;
import java.util.ResourceBundle;

import at.c02.bpj.client.service.model.Article;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ArticleView implements FxmlView<ArticleViewModel>, Initializable {

	@InjectViewModel
	private ArticleViewModel model;
	@FXML
	private TableView<Article> tblArticles;
	@FXML
	private TableColumn<Article, Long> idColumn;
	@FXML
	private TableColumn<Article, String> nameColumn;
	@FXML
	private TableColumn<Article, Double> priceColumn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Bindings.bindContent(tblArticles.itemsProperty().get(), model.articlesProperty());
		idColumn.setCellValueFactory(new PropertyValueFactory<>("articleId"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

		tblArticles.setRowFactory(table -> {
			final TableRow<Article> row = new TableRow<>();
			row.setContextMenu(createContextMenu(row));
			return row;
		});
	}

	private ContextMenu createContextMenu(TableRow<Article> row) {
		MenuItem miNewArticle = new MenuItem("Neu...");
		miNewArticle.setOnAction(event -> onNewArticleClick());
		MenuItem miEditArticle = new MenuItem("Bearbeiten...");
		miEditArticle.setOnAction(event -> onEditArticleClick(row.getItem()));
		miEditArticle.disableProperty().bind(row.emptyProperty());
		MenuItem miDeleteArticle = new MenuItem("Löschen");
		miDeleteArticle.setOnAction(event -> onDeleteArticleClick(row.getItem()));
		miDeleteArticle.disableProperty().bind(row.emptyProperty());
		ContextMenu contextMenu = new ContextMenu(miNewArticle, miEditArticle, miDeleteArticle);
		return contextMenu;
	}

	public void onNewArticleClick() {
		model.newArticle();
	}

	public void onEditArticleClick(Article article) {
		model.editArticle(article);
	}

	public void onDeleteArticleClick(Article article) {
		model.deleteArticle(article);
	}

}
