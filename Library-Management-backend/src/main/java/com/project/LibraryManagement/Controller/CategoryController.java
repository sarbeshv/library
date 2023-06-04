package com.project.LibraryManagement.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.LibraryManagement.Service.CategoryService;
import com.project.LibraryManagement.entity.Category;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/categories")
	public class CategoryController {

		@Autowired
		private CategoryService categoryService;
		
		@GetMapping
		public List<Category> getAllCategories() {
			return categoryService.getAll();
		}
		
		@GetMapping("/{id}")
		public Category getCategory(@PathVariable(name = "id") Long id) {
			return categoryService.get(id);
		}
		
		@PostMapping
		public Category addCategory(@RequestBody Category category) {
			return categoryService.addNew(category);
		}
		
		@PutMapping("/{id}")
		public Category updateCategory(@PathVariable(name = "id") Long id, @RequestBody Category category) {
			category.setId(id);
			category.setCreateDate(new Date());
			return categoryService.save(category);
		}
		
		@DeleteMapping("/{id}")
		public void removeCategory(@PathVariable(name = "id") Long id) {
			categoryService.delete(id);
		}
	}

