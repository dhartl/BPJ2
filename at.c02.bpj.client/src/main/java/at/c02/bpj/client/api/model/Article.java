package at.c02.bpj.client.api.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model-Objekt für Artikel
 */
public class Article {
	private ObjectProperty<Long> articleId = new SimpleObjectProperty<>();
	private StringProperty name = new SimpleStringProperty();
	private DoubleProperty price = new SimpleDoubleProperty();
	private StringProperty description = new SimpleStringProperty();
	private ObjectProperty<Category> category = new SimpleObjectProperty<>();

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

	public final StringProperty descriptionProperty() {
		return this.description;
	}

	public final java.lang.String getDescription() {
		return this.descriptionProperty().get();
	}

	public final void setDescription(final java.lang.String description) {
		this.descriptionProperty().set(description);
	}

	public final ObjectProperty<Category> categoryProperty() {
		return this.category;
	}

	public final at.c02.bpj.client.api.model.Category getCategory() {
		return this.categoryProperty().get();
	}

	public final void setCategory(final at.c02.bpj.client.api.model.Category category) {
		this.categoryProperty().set(category);
	}

	@Override
	public String toString() {
		return "Article [articleId=" + getArticleId() + "; name=" + getName() + "; price=;" + getPrice() + "]";
	}

}
