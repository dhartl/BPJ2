package at.c02.bpj.client.article;

import at.c02.bpj.client.api.model.Article;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Dialog zum Erstellen oder Bearbeiten eines Artikels
 */
public class ArticleEditDialog extends Dialog<Article> {

	private ViewTuple<ArticleEditView, ArticleEditViewModel> viewTuple;

	public ArticleEditDialog() {
		// Laden von ArticleEditView
		viewTuple = FluentViewLoader
				.fxmlView(ArticleEditView.class).load();
		getDialogPane().setContent(viewTuple.getView());
		// Speichern-Button
		final ButtonType saveType = new ButtonType("Speichern", ButtonData.OK_DONE);
		// Dialog hat Buttons "Speichern" und "Abbrechen"
		getDialogPane().getButtonTypes().addAll(saveType, ButtonType.CANCEL);

		setResultConverter(buttonType -> {
			if (saveType.equals(buttonType)) {
				// Nur wenn Speichern gedrückt wurde, wird der Artikel
				// zurückgeliefert
				return viewTuple.getViewModel().getArticle();
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
