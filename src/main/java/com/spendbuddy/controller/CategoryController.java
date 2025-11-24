package com.spendbuddy.controller;

import com.spendbuddy.entity.expensetracker.Category;
import com.spendbuddy.request.dto.CategoryRequest;
import com.spendbuddy.response.dto.CategoryResponse;
import com.spendbuddy.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/api/category")
	public List<CategoryResponse> list() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return categoryService.list(userDetails);

	}
	
	@PostMapping("/api/category")
	public ResponseEntity<Category> save(@Valid @RequestBody CategoryRequest categoryRequest) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(categoryService.save(userDetails, categoryRequest));
	}

}
