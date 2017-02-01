package at.c02.bpj.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import at.c02.bpj.server.entity.Article;
import at.c02.bpj.server.service.ArticleService;

@Controller
@RequestMapping("articles")
public class ArticleController {

	private ArticleService articleService;

	@Autowired
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Article> getAllArticles() {
		List<Article> articles = articleService.getAllArticles();
		return articles;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	@ResponseBody
	public Article getArticleById(@PathVariable("id") Long id) {
		return articleService.getArticleById(id);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/search")
	@ResponseBody
	public List<Article> findArticles(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "search", required = false) String search) {
		return articleService.findArticles(name, search);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Article createOrUpdateArticle(@RequestBody Article article) {
		Article responseArticle = articleService.createOrUpdateArticle(article);
		return responseArticle;
	}

}
