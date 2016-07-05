package at.c02.bpj.client.offer;

import java.util.Collections;

import com.google.common.collect.Lists;

import at.c02.bpj.client.article.ArticleView;
import at.c02.bpj.client.customer.CustomerView;
import at.c02.bpj.client.ui.MultiStepNavigator;
import at.c02.bpj.client.ui.MultiStepViewModel;
import at.c02.bpj.client.ui.Step;
import at.c02.bpj.client.ui.StepAction;
import de.saxsys.mvvmfx.ViewModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;

public class OfferCreateStepViewModel implements ViewModel {

	private static final String ARTICLE_VIEW = "articleMgmt";
	private static final String CUSTOMER_VIEW = "custCreate";
	private static final String OFFER_SELECT_ARTICLES = "posSel";
	private static final String OFFER_CHOOSE_CUSTOMER = "custSel";
	private MultiStepNavigator navigator = new MultiStepNavigator();

	public OfferCreateStepViewModel() {
		createSteps();
	}

	public void setMultiStepModel(MultiStepViewModel multiStepViewModel) {
		navigator.setModel(multiStepViewModel);
		navigator.setScopes(Collections.singletonList(new OfferScope()));
		navigator.navigateTo(OFFER_CHOOSE_CUSTOMER);

	}

	private void createSteps() {
		Step customerSelction = new Step(OFFER_CHOOSE_CUSTOMER, OfferChooseCustomerView.class);
		customerSelction.setBreadcrumbs("Angebot erstellen > Kunde auswählen");
		StepAction kundeView = new StepAction("Kunde suchen / neu erstellen", () -> {
			navigator.navigateTo(CUSTOMER_VIEW);
		});
		StepAction posSelView = new StepAction("Weiter", () -> {
			OfferChooseCustomerModel model = getOfferCustomerModel();
			if (model.validateSelectedCustomer()) {
				navigator.navigateTo(OFFER_SELECT_ARTICLES);
			}
		});
		customerSelction.setStepActions(Lists.newArrayList(kundeView, posSelView));
		navigator.addStep(customerSelction);

		Step kundeCreateStep = new Step(CUSTOMER_VIEW, CustomerView.class);
		kundeCreateStep.setBreadcrumbs("Angebot erstellen > Kunde suchen / neu erstellen");
		StepAction custSelView = new StepAction("Zurück", () -> {
			navigator.navigateTo(OFFER_CHOOSE_CUSTOMER);
		});
		kundeCreateStep.setStepActions(Lists.newArrayList(custSelView));
		navigator.addStep(kundeCreateStep);

		Step posSelction = new Step(OFFER_SELECT_ARTICLES, OfferCreateView.class);
		posSelction.setBreadcrumbs("Angebot erstellen > Positionen hinzufügen");
		StepAction createOffer = new StepAction("Angebot erstellen", () -> {
			getOfferPosModel().saveOffer();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Angebot gespeichert");
			alert.setContentText("Ihr Angebot wurde erstellt!");
			alert.showAndWait();
		});
		StepAction articleView = new StepAction("Artikel suchen / neu erstellen", () -> {
			navigator.navigateTo(ARTICLE_VIEW);
		});
		posSelction.setStepActions(Lists.newArrayList(articleView, custSelView, createOffer));
		navigator.addStep(posSelction);

		Step articleMgmt = new Step(ARTICLE_VIEW, ArticleView.class);
		articleMgmt.setBreadcrumbs("Angebot erstellen > Artikel suchen / neu erstellen");
		StepAction backToOfferView = new StepAction("Zurück", () -> {
			navigator.navigateTo(OFFER_SELECT_ARTICLES);
		});
		articleMgmt.setStepActions(Lists.newArrayList(backToOfferView));
		navigator.addStep(articleMgmt);
	}

	private OfferChooseCustomerModel getOfferCustomerModel() {
		OfferChooseCustomerModel model = (OfferChooseCustomerModel) navigator.getViewTuple().getViewModel();
		return model;
	}

	private OfferCreateViewModel getOfferPosModel() {
		OfferCreateViewModel model = (OfferCreateViewModel) navigator.getViewTuple().getViewModel();
		return model;
	}
}
