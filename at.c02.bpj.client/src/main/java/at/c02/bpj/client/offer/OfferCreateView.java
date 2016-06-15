package at.c02.bpj.client.offer;

import java.net.URL;
import java.util.ResourceBundle;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.OfferPosition;
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
 * Controller für OfferCreateView.fxml.
 */

public class OfferCreateView implements FxmlView<OfferCreateViewModel>, Initializable {

    // ViewModel wird vom FluentFxmlLoader erzeugt und eingefügt
    @InjectViewModel
    private OfferCreateViewModel model;
    // FXML-Properties: werden in der .fxml-Datei angegeben mit fx:id
    @FXML
    private TableView<Article> tblArticles;
    @FXML
    private TableView<OfferPosition> tblOfferPositions;
    @FXML
    private TableColumn<Article, Long> idColumn;
    @FXML
    private TableColumn<Article, String> nameColumn;
    @FXML
    private TableColumn<Article, Double> priceColumn;
    @FXML
    private TableColumn<Article, Long> idOPColumn;
    @FXML
    private TableColumn<Article, String> nameOPColumn;
    @FXML
    private TableColumn<Article, Double> priceOPColumn;
    @FXML
    private TableColumn<Article, Double> amountOPColumn;

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

	Article article = row.getItem();
	MenuItem miNewPositiontoOffer = new MenuItem("Zum Angebot hinzufügen");
	// Bei Click auf "Zum Angebot hinzufügen..." wird
	// onAddtoOfferArticleClick
	// aufgerufen
	miNewPositiontoOffer.setOnAction(event -> onAddtoOfferArticleClick(article));

	ContextMenu contextMenu = new ContextMenu(miNewPositiontoOffer);
	return contextMenu;
    }

    private void onAddtoOfferArticleClick(Article article) {
	model.addPositiontoOffer(article);
    }

}
