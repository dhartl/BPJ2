package at.c02.bpj.client.article;

import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.service.ArticleService;
import at.c02.bpj.client.service.CategoryService;
import at.c02.bpj.client.test.TestData;

@RunWith(MockitoJUnitRunner.class)
public class ArticleViewModelTest {

	@Mock
	private ArticleService articleService;

	@Mock
	private CategoryService categoryService;

	private ArticleViewModel articleViewModel;

	@Before
	public void setup() {
		Mockito.reset(articleService, categoryService);
		articleViewModel = new ArticleViewModel(categoryService, articleService);
	}

	@Test
	public void testCreateArticle() {
		Article article1 = TestData.article1();
		Mockito.when(articleService.createArticle()).thenReturn(Optional.of(article1));
		Mockito.when(articleService.saveArticle(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());

		articleViewModel.newArticle();

		Assert.assertEquals(1, articleViewModel.articleListProperty().size());
		Assert.assertEquals(article1, articleViewModel.articleListProperty().get(0));
		Mockito.verify(articleService, Mockito.times(1)).saveArticle(article1);

	}

	@Test
	public void testCreateArticleAborted() {
		Mockito.when(articleService.createArticle()).thenReturn(Optional.empty());

		articleViewModel.newArticle();

		Assert.assertEquals(0, articleViewModel.articleListProperty().size());
		Mockito.verify(articleService, Mockito.times(0)).saveArticle(Mockito.any());
	}

	@Test
	public void testEditArticle() throws InterruptedException {
		Article article1 = TestData.article1();
		Article article2 = TestData.article2();
		Mockito.when(articleService.editArticle(Mockito.any())).thenReturn(Optional.of(article2));
		Mockito.when(articleService.saveArticle(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
		articleViewModel.setArticles(Lists.newArrayList(article1));

		articleViewModel.editArticle(article1);

		Assert.assertEquals(1, articleViewModel.articleListProperty().size());
		Assert.assertEquals(article2, articleViewModel.articleListProperty().get(0));
		Mockito.verify(articleService, Mockito.times(1)).saveArticle(article2);
	}
}
