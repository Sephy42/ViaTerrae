package com.formation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.dto.category.CategoryFull;
import com.formation.persistence.entities.Category;
import com.formation.services.ICategoryService;

@RestController
@RequestMapping(path = "/api/private/category")
public class CategoryController {

	@Autowired
	ICategoryService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public List<CategoryFull> findAll() {
		
		
		return service.findAll()
				.stream()
				.map(cat -> mapper.map(cat, CategoryFull.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping(path = "/{id}")
	public CategoryFull findOne(@PathVariable Long id) {
		return mapper.map(service.findOne(id), CategoryFull.class);
	}
	
	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteById(id);
	}
	
	@PostMapping
	public CategoryFull save(@RequestBody CategoryFull category) {
		return mapper.map(service.save(mapper.map(category, Category.class)), CategoryFull.class);
	}
		
		
		}

