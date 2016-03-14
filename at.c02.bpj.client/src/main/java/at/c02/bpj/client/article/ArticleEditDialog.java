package at.c02.bpj.client.article;

import at.c02.bpj.client.service.model.Article;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class ArticleEditDialog extends Dialog<Article> {

	private ViewTuple<ArticleEditView, ArticleEditViewModel> viewTuple;

	public ArticleEditDialog() {
		viewTuple = FluentViewLoader
				.fxmlView(ArticleEditView.class).load();
		getDialogPane().setContent(viewTuple.getView());
		final ButtonType saveType = new ButtonType("Speichern", ButtonData.OK_DONE);
		getDialogPane().getButtonTypes().addAll(saveType, ButtonType.CANCEL);
		setResultConverter(buttonType -> {
			if (saveType.equals(buttonType)) {
				return viewTuple.getViewModel().getArticle();
			}
			return null;
		});
	}

	public void setArticle(Article article) {
		viewTuple.getViewModel().editArticle(article);
	}

}
