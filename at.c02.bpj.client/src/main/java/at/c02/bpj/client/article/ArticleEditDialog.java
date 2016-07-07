package at.c02.bpj.client.article;

import at.c02.bpj.client.api.model.Article;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Dialog zum Erstellen oder Bearbeiten eines Artikels
 */
public class ArticleEditDialog extends Dialog<Article> {

	private ViewTuple<ArticleEditView, ArticleEditViewModel> viewTuple;
	private Boolean correctValues;
	private Article article;

	public ArticleEditDialog() {
		// Laden von ArticleEditView
		viewTuple = FluentViewLoader.fxmlView(ArticleEditView.class).load();
		getDialogPane().setContent(viewTuple.getView());
		// Speichern-Button
		final ButtonType saveType = new ButtonType("Speichern", ButtonData.OK_DONE);
		// Dialog hat Buttons "Speichern" und "Abbrechen"
		getDialogPane().getButtonTypes().addAll(saveType, ButtonType.CANCEL);

		Button saveButton = (Button) getDialogPane().lookupButton(saveType);
		saveButton.setOnAction((event) -> {
			article = viewTuple.getViewModel().getArticle();
			correctValues = (article.getName().length() > 0 && article.getPrice() != 0
					&& article.getCategory() != null);
			if (correctValues) {
				this.correctValues = true;
				close();
			}
		});

		this.setOnCloseRequest(event -> {
			article = viewTuple.getViewModel().getArticle();
			correctValues = (article.getName().length() > 0 && article.getPrice() != 0
					&& article.getCategory() != null);

			if (correctValues == false) {
				event.consume();
			}

		});

		setResultConverter(buttonType -> {
			if (saveType.equals(buttonType)) {
				// Nur wenn Speichern gedrückt wurde, wird der Artikel
				// zurückgeliefert
				article = viewTuple.getViewModel().getArticle();
				correctValues = (article.getName().length() > 0 && article.getPrice() != 0
						&& article.getCategory() != null);

				if (correctValues == false) {
					Alert noInputAlert = new Alert(AlertType.WARNING);
					noInputAlert.setHeaderText("Eingabe fehlerhaft");
					noInputAlert.setContentText("Bitte Pflichtfelder ausfüllen!");
					noInputAlert.showAndWait();

				} else {
					return article;
				}
			}
			// sonst NULL
			return null;
		});
	}

	/**
	 * Setzt den zu bearbeitenden Artikel
	 * 
	 * @param article
	 */
	public void setArticle(Article article) {
		viewTuple.getViewModel().editArticle(article);
	}

}
