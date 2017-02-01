package at.c02.bpj.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.c02.bpj.server.entity.Category;
import at.c02.bpj.server.repository.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;

	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findById(Long categoryId) {
		return categoryRepository.findOne(categoryId);
	}

	public Category createOrUpdateCategory(Category category) {
		return categoryRepository.saveAndFlush(category);
	}

	public List<Category> findCategories(String name, String search) {
		if (name != null) {
			return categoryRepository.findByName(name.toLowerCase());
		}
		if (search != null) {
			return categoryRepository.findByNameLike("%" + search + "%");
		}
		return null;
	}

}
