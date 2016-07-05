package at.c02.bpj.client.offer;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferPosition;
import at.c02.bpj.client.service.ArticleService;
import at.c02.bpj.client.service.OfferService;
import at.c02.bpj.client.test.MvvmFxGuiTest;
import at.c02.bpj.client.test.TestData;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.Scope;
import de.saxsys.mvvmfx.ViewModel;
import eu.lestard.easydi.EasyDI;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

@RunWith(MockitoJUnitRunner.class)
public class OfferCreateViewTest extends MvvmFxGuiTest {

	@Mock
	private OfferService offerService;

	@Mock
	private ArticleService articleService;

	private Offer offer1;
	private Offer offer2;
	private Employee employee1;
	private Customer customer1;

	@Override
	public Class<? extends FxmlView<? extends ViewModel>> getViewClass() {
		return OfferCreateView.class;
	}

	@Override
	public List<Scope> getViewScopes() {
		return Lists.newArrayList(new OfferScope());
	}

	@Override
	public void setupContext(EasyDI context) {
		context.bindInstance(OfferService.class, offerService);
		context.bindInstance(ArticleService.class, articleService);

		Mockito.when(articleService.getArticles()).thenReturn(Lists.newArrayList(TestData.article1()));

		Mockito.when(offerService.saveOffer(offer1)).thenReturn(offer1);
		stage.setWidth(1000);
		stage.setHeight(1000);
	}

	@Test
	public void testInitialize() {
		TableView<OfferPosition> tblOfferPositions = find("#tblOfferPositions");

		TableView<Article> tblArticles = find("#tblArticles");

	}

	// #007 Der User wählt in der Artikelansicht einen Artikel aus und fügt ihn
	// zum Angebot hinzu.
	@Test
	public void testAddArticleToOffer() {
		TableView<OfferPosition> tblOfferPositions = find("#tblOfferPositions");

		TableView<Article> tblArticles = find("#tblArticles");
		click("Artikel1", MouseButton.SECONDARY).click("Zum Angebot hinzufügen");

		Assert.assertEquals(1, tblOfferPositions.getItems().size());
	}

	// #008 Der User möchte das Angebot abschließen und klickt auf Speichern.
	// Es wurde jedoch einer Position die Menge 100 zugeordnet .
	@Test
	public void testSaveOfferAmountError() {
		TableView<OfferPosition> tblOfferPositions = find("#tblOfferPositions");

		TableView<Article> tblArticles = find("#tblArticles");
		click("Artikel1", MouseButton.SECONDARY).click("Zum Angebot hinzufügen");
		// Eingabe von 100 bei Amount?
		move(find("Menge")).moveBy(-10, 25).doubleClick(MouseButton.PRIMARY).doubleClick().type("100")
				.press(KeyCode.ENTER);
		// Hier Button drücken
		OfferCreateViewModel viewModel = (OfferCreateViewModel) getViewTuple().getViewModel();
		viewModel.saveOffer();
		// Warnung muss aufscheinen

	}

	// #009 Der User möchte das Angebot abschließen und klickt auf Speichern.
	// Es wurde jedoch einem Artikel ein negativer Preis (Preis € -100)
	// zugeordnet.
	@Test
	public void testSaveOfferPriceError() {
		TableView<OfferPosition> tblOfferPositions = find("#tblOfferPositions");

		TableView<Article> tblArticles = find("#tblArticles");
		click("#tblArticles").click(MouseButton.SECONDARY).click(MouseButton.PRIMARY);
		// Eingabe von -100 bei Price?

		// Hier Button drücken
		Button btnSaveAndClose = find("#btnSaveAndClose");
		click("#btnSaveAndClose");
		// Warnung muss aufscheinen

	}

	// #010 Der User möchte das Angebot abschließen und klickt auf Speichern. Es
	// wurde jedoch bisher kein Artikel (keine Position) dem Angebot
	// hinzugefügt.

	@Test
	public void testSaveOfferOfferPositionError() {
		TableView<OfferPosition> tblOfferPositions = find("#tblOfferPositions");

		TableView<Article> tblArticles = find("#tblArticles");

		// Hier Button drücken
		Button btnSaveAndClose = find("#btnSaveAndClose");
		click("#btnSaveAndClose");
		// Warnung muss aufscheinen

	}

	// #011 Der User tätigt folgende Eingabe: Artikel= Prüfstand xy; Preis= €
	// 10.000,--, Menge= 1 und klickt folgend auf „Angebot speichern“.
	@Test
	public void testSaveOfferCompleted() {
		TableView<OfferPosition> tblOfferPositions = find("#tblOfferPositions");

		TableView<Article> tblArticles = find("#tblArticles");

		// Hier Button drücken
		Button btnSaveAndClose = find("#btnSaveAndClose");
		click("#btnSaveAndClose");

		// Assert.assertEquals(getViewTuple().getViewModel().???,
		// btnSaveAndClose.getOnAction());
	}

	// #012 Der User schließt die Angebotserstellungsmaske und möchte somit die
	// aktuelle Erstellung des Angebots verwerfen. @Test
	public void testCloseOfferCreateView() {
		TableView<OfferPosition> tblOfferPositions = find("#tblOfferPositions");

		TableView<Article> tblArticles = find("#tblArticles");

		this.closeCurrentWindow();

	}

}
