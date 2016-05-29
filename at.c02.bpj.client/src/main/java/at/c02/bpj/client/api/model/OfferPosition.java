package at.c02.bpj.client.api.model;

import java.text.DecimalFormat;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class OfferPosition {
	
	private ObjectProperty<Long> offerPositionId = new SimpleObjectProperty<>();
	private ObjectProperty<Long> offerId = new SimpleObjectProperty<>();
	private ObjectProperty<Long> posNr = new SimpleObjectProperty<>();
	private ObjectProperty<DecimalFormat> price = new SimpleObjectProperty<>();
	private ObjectProperty<Long> amount = new SimpleObjectProperty<>();
	private ObjectProperty<Long> articleId = new SimpleObjectProperty<>();
	
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
	

	public final ObjectProperty<Long> offerIdProperty() {
		return this.offerId;
	}
	

	public final Long getOfferId() {
		return this.offerIdProperty().get();
	}
	

	public final void setOfferId(final Long offerId) {
		this.offerIdProperty().set(offerId);
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
	

	public final ObjectProperty<Long> articleIdProperty() {
		return this.articleId;
	}
	

	public final Long getArticleId() {
		return this.articleIdProperty().get();
	}
	

	public final void setArticleId(final Long articleId) {
		this.articleIdProperty().set(articleId);
	}

	@Override
	public String toString() {
		return "OfferPosition [offerPositionId=" + offerPositionId + ", offerId=" + offerId + ", posNr=" + posNr
				+ ", price=" + price + ", amount=" + amount + ", articleId=" + articleId + "]";
	}
	


}
