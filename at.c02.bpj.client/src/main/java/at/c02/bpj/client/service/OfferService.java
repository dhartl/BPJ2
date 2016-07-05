package at.c02.bpj.client.service;

import java.io.File;
import java.util.List;

import at.c02.bpj.client.api.OfferApi;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferPosition;

public class OfferService {

	public OfferApi offerApi;

	public OfferService(OfferApi offerApi) {
		super();
		this.offerApi = offerApi;
	}

	public List<Offer> getOffer() throws ServiceException {
		return Services.executeCall(offerApi.getOffer());
	}

	public void validateOffer(Offer offer) {
		if (offer.getOfferPositions().isEmpty()) {
			throw new ServiceException("Es muss mindestens eine Position zum Angebot hinzugefügt werden!");
		}
		for (OfferPosition op : offer.offerPositionsProperty()) {
			if (op.amountProperty().get() > 99 || op.amountProperty().get() < 1) {
				throw new ServiceException(
						"Die Menge der Position " + op.getPosNr() + " muss zwischen 1 und 99 liegen!");
			}
			if (op.priceProperty().get() > 1000000 || op.priceProperty().get() < 0) {
				throw new ServiceException(
						"Der Preis der Position " + op.getPosNr() + " muss zwischen 0 und 1000000 liegen!");
			}
		}
	}

	public Offer saveOffer(Offer offer) throws ServiceException {
		validateOffer(offer);
		return Services.executeCall(offerApi.saveOffer(offer));
	}

	// hier wird methode offer hinzugefügt
	public void exportOffer(File selectedFile, Offer offer) {
		// hier wird dokument angelegt
		// itext
		// properties für text: offer.getcustomer, offer.get... stehen im
		// client.api.model

		// dateinamen automatisch befüllen im offermanagementview, wo dialog
		// geöffnet wird

	}
}
