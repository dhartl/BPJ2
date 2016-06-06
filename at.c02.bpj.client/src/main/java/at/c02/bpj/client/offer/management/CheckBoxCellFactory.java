package at.c02.bpj.client.offer.management;

import at.c02.bpj.client.api.model.Offer;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

public class CheckBoxCellFactory implements Callback {

	// http://stackoverflow.com/questions/20879242/get-checkbox-value-in-a-table-in-javafx
	
	@Override
	public TableCell call(Object param) {
		CheckBoxTableCell<Offer,Boolean> checkBoxCell = new CheckBoxTableCell();
        return checkBoxCell;
	}


}
