package at.c02.bpj.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends BaseEntity<Long> {
	private static final long serialVersionUID = 6624241982225343204L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "categoryId")
	private Long categoryId;
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	@Column(name = "sortNr", nullable = false)
	private Integer sortNr;

	@Override
	public Long getId() {
		return getCategoryId();
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSortNr() {
		return sortNr;
	}

	public void setSortNr(Integer sortNr) {
		this.sortNr = sortNr;
	}

}
