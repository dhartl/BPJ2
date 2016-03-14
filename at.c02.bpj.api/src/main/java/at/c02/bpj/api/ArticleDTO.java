package at.c02.bpj.api;

import java.io.Serializable;
import java.math.BigDecimal;

public class ArticleDTO implements Serializable {

	private static final long serialVersionUID = -7429652998387069331L;

	private Long articleId;
	private String name;
	private BigDecimal price;

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ArticleDTO [articleId=" + articleId + ", name=" + name + ", price=" + price + "]";
	}

}
