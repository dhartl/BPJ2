package at.c02.bpj.client.article;

import java.net.URL;
import java.util.ResourceBundle;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Category;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

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
	@FXML
	private TableColumn<Article, String> categoryColumn;
	
	@FXML
	private GridPane searchGridPane;
	@FXML
	private TextField idField;
	@FXML
	private TextField nameField;
	@FXML
	private ComboBox<Category> categoryField;
	@FXML
	private Button searchButton;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Die Artikel-Tabelle zeigt genau das an, was in der ArticlesProperty
		// des Models ist
		
		Bindings.bindContent(tblArticles.itemsProperty().get(), model.articlesProperty());
		idColumn.setCellValueFactory(new PropertyValueFactory<>("articleId"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


		categoryColumn.setCellValueFactory(new Callback<CellDataFeatures<Article, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Article, String> param) {
				return new SimpleStringProperty(param.getValue().getCategory().getName());
			}
		});
		
		// Einfügen des Kontext-Menüs für jede Zeile
		tblArticles.setRowFactory(table -> {
			final TableRow<Article> row = new TableRow<>();
			row.setContextMenu(createContextMenu(row));
			return row;
		});
		
		// für ComboBox Category (Binding zwischen ComboBox Feld und
		// CategoryProperty
		Bindings.bindContent(categoryField.itemsProperty().get(), model.categoryProperty());
		categoryField.valueProperty().bindBidirectional(model.searchCategoryProperty());

		categoryField.setConverter(new StringConverter<Category>() {

			@Override
			public String toString(Category category) {
				return category.getName();
			}

			@Override
			public Category fromString(String string) {
				return null;
			}
		});
		

		idField.textProperty().bindBidirectional(model.searchArticleIdProperty());
		nameField.textProperty().bindBidirectional(model.searchArticleNameProperty());
		
		
		
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

		// MenuItem miDeleteArticle = new MenuItem("Löschen");
		// Bei Click auf "Löschen" wird onDeleteArticleClick aufgerufen
		//miDeleteArticle.setOnAction(event -> onDeleteArticleClick(row.getItem()));
		// Löschen ist nur enabled, wenn die Zeile einen Datensatz beinhaltet
		//miDeleteArticle.disableProperty().bind(row.emptyProperty());

		ContextMenu contextMenu = new ContextMenu(miNewArticle, miEditArticle);
		return contextMenu;
	}

	public void onNewArticleClick() {
		model.newArticle();
	}

	public void onEditArticleClick(Article article) {
		model.editArticle(article);
	}

	@FXML
	public void onSearchButtonClick() {
		model.onSearchButtonClick();
	}
	
	// OfferManagment UC006 öffnen im Fenster
	public void onOfferManagement() {
		model.openOfferManagement();
	}

}
