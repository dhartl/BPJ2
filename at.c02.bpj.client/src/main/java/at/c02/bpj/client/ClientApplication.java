package at.c02.bpj.client;

import at.c02.bpj.client.api.Api;
import at.c02.bpj.client.article.ArticleView;
import at.c02.bpj.client.article.ArticleViewModel;
import at.c02.bpj.client.service.Services;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewTuple;
import eu.lestard.easydi.EasyDI;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApplication extends Application {

	public static void main(String... args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		setupDependencyInjection();

		stage.setTitle("BPJ2 Application");
		// ViewTuple<HelloWorldView, HelloWorldViewModel> viewTuple =
		// FluentViewLoader.fxmlView(HelloWorldView.class)
		// .load();
		ViewTuple<ArticleView, ArticleViewModel> viewTuple = FluentViewLoader.fxmlView(ArticleView.class).load();

		Parent root = viewTuple.getView();
		stage.setScene(new Scene(root));
		stage.show();
	}

	private void setupDependencyInjection() {
		EasyDI context = new EasyDI();
		Api.initialize(context);
		Services.initialize(context);
		MvvmFX.setCustomDependencyInjector(context::getInstance);
	}
}
