package at.c02.bpj.client.offer;

import at.c02.bpj.client.api.model.Offer;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class OfferScope implements Scope {

	private ObjectProperty<Offer> offer = new SimpleObjectProperty<>(new Offer());

	public OfferScope() {
	}

	public final ObjectProperty<Offer> offerProperty() {
		return this.offer;
	}

	public final at.c02.bpj.client.api.model.Offer getOffer() {
		return this.offerProperty().get();
	}

	public final void setOffer(final at.c02.bpj.client.api.model.Offer offer) {
		this.offerProperty().set(offer);
	}

}
