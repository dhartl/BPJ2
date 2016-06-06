package at.c02.bpj.server.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import at.c02.bpj.server.entity.OfferPosition;
import at.c02.bpj.server.service.OfferPositionService;

public class OfferPositionController {

	private OfferPositionService offerPositionService;

	
	public OfferPositionService getOfferPositionService() {
		return offerPositionService;
	}

	public void setOfferPositionService(OfferPositionService offerPositionService) {
		this.offerPositionService = offerPositionService;
	}


	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<OfferPosition> getAllOfferPositions() {
		List<OfferPosition> offerPosition = offerPositionService.getAllOfferPositions();
		return offerPosition;
	}


	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public OfferPosition createOrUpdateOfferPosition(@RequestBody OfferPosition offerPosition) {
		OfferPosition responseOfferPosition = offerPositionService.createOrUpdateOfferPosition(offerPosition);
		return responseOfferPosition;
	}
	
}
