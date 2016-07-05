package at.c02.bpj.client.api.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {
	private LongProperty categoryId = new SimpleLongProperty();
	private StringProperty name = new SimpleStringProperty();
	private IntegerProperty sortNr = new SimpleIntegerProperty();

	public final LongProperty categoryIdProperty() {
		return this.categoryId;
	}

	public final java.lang.Long getCategoryId() {
		return this.categoryIdProperty().get();
	}

	public final void setCategoryId(final java.lang.Long categoryId) {
		this.categoryIdProperty().set(categoryId);
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

	public final IntegerProperty sortNrProperty() {
		return this.sortNr;
	}

	public final int getSortNr() {
		return this.sortNrProperty().get();
	}

	public final void setSortNr(final int sortNr) {
		this.sortNrProperty().set(sortNr);
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId.get() + ", name=" + name.get() + ", sortNr=" + sortNr.get() + "]";
	}

}
