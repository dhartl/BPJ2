package at.c02.bpj.client.api.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class OfferPosition {

    private ObjectProperty<Long> offerPositionId = new SimpleObjectProperty<>();
    private ObjectProperty<Long> posNr = new SimpleObjectProperty<>();
    private ObjectProperty<Double> price = new SimpleObjectProperty<>();
    private ObjectProperty<Long> amount = new SimpleObjectProperty<>();
    private ObjectProperty<Article> article = new SimpleObjectProperty<>();
    private ObjectProperty<Offer> offerId = new SimpleObjectProperty<>();

    public OfferPosition() {

    }

    public final ObjectProperty<Offer> offerIdProperty() {
	return this.offerId;
    }

    public final Offer getOfferId() {
	return this.offerIdProperty().get();
    }

    public final void setOfferId(final Offer offerId) {
	this.offerIdProperty().set(offerId);
    }

    public final ObjectProperty<Long> offerPositionIdProperty() {
	return this.offerPositionId;
    }

    public final Long getOfferPositionId() {
	return this.offerPositionIdProperty().get();
    }

    public final void setOfferPositionId(final Long offerPositionId) {
	this.offerPositionIdProperty().set(offerPositionId);
    }

    public final ObjectProperty<Long> posNrProperty() {
	return this.posNr;
    }

    public final Long getPosNr() {
	return this.posNrProperty().get();
    }

    public final void setPosNr(final Long posNr) {
	this.posNrProperty().set(posNr);
    }

    public final ObjectProperty<Double> priceProperty() {
	return this.price;
    }

    public final Double getPrice() {
	return this.priceProperty().get();
    }

    public final void setPrice(final Double price) {
	this.priceProperty().set(price);
    }

    public final ObjectProperty<Long> amountProperty() {
	return this.amount;
    }

    public final Long getAmount() {
	return this.amountProperty().get();
    }

    public final void setAmount(final Long amount) {
	this.amountProperty().set(amount);
    }

    public final ObjectProperty<Article> articleProperty() {
	return this.article;
    }

    public final Article getArticle() {
	return this.articleProperty().get();
    }

    public final void setArticle(final Article article) {
	this.articleProperty().set(article);
    }

    @Override
    public String toString() {
	return "OfferPosition [offerPositionId=" + offerPositionId + ", posNr=" + posNr + ", price=" + price
		+ ", amount=" + amount + ", articleId=" + article + "]";
    }

}
