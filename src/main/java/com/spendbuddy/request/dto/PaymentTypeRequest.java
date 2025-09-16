package com.spendbuddy.request.dto;


import jakarta.validation.constraints.NotBlank;

public class PaymentTypeRequest {
	
	@NotBlank(message="Payment type should not be blank")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

	
}
