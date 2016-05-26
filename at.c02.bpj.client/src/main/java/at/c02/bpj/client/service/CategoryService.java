package at.c02.bpj.client.service;

import java.util.List;

import at.c02.bpj.client.api.CategoryApi;
import at.c02.bpj.client.api.model.Category;

public class CategoryService {
	private CategoryApi categoryApi;

	public CategoryService(CategoryApi categoryApi) {
		super();
		this.categoryApi = categoryApi;
	}

	public List<Category> getCategories() {
		return Services.executeCall(categoryApi.getCategories());
	}
}
