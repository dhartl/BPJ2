package at.c02.bpj.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import at.c02.bpj.server.entity.Category;
import at.c02.bpj.server.repository.CategoryRepository;

@Controller
@RequestMapping("category")
public class CategoryController {

	private CategoryRepository categoryRepository;

	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}
}
