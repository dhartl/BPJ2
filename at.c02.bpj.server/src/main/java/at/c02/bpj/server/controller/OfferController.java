package at.c02.bpj.server.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import at.c02.bpj.server.entity.Offer;
import at.c02.bpj.server.service.OfferService;

public class OfferController {
	
	
	private OfferService offerService;


	public OfferService getOfferService() {
		return offerService;
	}


	public void setOfferService(OfferService offerService) {
		this.offerService = offerService;
	}


	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Offer> getAllOffers() {
		List<Offer> offer = offerService.getAllOffers();
		return offer;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Offer createOrUpdateOffer(@RequestBody Offer offer) {
		Offer responseOffer = offerService.createOrUpdateOffer(offer);
		return responseOffer;
	}
	

}
