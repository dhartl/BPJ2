package at.c02.bpj.client.customer;

import java.net.URL;
import java.util.ResourceBundle;



import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Customer;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;



/**
 * Controller für ArticleView.fxml.
 */
public class CustomerView implements FxmlView<CustomerViewModel>, Initializable {

	// ViewModel wird vom FluentFxmlLoader erzeugt und eingefügt
	@InjectViewModel
	private CustomerViewModel model;
	// FXML-Properties: werden in der .fxml-Datei angegeben mit fx:id
	@FXML
	private TableView<Customer> tblCustomer;
	@FXML
	private TableColumn<Customer, Long> idColumn;
	@FXML
	private TableColumn<Article, String> firstnameColumn;
	@FXML
	private TableColumn<Article, String> lastnameColumn;
	@FXML
	private TableColumn<Article, String> companynameColumn;
	
	@FXML
	private GridPane searchGridPane;
	@FXML
	private TextField idField;
	@FXML
	private TextField firstnameField;
	@FXML
	private TextField lastnameField;
	@FXML
	private TextField companynameField;
	@FXML
	private Button searchButton;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Die Artikel-Tabelle zeigt genau das an, was in der ArticlesProperty
		// des Models ist
		
		Bindings.bindContent(tblCustomer.itemsProperty().get(), model.customerProperty());
		idColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
		firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("contactFirstName"));
		lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("contactLastName"));
		companynameColumn.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));


		
		
		
		// Einfügen des Kontext-Menüs für jede Zeile
		tblCustomer.setRowFactory(table -> {
			final TableRow<Customer> row = new TableRow<>();
			row.setContextMenu(createContextMenu(row));
			return row;
		});
		
		// für ComboBox Category (Binding zwischen ComboBox Feld und
		// CategoryProperty
		


		
		

		idField.textProperty().bindBidirectional(model.searchCustomerIdProperty());
		firstnameField.textProperty().bindBidirectional(model.searchCustomerFirstNameProperty());
		lastnameField.textProperty().bindBidirectional(model.searchCustomerLastNameProperty());
		companynameField.textProperty().bindBidirectional(model.searchCompanyNameProperty());
		
		
	}

	private ContextMenu createContextMenu(TableRow<Customer> row) {
		MenuItem miNewCustomer = new MenuItem("Neu...");
		// Bei Click auf "Neu..." wird onNewArticleClick aufgerufen
		miNewCustomer.setOnAction(event -> onNewCustomerClick());

		MenuItem miEditCustomer = new MenuItem("Bearbeiten...");
		// Bei Click auf "Bearbeiten..." wird onEditArticleClick aufgerufen
		miEditCustomer.setOnAction(event -> onEditCustomerClick(row.getItem()));
		// Bearbeiten ist nur enabled, wenn die Zeile einen Datensatz beinhaltet
		miEditCustomer.disableProperty().bind(row.emptyProperty());

		MenuItem miDeleteCustomer = new MenuItem("Löschen");
		// Bei Click auf "Löschen" wird onDeleteArticleClick aufgerufen
		//miDeleteCustomer.setOnAction(event -> onDeleteCustomerClick(row.getItem()));
		// Löschen ist nur enabled, wenn die Zeile einen Datensatz beinhaltet
		miDeleteCustomer.disableProperty().bind(row.emptyProperty());

		ContextMenu contextMenu = new ContextMenu(miNewCustomer, miEditCustomer, miDeleteCustomer);
		return contextMenu;
	}

	public void onNewCustomerClick() {
		model.newCustomer();
	}

	public void onEditCustomerClick(Customer customer) {
		model.editCustomer(customer);
	}

//	public void onDeleteCustomerClick(Article customer) {
//		model.deleteCustomer(customer);
//	}
	
	@FXML
	public void onSearchButtonClick() {
		model.onSearchButtonClick();
	}
	
	//OfferManagment UC006 öffnen im Fenster
	public void onOfferManagement() {
		model.openOfferManagement();
	}

}
