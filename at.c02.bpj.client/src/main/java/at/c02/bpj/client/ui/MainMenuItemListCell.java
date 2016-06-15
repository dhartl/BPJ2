package at.c02.bpj.client.ui;

import javafx.scene.control.ListCell;
import javafx.scene.text.Text;

public class MainMenuItemListCell extends ListCell<MainMenuItem> {

	@Override
	protected void updateItem(MainMenuItem item, boolean empty) {
		super.updateItem(item, empty);
		if (item == null || empty) {
			setGraphic(null);
		} else {
			Text text = new Text();
			text.textProperty().bind(item.titleProperty());
			setGraphic(text);
		}
	}
}
