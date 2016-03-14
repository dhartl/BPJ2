package at.c02.bpj.client.service.converter;

import java.math.BigDecimal;

import at.c02.bpj.api.ArticleDTO;
import at.c02.bpj.client.service.model.Article;

public class ArticleConverter implements Converter<ArticleDTO, Article> {

	@Override
	public ArticleDTO convertToDto(Article model) {
		ArticleDTO dto = new ArticleDTO();
		dto.setArticleId(model.getArticleId());
		dto.setName(model.getName());
		dto.setPrice(BigDecimal.valueOf(model.getPrice()));
		return dto;
	}

	@Override
	public Article convertToModel(ArticleDTO dto) {
		Article article = new Article();
		article.setArticleId(dto.getArticleId());
		article.setName(dto.getName());
		article.setPrice(dto.getPrice() != null ? dto.getPrice().doubleValue() : 0);
		return article;
	}

}
