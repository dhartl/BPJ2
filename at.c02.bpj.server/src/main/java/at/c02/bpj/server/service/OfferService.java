package at.c02.bpj.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.c02.bpj.server.entity.Offer;
import at.c02.bpj.server.repository.OfferRepository;

@Service
public class OfferService {

	private OfferRepository offerRepository;

	public OfferRepository getOfferRepository() {
		return offerRepository;
	}

	@Autowired
	public void setOfferRepository(OfferRepository offerRepository) {
		this.offerRepository = offerRepository;
	}

	/**
	 * findet alle Angebote
	 * 
	 * @return Liste aller Angebote
	 */
	public List<Offer> getAllOffers() {
		return offerRepository.findAllFetching();
	}

	/**
	 * erstellt oder aktualisiert Angebot.
	 * 
	 * @param offer
	 */
	public Offer createOrUpdateOffer(Offer offer) {
		offer.getOfferPositions().forEach(offerPosition -> offerPosition.setOffer(offer));
		return offerRepository.save(offer);
	}

}
