package at.c02.bpj.client.api.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Article {
	private ObjectProperty<Long> articleId = new SimpleObjectProperty<>();
	private StringProperty name = new SimpleStringProperty();
	private DoubleProperty price = new SimpleDoubleProperty();

	// kein Argument-Konstruktor UNBEDINGT erforderlich
	public Article() {
	}

	public final ObjectProperty<Long> articleIdProperty() {
		return this.articleId;
	}

	public final Long getArticleId() {
		return this.articleIdProperty().get();
	}

	public final void setArticleId(final Long articleId) {
		this.articleIdProperty().set(articleId);
	}

	public final StringProperty nameProperty() {
		return this.name;
	}

	public final java.lang.String getName() {
		return this.nameProperty().get();
	}

	public final void setName(final java.lang.String name) {
		this.nameProperty().set(name);
	}

	public final DoubleProperty priceProperty() {
		return this.price;
	}

	public final double getPrice() {
		return this.priceProperty().get();
	}

	public final void setPrice(final double price) {
		this.priceProperty().set(price);
	}

	@Override
	public String toString() {
		return "Article [articleId=" + getArticleId() + "; name=" + getName() + "; price=;" + getPrice() + "]";
	}
}
