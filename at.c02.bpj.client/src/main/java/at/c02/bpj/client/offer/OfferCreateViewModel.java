package at.c02.bpj.client.offer;

import java.util.Date;
import java.util.List;

import at.c02.bpj.client.AuthContext;
import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferPosition;
import at.c02.bpj.client.api.model.OfferStatus;
import at.c02.bpj.client.service.ArticleService;
import at.c02.bpj.client.service.OfferService;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Model für {@link OfferCreatView}
 */
public class OfferCreateViewModel implements ViewModel {

	/**
	 * Liste aller Artikel und OfferPositons
	 */
	private ObjectProperty<Double> sumPrice = new SimpleObjectProperty<>();

	private ObservableList<Article> articles = FXCollections.observableArrayList();

	private ObservableList<OfferPosition> offerPositions = FXCollections.observableArrayList();

	private ArticleService articleService;
	private OfferService offerService;
	public int positionNumber;

	@InjectScope
	private OfferScope offerScope;

	public ObjectProperty<Double> sumPriceProperty() {
		return sumPrice;
	}

	// ArticleService wird mittels ConstruktorInjection gesetzt
	public OfferCreateViewModel(ArticleService articleService, OfferService offerService) {
		positionNumber = 0;
		this.articleService = articleService;
		this.offerService = offerService;

	}

	public void initialize() {
		offerScope.offerProperty().addListener((observable, oldValue, newValue) -> {
			unbindOffer(oldValue);
			bindOffer(newValue);
		});
		bindOffer(offerScope.getOffer());
		loadArticles();

	}

	private void bindOffer(Offer newValue) {
		if (newValue != null) {
			sumPrice.bind(Bindings.createObjectBinding(
					() -> newValue.offerPositionsProperty().stream()
							.mapToDouble(pos -> pos.getPrice() * pos.getAmount()).sum(),
					newValue.offerPositionsProperty()));
			Bindings.bindContentBidirectional(offerPositions, newValue.offerPositionsProperty());
		}
	}

	private void unbindOffer(Offer oldValue) {
		if (oldValue != null) {
			sumPrice.unbind();
			Bindings.unbindContentBidirectional(offerPositions, oldValue.offerPositionsProperty());
		}
	}

	public ObservableList<OfferPosition> offerPositionsProperty() {
		return offerPositions;
	}

	/**
	 * Lädt die Artikel
	 */
	public void loadArticles() {
		List<Article> artciles = articleService.getArticles();
		setArticles(artciles);
	}

	public ObservableList<Article> articlesProperty() {
		return articles;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles.setAll(articles);
	}

	/**
	 * Fügt eine neue Position zum Angebot hinzu
	 */
	public void addPositiontoOffer(Article article) {
		positionNumber++;
		OfferPosition newOfferPosition = new OfferPosition();
		newOfferPosition.setArticle(article);
		newOfferPosition.setPrice(article.getPrice());
		newOfferPosition.setPosNr(positionNumber);
		int nmbr = 1;
		newOfferPosition.setAmount(nmbr);

		offerScope.offerProperty().get().getOfferPositions().add(newOfferPosition);
	}

	public void saveOffer() {
		Offer offer = offerScope.offerProperty().get();
		offer.setCreatedDt(new Date());
		offer.setStatus(OfferStatus.CREATED);
		offer.setEmployee(AuthContext.getInstance().getCurrentUser());
		Offer savedOffer = offerService.saveOffer(offer);
		offerScope.setOffer(savedOffer);
	}

}
