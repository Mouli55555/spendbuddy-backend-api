package com.spendbuddy.controller;


import com.spendbuddy.entity.expensetracker.SubCategory;
import com.spendbuddy.request.dto.SubCategoryRequest;
import com.spendbuddy.response.dto.SubCategoryResponse;
import com.spendbuddy.service.SubCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
public class SubCategoryController {
	@Autowired
	private SubCategoryService subCategoryService;

	@GetMapping("/api/subcategory")
	public List<SubCategoryResponse> list(@RequestParam(required=true,name="categoryId") Long categoryId) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return subCategoryService.list(categoryId,userDetails);
	}

	@PostMapping("/api/subcategory")
	public ResponseEntity<SubCategory> save(@Valid @RequestBody SubCategoryRequest categoryRequest) throws Exception {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(subCategoryService.save(userDetails,categoryRequest));
	}

}
