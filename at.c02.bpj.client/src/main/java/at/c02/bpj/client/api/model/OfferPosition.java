package at.c02.bpj.client.api.model;

import java.text.DecimalFormat;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class OfferPosition {
	
	private ObjectProperty<Long> offerPositionId = new SimpleObjectProperty<>();
	private ObjectProperty<Offer> offer = new SimpleObjectProperty<>();
	private ObjectProperty<Long> posNr = new SimpleObjectProperty<>();
	private ObjectProperty<DecimalFormat> price = new SimpleObjectProperty<>();
	private ObjectProperty<Long> amount = new SimpleObjectProperty<>();
	private ObjectProperty<Article> article = new SimpleObjectProperty<>();
	
	public OfferPosition() {
		
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
	

	public final ObjectProperty<Offer> offerProperty() {
		return this.offer;
	}
	

	public final Offer getOffer() {
		return this.offerProperty().get();
	}
	

	public final void setOffer(final Offer offer) {
		this.offerProperty().set(offer);
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
	

	public final ObjectProperty<DecimalFormat> priceProperty() {
		return this.price;
	}
	

	public final DecimalFormat getPrice() {
		return this.priceProperty().get();
	}
	

	public final void setPrice(final DecimalFormat price) {
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
	

	public final void setArticleId(final Article article) {
		this.articleProperty().set(article);
	}

	@Override
	public String toString() {
		return "OfferPosition [offerPositionId=" + offerPositionId + ", offerId=" + offer + ", posNr=" + posNr
				+ ", price=" + price + ", amount=" + amount + ", articleId=" + article + "]";
	}
	


}