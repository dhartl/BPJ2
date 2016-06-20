package at.c02.bpj.client.service;

import java.io.File;
import java.util.List;

import at.c02.bpj.client.api.OfferApi;
import at.c02.bpj.client.api.model.Offer;

public class OfferService {
	
	public OfferApi offerApi;

	
	public OfferService(OfferApi offerApi) {
		super();
		this.offerApi = offerApi;
	} 
	
	public List<Offer> getOffer() throws ServiceException {
		return Services.executeCall(offerApi.getOffer());
	}
	
	
	public Offer saveArticle(Offer offer) throws ServiceException {
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
