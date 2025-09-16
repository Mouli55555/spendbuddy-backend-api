package com.spendbuddy.response.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ExpenseResponse {
	
	private Long id;
	

	private BigDecimal expenseAmount;
	private String expenseDescription;
	private String categoryName;

	private String subCategoryName;
	private String paymentType;
	private Date createdAt;
	private Date updatedAt;

	private Date expenseDate;
	
	
	
	public ExpenseResponse(Long id, BigDecimal expenseAmount, String expenseDescription, String categoryName,String subCategoryName,
			String paymentType, Date createdAt, Date updatedAt,Date expenseDate) {
		super();
		this.id = id;
		this.expenseAmount = expenseAmount;
		this.subCategoryName = subCategoryName;
		this.expenseDescription = expenseDescription;
		this.categoryName = categoryName;
		this.paymentType = paymentType;
		this.expenseDate=expenseDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public BigDecimal getExpenseAmount() {
		return expenseAmount;
	}
	public void setExpenseAmount(BigDecimal expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	public String getExpenseDescription() {
		return expenseDescription;
	}
	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}
}
