package at.c02.bpj.client.article;

import com.google.common.base.Strings;

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
	private boolean correctValues = false;

	public ArticleEditDialog() {
		// Laden von ArticleEditView
		viewTuple = FluentViewLoader.fxmlView(ArticleEditView.class).load();
		getDialogPane().setContent(viewTuple.getView());
		// Speichern-Button
		final ButtonType saveType = new ButtonType("Speichern", ButtonData.OK_DONE);
		// Dialog hat Buttons "Speichern" und "Abbrechen"
		getDialogPane().getButtonTypes().addAll(saveType);

		Button saveButton = (Button) getDialogPane().lookupButton(saveType);
		saveButton.setOnAction((event) -> {
			correctValues = validateArticle(viewTuple.getViewModel().getArticle());
			if (correctValues) {
				close();
			} else {
				Alert noInputAlert = new Alert(AlertType.WARNING);
				noInputAlert.setHeaderText("Eingabe fehlerhaft");
				noInputAlert.setContentText("Bitte Pflichtfelder ausfüllen!");
				noInputAlert.showAndWait();
			}
		});

		this.setOnCloseRequest(event -> {
			if (!correctValues) {
				event.consume();
			}

		});

		setResultConverter(buttonType -> {
			if (saveType.equals(buttonType) && validateArticle(viewTuple.getViewModel().getArticle())) {
				// Nur wenn Speichern gedrückt wurde, wird der Artikel
				// zurückgeliefert
				return viewTuple.getViewModel().getArticle();
			}
			// sonst NULL
			return null;
		});
	}

	private boolean validateArticle(Article article) {
		return article != null && !Strings.isNullOrEmpty(article.getName()) && article.getPrice() != 0
				&& article.getCategory() != null;
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
