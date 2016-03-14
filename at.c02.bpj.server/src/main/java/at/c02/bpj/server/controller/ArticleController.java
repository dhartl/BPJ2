package at.c02.bpj.server.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import at.c02.bpj.api.ArticleDTO;
import at.c02.bpj.server.entity.Article;
import at.c02.bpj.server.service.ArticleService;

@Controller
public class ArticleController {

	private ArticleService articleService;

	private ModelMapper modelMapper;

	@Autowired
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@RequestMapping(method = RequestMethod.GET, path = "articles")
	@ResponseBody
	public List<ArticleDTO> getAllArticles() {
		List<Article> articles = articleService.getAllArticles();
		return articles.stream().map(this::mapToArticleDTO).collect(Collectors.toList());
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "articles/{articleId}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteArticle(@PathVariable("articleId") long articleId) {
		articleService.deleteArticle(articleId);
	}

	@RequestMapping(method = RequestMethod.POST, path = "articles")
	@ResponseBody
	public ArticleDTO createOrUpdateArticle(@RequestBody ArticleDTO articleDTO) {
		Article article = mapToArticle(articleDTO);
		article = articleService.createOrUpdateArticle(article);
		return mapToArticleDTO(article);
	}

	private ArticleDTO mapToArticleDTO(Article article) {
		return modelMapper.map(article, ArticleDTO.class);
	}

	private Article mapToArticle(ArticleDTO article) {
		return modelMapper.map(article, Article.class);
	}
}
