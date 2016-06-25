package at.c02.bpj.client.service;

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

    public Offer saveOffer(Offer offer) throws ServiceException {
	return Services.executeCall(offerApi.saveOffer(offer));
    }

}
