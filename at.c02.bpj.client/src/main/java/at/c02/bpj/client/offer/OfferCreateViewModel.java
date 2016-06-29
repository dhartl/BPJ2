package at.c02.bpj.client.offer;

import java.util.List;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferPosition;
import at.c02.bpj.client.service.ArticleService;
import at.c02.bpj.client.service.OfferService;
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
    private ObjectProperty<Offer> offer = new SimpleObjectProperty<>(new Offer());

    private ObservableList<Article> articles = FXCollections.observableArrayList();

    private ObservableList<OfferPosition> offerPositions = FXCollections.observableArrayList();

    private ArticleService articleService;
    private OfferService offerService;
    private long positionNumber;

    public ObjectProperty<Offer> offerProperty() {
	return offer;
    }

    // ArticleService wird mittels ConstruktorInjection gesetzt
    public OfferCreateViewModel(ArticleService articleService, OfferService offerService) {
	positionNumber = 0;
	this.articleService = articleService;
	this.offerService = offerService;
	Bindings.bindContent(offerPositionsProperty(), offer.get().offerPositionsProperty());
	loadPositions();
	loadArticles();
    }

    /**
     * Lädt die Positionen
     */
    public void loadPositions() {
	List<OfferPosition> offerPositions = offer.get().getOfferPositions();
	setOfferPositions(offerPositions);
    }

    public ObservableList<OfferPosition> offerPositionsProperty() {
	return offerPositions;
    }

    public List<OfferPosition> getOfferPositions() {
	return offerPositions;
    }

    public void setOfferPositions(List<OfferPosition> offerPositions) {
	this.offerPositions.setAll(offerPositions);
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
	long nmbr = 1;
	newOfferPosition.setAmount(nmbr);

	offerPositions.add(newOfferPosition);
    }

    public void saveOffer() {
	offerService.saveOffer(offer.get());
    }
}
