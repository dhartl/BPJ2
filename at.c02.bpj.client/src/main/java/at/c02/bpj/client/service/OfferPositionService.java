package at.c02.bpj.client.service;

import java.util.List;

import at.c02.bpj.client.api.OfferPositionApi;
import at.c02.bpj.client.api.model.OfferPosition;

public class OfferPositionService {

    public OfferPositionApi offerPositionApi;

    public OfferPositionService(OfferPositionApi offerPositionApi) {
	super();
	this.offerPositionApi = offerPositionApi;
    }

    public List<OfferPosition> getOfferPosition() throws ServiceException {
	return Services.executeCall(offerPositionApi.getOfferPosition());
    }

    public OfferPosition saveOfferPosition(OfferPosition offerPosition) throws ServiceException {
	return Services.executeCall(offerPositionApi.saveOfferPosition(offerPosition));
    }

}
