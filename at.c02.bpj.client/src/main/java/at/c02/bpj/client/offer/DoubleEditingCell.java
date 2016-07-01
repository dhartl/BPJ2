package at.c02.bpj.client.offer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import at.c02.bpj.client.api.model.OfferPosition;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

public class DoubleEditingCell extends TableCell<OfferPosition, Number> {

    private final TextField textField = new TextField();
	NumberFormat doubleFormat = createFormat();

    public DoubleEditingCell() {
	textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
	    if (!isNowFocused) {
		processEdit();
	    }
	});
	textField.setOnAction(event -> processEdit());
    }

	private NumberFormat createFormat() {
		DecimalFormat decimalFormat = new DecimalFormat();
		return decimalFormat;
	}

	private void processEdit() {
	String text = textField.getText();
		try {
			commitEdit(doubleFormat.parse(text));
		} catch (ParseException ex) {
			cancelEdit();
	}
    }

    @Override
    public void updateItem(Number value, boolean empty) {
	super.updateItem(value, empty);
	if (empty) {
	    setText(null);
	    setGraphic(null);
	} else if (isEditing()) {
	    setText(null);
	    textField.setText(value.toString());
	    setGraphic(textField);
	} else {
	    setText(value.toString());
	    setGraphic(null);
	}
    }

    @Override
    public void startEdit() {
	super.startEdit();
	Number value = getItem();
	if (value != null) {
	    textField.setText(value.toString());
	    setGraphic(textField);
	    setText(null);
	}
    }

    @Override
    public void cancelEdit() {
	super.cancelEdit();
	setText(getItem().toString());
	setGraphic(null);
    }

    @Override
    public void commitEdit(Number value) {
	super.commitEdit(value);
	((OfferPosition) this.getTableRow().getItem()).priceProperty().set(value.doubleValue());
    }
}
