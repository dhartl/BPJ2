package at.c02.bpj.client.offer;

import java.util.List;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferPosition;
import at.c02.bpj.client.service.ArticleService;
import at.c02.bpj.client.service.OfferService;
import de.saxsys.mvvmfx.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Model für {@link OfferCreatView}
 */
public class OfferCreateViewModel implements ViewModel {

    /**
     * Liste aller Artikel und OfferPositons
     */

    private ObservableList<Article> articles = FXCollections.observableArrayList();
    private ObservableList<OfferPosition> offerPositions = FXCollections.observableArrayList();

    private ArticleService articleService;
    private OfferService offerService;
    private Offer offer;

    // ArticleService wird mittels ConstruktorInjection gesetzt
    public OfferCreateViewModel(ArticleService articleService, OfferService offerService, Offer param_offer) {
	this.offer = param_offer;
	this.articleService = articleService;
	this.offerService = offerService;

	loadPositions();
	loadArticles();
    }

    /**
     * Lädt die Positionen
     */
    public void loadPositions() {
	List<OfferPosition> offerPositions = offer.getOfferPositions();
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
     * TODO: Lädt die Kundendaten
     */

    /**
     * Fügt eine neue Position zum Angebot hinzu
     */
    public void addPositiontoOffer(Article article) {
	OfferPosition newOfferPosition = new OfferPosition();
	newOfferPosition.setArticleId(article);
	newOfferPosition.setOffer(this.offer);

	offerPositions.add(newOfferPosition);
    }

}
