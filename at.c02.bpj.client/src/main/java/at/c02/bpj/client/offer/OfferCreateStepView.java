package at.c02.bpj.client.offer;

import java.net.URL;
import java.util.ResourceBundle;

import at.c02.bpj.client.ui.MultiStepView;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class OfferCreateStepView implements FxmlView<OfferCreateStepViewModel>, Initializable {

	@InjectViewModel
	private OfferCreateStepViewModel viewModel;

	@FXML
	private MultiStepView stepContextController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		viewModel.setMultiStepModel(stepContextController.getModel());
	}

}
