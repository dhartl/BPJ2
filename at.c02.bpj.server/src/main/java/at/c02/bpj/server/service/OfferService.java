package at.c02.bpj.server.service;

import java.util.List;

import at.c02.bpj.server.entity.Offer;
import at.c02.bpj.server.repository.OfferRepository;


public class OfferService {

	
private OfferRepository offerRepository;

	
	public OfferRepository getOfferRepository() {
	return offerRepository;
	}

	public void setOfferRepository(OfferRepository offerRepository) {
	this.offerRepository = offerRepository;
	}


	/**
	 * findet alle Angebote
	 * @return Liste aller Angebote
	 */
	public List<Offer> getAllOffers() {
		return offerRepository.findAll();
	}

	/**
	 * erstellt oder aktualisiert Angebot. 
	 * @param offer
	 */
	public Offer createOrUpdateOffer(Offer offer) {
		return offerRepository.save(offer);
	}
	
	
}
