package com.spendbuddy.request.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubCategoryRequest {

	@NotNull(message="Category id should not be null.")
	private Long categoryId;

	@NotNull(message="SubCategory name should not be null.")
	@NotBlank(message="SubCategory name should not be blank.")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
}
