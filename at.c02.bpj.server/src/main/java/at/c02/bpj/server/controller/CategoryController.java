package at.c02.bpj.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import at.c02.bpj.server.entity.Category;
import at.c02.bpj.server.service.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {

	private CategoryService categoryService;

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Category> findAllCategories() {
		return categoryService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public Category getCategoryById(@PathVariable("id") Long id) {
		return categoryService.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Category createOrUpdateCategory(@RequestBody Category category) {
		return categoryService.createOrUpdateCategory(category);
	}
}
