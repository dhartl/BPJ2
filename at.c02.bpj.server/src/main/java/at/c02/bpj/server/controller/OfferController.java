package at.c02.bpj.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import at.c02.bpj.server.entity.Offer;
import at.c02.bpj.server.service.OfferService;

@Controller // sehr wichtig - Sagt Spring, dass die Klasse erzeugt werden muss
@RequestMapping(path = "offer")
public class OfferController {

	private OfferService offerService;

	@Autowired
	// sagt Spring, dass diese Property automatisch beim Initialisieren des
	// Controllers befüllt werden soll
	public void setOfferService(OfferService offerService) {
		this.offerService = offerService;
	}

	// gibt einen Basispfad für alle Controllermethoden an.
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Offer> getAllOffers() {
		List<Offer> offers = offerService.getAllOffers();
		return offers;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Offer createOrUpdateOffer(@RequestBody Offer offer) {
		Offer responseoffer = offerService.createOrUpdateOffer(offer);
		return responseoffer;
	}

}
