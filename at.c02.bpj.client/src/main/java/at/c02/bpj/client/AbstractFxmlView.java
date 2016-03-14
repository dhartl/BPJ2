package at.c02.bpj.client;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewModel;
import javafx.fxml.Initializable;

public abstract class AbstractFxmlView<T extends ViewModel> implements FxmlView<T>, Initializable {

	@InjectViewModel
	private T model;

	public T getModel() {
		return model;
	}

}
