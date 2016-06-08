package at.c02.bpj.client.offer.management;

import at.c02.bpj.client.api.model.Offer;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;



// noch nicht in Gebrauch -> bei use Case Verbindung erst (z.b. Dashboard -- klick auf Angebotsmanagement)
public class OfferManagementDialog extends Dialog<Offer> {

	
	private ViewTuple<OfferManagementView, OfferManagementViewModel> viewTuple;

	
	public OfferManagementDialog() {
		
		
		// Laden von ArticleEditView
		viewTuple = FluentViewLoader.fxmlView(OfferManagementView.class).load();
		getDialogPane().setContent(viewTuple.getView());

	
		
	}
	
}
