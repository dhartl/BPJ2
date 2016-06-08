package at.c02.bpj.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import at.c02.bpj.server.entity.OfferPosition;
import at.c02.bpj.server.service.OfferPositionService;


@Controller // sehr wichtig - Sagt Spring, dass die Klasse erzeugt werden muss
@RequestMapping(path = "offerPosition")
public class OfferPositionController {
	
	private OfferPositionService offerPositionService;

	@Autowired
	// sagt Spring, dass diese Property automatisch beim Initialisieren des
	// Controllers befüllt werden soll
	public void setOfferPositionService(OfferPositionService offerPositionService) {
		this.offerPositionService = offerPositionService;
	}
	
// gibt einen Basispfad für alle Controllermethoden an.
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<OfferPosition> getAllOffersPositions() {
		List<OfferPosition> offerPositions = offerPositionService.getAllOfferPositions();
		return offerPositions;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public OfferPosition createOrUpdateOfferPosition(@RequestBody OfferPosition offerPosition) {
		OfferPosition responseofferPosition = offerPositionService.createOrUpdateOfferPosition(offerPosition);
		return responseofferPosition;
	}

}
