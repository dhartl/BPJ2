package at.c02.bpj.server.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Java-Objekt Repräsentation der DB-Tabelle article
 */
@Entity
@Table(name = "article")
public class Article extends ModLogEntity<Long> {
	private static final long serialVersionUID = 7927325547287231032L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "articleId")
	private Long articleId;
	@Column(name = "name", length = 255, nullable = false)
	private String name;
	@Column(name = "price", nullable = false)
	private BigDecimal price;
	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryId")
	private Category category;

	@Override
	public Long getId() {
		return getArticleId();
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
