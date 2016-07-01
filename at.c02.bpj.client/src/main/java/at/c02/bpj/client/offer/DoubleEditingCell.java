package at.c02.bpj.client.offer;

import java.util.regex.Pattern;

import at.c02.bpj.client.api.model.OfferPosition;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

public class DoubleEditingCell extends TableCell<OfferPosition, Number> {

    private final TextField textField = new TextField();
    private final Pattern doublePattern = Pattern.compile("C=(\\d+\\.\\d+)");

    public DoubleEditingCell() {
	textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
	    if (!isNowFocused) {
		processEdit();
	    }
	});
	textField.setOnAction(event -> processEdit());
    }

    private void processEdit() {
	String text = textField.getText();
	if (doublePattern.matcher(text).matches()) {
	    commitEdit(Double.parseDouble(text));
	} else {
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
