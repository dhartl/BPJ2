package at.c02.bpj.client.offer;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.Optional;
import java.util.ResourceBundle;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.OfferPosition;
import at.c02.bpj.client.article.ArticleView;
import at.c02.bpj.client.article.ArticleViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
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
import javafx.scene.layout.BorderPane;
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
    private TableColumn<OfferPosition, Number> priceOPColumn;
    @FXML
    private TableColumn<OfferPosition, Number> amountOPColumn;

    @FXML
    private BorderPane mainPane;

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

	idOPColumn.setCellValueFactory(new PropertyValueFactory<>("posNr"));

	amountOPColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty());

	amountOPColumn.setCellFactory(col -> new IntegerEditingCell());

	amountOPColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

	priceOPColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());

	priceOPColumn.setCellFactory(col -> new DoubleEditingCell());

	priceOPColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

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
	Bindings.bindContentBidirectional(tblOfferPositions.itemsProperty().get(), model.offerPositionsProperty());
	tblOfferPositions.setEditable(true);
    }

    public boolean shutdown() {
	if (!model.offerPositionsProperty().isEmpty()) {
	    Alert confInputAlert = new Alert(AlertType.CONFIRMATION);
	    confInputAlert.setHeaderText("Angebot verwerfen");
	    confInputAlert.setContentText("Wollen Sie das Angebot wirklich verwerfen?");
	    ButtonType buttonTypeOne = new ButtonType("Yes", ButtonData.OK_DONE);
	    ButtonType buttonTypeCancel = new ButtonType("No", ButtonData.CANCEL_CLOSE);
	    confInputAlert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
	    Optional<ButtonType> result = confInputAlert.showAndWait();
	    if (result.get().getButtonData() == ButtonData.CANCEL_CLOSE) {
		return false;
	    }
	    if (result.get().getButtonData() == ButtonData.OK_DONE) {
		return true;
	    }

	}
	return true;
    }

    private ContextMenu createContextMenuOP(TableRow<OfferPosition> row) {

	MenuItem miEditPosition = new MenuItem("Position entfernen");

	miEditPosition.setOnAction(event -> onDeleteOfferPosition(row.getItem()));

	ContextMenu contextMenu = new ContextMenu(miEditPosition);
	return contextMenu;
    }

    private Object onDeleteOfferPosition(OfferPosition item) {
	model.positionNumber--;
	tblOfferPositions.itemsProperty().get().remove(item);
	return null;
    }

    public void onbtnSaveOffer() {
	boolean business_rules_ok = true;

	if (!model.offerPositionsProperty().isEmpty()) {

	    for (OfferPosition op : model.offerPositionsProperty()) {
		if (op.amountProperty().get() > 99 || op.amountProperty().get() < 1) {
		    business_rules_ok = false;
		}
		if (op.priceProperty().get() > 1000000 || op.priceProperty().get() < 0) {
		    business_rules_ok = false;
		}

	    }
	} else {
	    business_rules_ok = false;
	}

	if (!business_rules_ok) {
	    Alert noInputAlert = new Alert(AlertType.WARNING);
	    noInputAlert.setHeaderText("Speichern nicht möglich");
	    noInputAlert.setContentText(
		    "Der Preis oder die Menge eines oder meherer Positionen verletzen die Geschäftsregeln!");
	    noInputAlert.showAndWait();
	    return;
	}

	if (business_rules_ok) {
	    model.saveOffer();
	    Stage stage = (Stage) btnSaveAndClose.getScene().getWindow();
	    stage.close();
	}
    }

    private ContextMenu createContextMenu(TableRow<Article> row) {

	MenuItem miNewPositiontoOffer = new MenuItem("Zum Angebot hinzufügen");
	MenuItem miManageArticle = new MenuItem("Artikel bearbeiten / neu anlegen");
	// Bei Click auf "Zum Angebot hinzufügen..." wird
	// onAddtoOfferArticleClick
	// aufgerufen
	miNewPositiontoOffer.setOnAction(event -> onAddtoOfferArticleClick(row.getItem()));
	miManageArticle.setOnAction(event -> onOpenArticleManagement());

	ContextMenu contextMenu = new ContextMenu(miNewPositiontoOffer, miManageArticle);
	return contextMenu;
    }

    private Object onOpenArticleManagement() {
	Parent root;
	ViewTuple<ArticleView, ArticleViewModel> viewTuple = FluentViewLoader.fxmlView(ArticleView.class).load();
	// Übergabe des erstellten Angebotes an das neue Fenster
	root = viewTuple.getView();
	Stage stage = new Stage();
	Scene scene = new Scene(root, 800, 600);

	stage.setTitle("Artikel bearbeiten / new anlegen");
	stage.setScene(scene);
	stage.show();
	return null;
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
