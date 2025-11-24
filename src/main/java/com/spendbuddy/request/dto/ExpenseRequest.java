package com.spendbuddy.request.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public class ExpenseRequest {

	@NotNull(message="Amount should not be null.")
	@Min(value = 0L, message = "Amount value must be positive.")
	private BigDecimal amount;
	
	private String expenseDescription;
	@NotNull(message="Payment Type Id should not be null.")
	private Long paymentId;
	@NotNull(message="Category Id should not be null.")
	private Long categoryId;

	@NotNull(message="Sub Category Id should not be null.")
	private Long subCategoryId;


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@NotNull(message="Expense Date should not be null.")
	private Date expenseDate;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getExpenseDescription() {
		return expenseDescription;
	}

	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}
}
