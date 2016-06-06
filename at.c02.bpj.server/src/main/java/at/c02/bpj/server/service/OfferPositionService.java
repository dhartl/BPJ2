package at.c02.bpj.server.service;

import java.util.List;

import at.c02.bpj.server.entity.OfferPosition;
import at.c02.bpj.server.repository.OfferPositionRepository;


public class OfferPositionService {
	
private OfferPositionRepository offerPositionRepository;

	
	public OfferPositionRepository getOfferPositionRepository() {
	return offerPositionRepository;
	}

	public void setOfferPositionRepository(OfferPositionRepository offerPositionRepository) {
	this.offerPositionRepository = offerPositionRepository;
	}

	/**
	 * findet alle Angebotspositionen
	 * @return Liste aller Positionen
	 */
	public List<OfferPosition> getAllOfferPositions() {
		return offerPositionRepository.findAll();
	}

	/**
	 * erstellt oder aktualisiert Angebotspositionen. 
	 * @param offerPosition
	 */
	public OfferPosition createOrUpdateOfferPosition(OfferPosition offerPosition) {
		return offerPositionRepository.save(offerPosition);
	}
	

}
