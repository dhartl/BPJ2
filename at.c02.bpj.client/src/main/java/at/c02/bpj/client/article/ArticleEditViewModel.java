package at.c02.bpj.client.article;

import at.c02.bpj.client.service.model.Article;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ArticleEditViewModel implements ViewModel {

	private SimpleObjectProperty<Long> id = new SimpleObjectProperty<>();
	private StringProperty name = new SimpleStringProperty();
	private DoubleProperty price = new SimpleDoubleProperty();


	public ArticleEditViewModel() {
	}

	public SimpleObjectProperty<Long> idProperty() {
		return id;
	}

	public StringProperty nameProperty() {
		return name;
	}

	public DoubleProperty priceProperty() {
		return price;
	}

	public void editArticle(Article article) {
		id.set(article.getArticleId());
		name.set(article.getName());
		price.set(article.getPrice());
	}

	public Article getArticle() {
		Article article = new Article();
		article.setArticleId(id.get());
		article.setName(name.get());
		article.setPrice(price.get());
		return article;
	}
}
