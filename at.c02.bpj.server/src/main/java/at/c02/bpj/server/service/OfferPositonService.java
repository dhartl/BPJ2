package at.c02.bpj.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import at.c02.bpj.server.entity.OfferPosition;
import at.c02.bpj.server.repository.OfferPositionRepository;

/**
 * Business-Logik für den Zugriff auf OfferPositions
 */

public class OfferPositonService {

    private OfferPositionRepository offerPositionRepository;

    // Spring managed diese Klasse. @Autowired-Felder werden automatisch befüllt
    @Autowired
    public void setOfferPositionRepository(OfferPositionRepository offerPositionRepository) {
	this.offerPositionRepository = offerPositionRepository;
    }

    /**
     * findet alle OfferPositions
     * 
     * @return Liste aller OfferPositions
     */
    public List<OfferPosition> getAllOfferPositions() {
	return offerPositionRepository.findAll();
    }

    /**
     * löscht die OfferPosition mit der Id offerPositionID
     * 
     * @param offerPositionID
     */
    public void deleteOfferPosition(long offerPositionID) {
	offerPositionRepository.delete(offerPositionID);
    }

    /**
     * erstellt oder aktualisiert die OfferPosition. Ob eine OfferPosition
     * bereits existiert wird an der OfferPositionID bestimmt
     * 
     * @param offerPosition
     * @return die gespeicherte OfferPosition
     */
    public OfferPosition createOrUpdateArticle(OfferPosition offerPosition) {
	return offerPositionRepository.save(offerPosition);
    }
}
