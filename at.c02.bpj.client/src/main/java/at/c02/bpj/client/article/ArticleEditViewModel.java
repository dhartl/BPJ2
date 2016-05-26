package at.c02.bpj.client.article;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Category;
import at.c02.bpj.client.service.CategoryService;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Model für {@link ArticleEditView}
 */
public class ArticleEditViewModel implements ViewModel {

	private SimpleObjectProperty<Long> id = new SimpleObjectProperty<>();
	private StringProperty name = new SimpleStringProperty();
	private DoubleProperty price = new SimpleDoubleProperty();
	private StringProperty description = new SimpleStringProperty();
	private ObjectProperty<Category> category = new SimpleObjectProperty<>();

	private ObservableList<Category> categoryList = FXCollections.observableArrayList();

	public ArticleEditViewModel(CategoryService categoryService) {
		categoryList.addAll(categoryService.getCategories());
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

	public StringProperty descriptionProperty() {
		return description;
	}

	public ObjectProperty<Category> categoryProperty() {
		return category;
	}

	public ObservableList<Category> categoryListProperty() {
		return categoryList;
	}

	/**
	 * setzt den zu bearbeitenden Artikel
	 * 
	 * @param article
	 */
	public void editArticle(Article article) {
		id.set(article.getArticleId());
		name.set(article.getName());
		price.set(article.getPrice());
		description.set(article.getDescription());
		category.set(article.getCategory());
	}

	public Article getArticle() {
		Article article = new Article();
		article.setArticleId(id.get());
		article.setName(name.get());
		article.setPrice(price.get());
		article.setDescription(description.get());
		article.setCategory(category.get());
		return article;
	}
}
