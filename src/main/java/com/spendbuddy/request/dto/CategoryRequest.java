package com.spendbuddy.request.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoryRequest {

	@NotNull(message="Category name should not be null.")
	@NotBlank(message="Category name should not be blank.")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
