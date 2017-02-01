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

	@RequestMapping(method = RequestMethod.GET, path = "{categoryId}")
	@ResponseBody
	public Category getCategoryById(@PathVariable("categoryId") Long categoryId) {
		return categoryService.findById(Long.valueOf(categoryId));
	}

	@RequestMapping(method = RequestMethod.GET, path = "/search")
	@ResponseBody
	public List<Category> findCategories(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "search", required = false) String search) {
		return categoryService.findCategories(name, search);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Category createOrUpdateCategory(@RequestBody Category category) {
		return categoryService.createOrUpdateCategory(category);
	}
}
