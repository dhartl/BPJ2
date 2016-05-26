package at.c02.bpj.client.article;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;

import at.c02.bpj.client.api.model.Category;
import at.c02.bpj.client.converter.StringToNumberConverter;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

/**
 * Controller von ArticleEditView.fxml
 */
public class ArticleEditView implements FxmlView<ArticleEditViewModel>, Initializable {

	@InjectViewModel
	private ArticleEditViewModel model;

	@FXML
	private TextField tfId;
	@FXML
	private TextField tfName;
	@FXML
	private TextField tfPrice;
	@FXML
	private TextArea taDescription;
	@FXML
	private ComboBox<Category> cmbCategory;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DecimalFormat idFormat = new DecimalFormat();
		idFormat.setParseIntegerOnly(true);

		// tfId ist ein Ganzzahlfeld
		tfId.setTextFormatter(new TextFormatter<>(c -> {
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = idFormat.parse(c.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else {
				return c;
			}
		}));
		DecimalFormat priceFormat = new DecimalFormat("0.00");
		priceFormat.setMaximumFractionDigits(2);
		// tfPrice ist ein Feld mit 2 Nachkommazahlen
		tfPrice.setTextFormatter(new TextFormatter<>(c -> {
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = priceFormat.parse(c.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else {
				return c;
			}
		}));

		// Binden der Text-Properties der Textfelder mit den Model-Properties
		// Konversaton von Text in Zahl passiert automatisch!
		Bindings.bindBidirectional(tfId.textProperty(), model.idProperty(),
				new StringToNumberConverter<Long>(idFormat));
		tfName.textProperty().bindBidirectional(model.nameProperty());
		Bindings.bindBidirectional(tfPrice.textProperty(), model.priceProperty(), priceFormat);

		taDescription.textProperty().bindBidirectional(model.descriptionProperty());
		Bindings.bindContent(cmbCategory.itemsProperty().get(), model.categoryListProperty());
		cmbCategory.valueProperty().bindBidirectional(model.categoryProperty());
		cmbCategory.setConverter(new StringConverter<Category>() {

			@Override
			public String toString(Category category) {
				return category.getName();
			}

			@Override
			public Category fromString(String string) {
				return null;
			}
		});
	}

}
