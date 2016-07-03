package at.c02.bpj.client.article;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Category;
import at.c02.bpj.client.service.ArticleService;
import at.c02.bpj.client.service.CategoryService;
import at.c02.bpj.client.test.MvvmFxGuiTest;
import at.c02.bpj.client.test.TestData;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.ViewModel;
import eu.lestard.easydi.EasyDI;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;

@RunWith(MockitoJUnitRunner.class)
public class ArticleViewTest extends MvvmFxGuiTest {

	@Mock
	private ArticleService articleService;
	@Mock
	private CategoryService categoryService;

	private Article article1;
	private Article article2;
	private Category category1;
	private Category category2;

	@Override
	public Class<? extends FxmlView<? extends ViewModel>> getViewClass() {
		return ArticleView.class;
	}

	@Override
	public void setupContext(EasyDI context) {
		article1 = TestData.article1();
		article2 = TestData.article2();
		category1 = TestData.category1();
		category2 = TestData.category2();
		Mockito.when(articleService.getArticles()).thenReturn(Lists.newArrayList(article1, article2));
		Mockito.when(categoryService.getCategories()).thenReturn(Lists.newArrayList(category1, category2));

		context.bindInstance(ArticleService.class, articleService);
		context.bindInstance(CategoryService.class, categoryService);
	}

	@Test
	public void testInitialize() {
		TableView<Article> tblArticles = find("#tblArticles");
		ComboBox<Category> categoryField = find("#categoryField");

		Assert.assertEquals(2, tblArticles.getItems().size());
		Assert.assertEquals(2, categoryField.getItems().size());
	}

	@Test
	public void testSearchCategory() {
		TableView<Article> tblArticles = find("#tblArticles");

		click("#categoryField").press(KeyCode.DOWN).press(KeyCode.ENTER);
		click("#searchButton");

		Assert.assertEquals(1, tblArticles.getItems().size());
		Assert.assertEquals(article1, tblArticles.getItems().get(0));
	}

	@Test
	public void testSearchId() {
		TableView<Article> tblArticles = find("#tblArticles");

		click("#idField").type("2");
		click("#searchButton");

		Assert.assertEquals(1, tblArticles.getItems().size());
		Assert.assertEquals(article2, tblArticles.getItems().get(0));
	}

	@Test
	public void testClearSearchId() {
		TableView<Article> tblArticles = find("#tblArticles");

		click("#idField").type("2");
		click("#searchButton");

		click("#idField").press(KeyCode.BACK_SPACE);
		click("#searchButton");

		Assert.assertEquals(2, tblArticles.getItems().size());
		Assert.assertEquals(article1, tblArticles.getItems().get(0));
		Assert.assertEquals(article2, tblArticles.getItems().get(1));
	}
}
