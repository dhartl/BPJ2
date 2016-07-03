package at.c02.bpj.client;

import at.c02.bpj.client.api.Api;
import at.c02.bpj.client.article.ArticleView;
import at.c02.bpj.client.customer.CustomerView;
import at.c02.bpj.client.offer.OfferChooseCustomerView;
import at.c02.bpj.client.offer.management.OfferManagementView;
import at.c02.bpj.client.service.Services;
import at.c02.bpj.client.ui.MainMenuItem;
import at.c02.bpj.client.ui.MainView;
import at.c02.bpj.client.ui.MainViewModel;
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
		Async.setFxInitialized(true);
		setupDependencyInjection();

		// Exceptions, die nicht behandelt werden, werden vom UI-ErrorHandler
		// verarbeitet.
		// Dieser erstellt einen Popup-Dialog mit der Fehlermeldung
		Thread.currentThread().setUncaughtExceptionHandler(new UiErrorHandler());

		stage.setTitle("BPJ2 Application");

		ViewTuple<MainView, MainViewModel> viewTuple = FluentViewLoader.fxmlView(MainView.class).load();
		initializeMenuItems(viewTuple.getViewModel());

		Parent root = viewTuple.getView();
		stage.setScene(new Scene(root));
		stage.show();
	}

	private void initializeMenuItems(MainViewModel mainViewModel) {
		mainViewModel.getMainMenuItems().addAll(new MainMenuItem("Artikel", ArticleView.class),
				new MainMenuItem("Kunden", CustomerView.class), new MainMenuItem("Angebote", OfferManagementView.class),
				new MainMenuItem("Neues Angebot", OfferChooseCustomerView.class));
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
