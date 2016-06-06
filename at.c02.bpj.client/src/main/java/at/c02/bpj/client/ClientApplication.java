package at.c02.bpj.client;

import at.c02.bpj.client.api.Api;
import at.c02.bpj.client.article.ArticleView;
import at.c02.bpj.client.article.ArticleViewModel;
import at.c02.bpj.client.offer.management.OfferManagementView;
import at.c02.bpj.client.offer.management.OfferManagementViewModel;
import at.c02.bpj.client.service.Services;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewTuple;
import eu.lestard.easydi.EasyDI;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hauptklasse für die Client-Applikation
 *
 */
public class ClientApplication extends Application {

	public static void main(String... args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		setupDependencyInjection();

		// Exceptions, die nicht behandelt werden, werden vom UI-ErrorHandler
		// verarbeitet.
		// Dieser erstellt einen Popup-Dialog mit der Fehlermeldung
		Thread.currentThread().setUncaughtExceptionHandler(new UiErrorHandler());

		stage.setTitle("BPJ2 Application");

		/*
		ViewTuple<ArticleView, ArticleViewModel> viewTuple = FluentViewLoader.fxmlView(ArticleView.class).load();
		 */
		
		ViewTuple<OfferManagementView, OfferManagementViewModel> viewTuple = 
				FluentViewLoader.fxmlView(OfferManagementView.class).load();
		
		Parent root = viewTuple.getView();
		stage.setScene(new Scene(root));
		stage.show();
		
	}

	/**
	 * Initialisierung des Contexts für die Dependency-Injection
	 */
	private void setupDependencyInjection() {
		EasyDI context = new EasyDI();
		// legt die Klassen für den API-Zugriff in den Context
		Api.initialize(context);
		// legt die Klassen für die Services in den Context
		Services.initialize(context);
		MvvmFX.setCustomDependencyInjector(context::getInstance);
	}
}
