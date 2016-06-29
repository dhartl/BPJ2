package at.c02.bpj.client.offer;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.OfferPosition;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    private Label offCustomerID;
    @FXML
    private Label offCompanyName;
    @FXML
    private TextField offSummaryPrice;
    @FXML
    private TableView<OfferPosition> tblOfferPositions;
    @FXML
    private TableColumn<Article, Long> idColumn;
    @FXML
    private TableColumn<Article, String> nameColumn;
    @FXML
    private TableColumn<Article, Double> priceColumn;
    @FXML
    private TableColumn<OfferPosition, Long> idOPColumn;
    @FXML
    private TableColumn<OfferPosition, String> nameOPColumn;
    @FXML
    private TableColumn<OfferPosition, Double> priceOPColumn;
    @FXML
    private TableColumn<OfferPosition, Integer> amountOPColumn;

    @FXML
    private Button btnSaveAndClose;

    private DecimalFormat priceFormat;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	priceFormat = new DecimalFormat("0.00");
	priceFormat.setMaximumFractionDigits(2);
	// offSummaryPrice ist ein Feld mit 2 Nachkommazahlen
	offSummaryPrice.setTextFormatter(new TextFormatter<>(c -> {
	    ParsePosition parsePosition = new ParsePosition(0);
	    Object object = priceFormat.parse(c.getControlNewText(), parsePosition);

	    if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
		return null;
	    } else {
		return c;
	    }
	}));

	offSummaryPrice.setEditable(false);

	// Die Artikel-Tabelle zeigt genau das an, was in der ArticlesProperty
	// des Models ist
	Bindings.bindContent(tblArticles.itemsProperty().get(), model.articlesProperty());
	idColumn.setCellValueFactory(new PropertyValueFactory<>("articleId"));
	nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

	Bindings.bindContent(tblOfferPositions.itemsProperty().get(), model.offerPositionsProperty());
	idOPColumn.setCellValueFactory(new PropertyValueFactory<>("posNr"));

	priceOPColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
	amountOPColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

	nameOPColumn
		.setCellValueFactory(new Callback<CellDataFeatures<OfferPosition, String>, ObservableValue<String>>() {
		    @Override
		    public ObservableValue<String> call(CellDataFeatures<OfferPosition, String> param) {
			return new SimpleStringProperty(param.getValue().getArticle().getName());
		    }
		});

	// amountOPColumn.setCellFactory(TextFieldTableCell.<OfferPosition>
	// forTableColumn());
	//
	// amountOPColumn.setEditable(true);

	// Einfügen des Kontext-Menüs für jede Zeile --ARTIKELTABELLE
	tblArticles.setRowFactory(table -> {
	    final TableRow<Article> row = new TableRow<>();
	    row.setContextMenu(createContextMenu(row));
	    return row;
	});

	// Einfügen des Kontext-Menüs für jede Zeile --POSITIONSTABELLE -- nur
	// wenn rowcell nicht editable sein kann
	tblOfferPositions.setRowFactory(table -> {
	    final TableRow<OfferPosition> row = new TableRow<>();
	    row.setContextMenu(createContextMenuOP(row));
	    return row;
	});
    }

    private ContextMenu createContextMenuOP(TableRow<OfferPosition> row) {

	MenuItem miEditPosition = new MenuItem("Position ändern");

	miEditPosition.setOnAction(event -> onEditOfferPosition(row.getItem()));

	ContextMenu contextMenu = new ContextMenu(miEditPosition);
	return contextMenu;
    }

    private Object onEditOfferPosition(OfferPosition item) {

	return null;
    }

    public void onbtnSaveOffer() {
	model.saveOffer();
	Stage stage = (Stage) btnSaveAndClose.getScene().getWindow();
	stage.close();
    }

    private ContextMenu createContextMenu(TableRow<Article> row) {

	MenuItem miNewPositiontoOffer = new MenuItem("Zum Angebot hinzufügen");
	// Bei Click auf "Zum Angebot hinzufügen..." wird
	// onAddtoOfferArticleClick
	// aufgerufen
	miNewPositiontoOffer.setOnAction(event -> onAddtoOfferArticleClick(row.getItem()));

	ContextMenu contextMenu = new ContextMenu(miNewPositiontoOffer);
	return contextMenu;
    }

    private void onAddtoOfferArticleClick(Article article) {
	showOfferData();
	model.addPositiontoOffer(article);
    }

    // kann nicht bei Initialize gemacht werden weil da das offer property noch
    // null ist
    public void showOfferData() {

	offCustomerID.textProperty()
		.bind(model.offerProperty().get().customerProperty().get().customerIdProperty().asString());
	offCompanyName.textProperty()
		.bindBidirectional(model.offerProperty().get().customerProperty().get().companyNameProperty());

	offSummaryPrice.textProperty().bind(model.sumPriceProperty().asString());
    }
}
