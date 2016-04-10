package at.c02.bpj.client.article;

import java.net.URL;
import java.util.ResourceBundle;

import at.c02.bpj.client.api.model.Article;
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

/**
 * Controller für ArticleView.fxml.
 */
public class ArticleView implements FxmlView<ArticleViewModel>, Initializable {

	// ViewModel wird vom FluentFxmlLoader erzeugt und eingefügt
	@InjectViewModel
	private ArticleViewModel model;
	// FXML-Properties: werden in der .fxml-Datei angegeben mit fx:id
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
		// Die Artikel-Tabelle zeigt genau das an, was in der ArticlesProperty
		// des Models ist
		Bindings.bindContent(tblArticles.itemsProperty().get(), model.articlesProperty());
		idColumn.setCellValueFactory(new PropertyValueFactory<>("articleId"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

		// Einfügen des Kontext-Menüs für jede Zeile
		tblArticles.setRowFactory(table -> {
			final TableRow<Article> row = new TableRow<>();
			row.setContextMenu(createContextMenu(row));
			return row;
		});
	}

	private ContextMenu createContextMenu(TableRow<Article> row) {
		MenuItem miNewArticle = new MenuItem("Neu...");
		// Bei Click auf "Neu..." wird onNewArticleClick aufgerufen
		miNewArticle.setOnAction(event -> onNewArticleClick());

		MenuItem miEditArticle = new MenuItem("Bearbeiten...");
		// Bei Click auf "Bearbeiten..." wird onEditArticleClick aufgerufen
		miEditArticle.setOnAction(event -> onEditArticleClick(row.getItem()));
		// Bearbeiten ist nur enabled, wenn die Zeile einen Datensatz beinhaltet
		miEditArticle.disableProperty().bind(row.emptyProperty());

		MenuItem miDeleteArticle = new MenuItem("Löschen");
		// Bei Click auf "Löschen" wird onDeleteArticleClick aufgerufen
		miDeleteArticle.setOnAction(event -> onDeleteArticleClick(row.getItem()));
		// Löschen ist nur enabled, wenn die Zeile einen Datensatz beinhaltet
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
