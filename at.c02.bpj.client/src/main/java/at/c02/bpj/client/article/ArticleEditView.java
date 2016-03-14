package at.c02.bpj.client.article;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;

import at.c02.bpj.client.converter.StringToNumberConverter;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class ArticleEditView implements FxmlView<ArticleEditViewModel>, Initializable {

	@InjectViewModel
	private ArticleEditViewModel model;
	@FXML
	private TextField tfId;
	@FXML
	private TextField tfName;
	@FXML
	private TextField tfPrice;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DecimalFormat idFormat = new DecimalFormat();
		idFormat.setParseIntegerOnly(true);

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
		tfPrice.setTextFormatter(new TextFormatter<>(c -> {
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = priceFormat.parse(c.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else {
				return c;
			}
		}));

		Bindings.bindBidirectional(tfId.textProperty(), model.idProperty(),
				new StringToNumberConverter<Long>(idFormat));
		tfName.textProperty().bindBidirectional(model.nameProperty());
		Bindings.bindBidirectional(tfPrice.textProperty(), model.priceProperty(), priceFormat);
	}

}
