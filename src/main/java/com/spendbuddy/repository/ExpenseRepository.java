package com.spendbuddy.repository;

import java.util.List;

import com.spendbuddy.entity.expensetracker.Expense;
import com.spendbuddy.response.dto.ExpenseResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	@Query("select new com.spendbuddy.response.dto.ExpenseResponse(ex.id,ex.expenseAmount,ex.expenseDescription,c.name,subCat.name,p.type,ex.createdAt,ex.updatedAt,ex.expenseDate) from Expense ex INNER JOIN Category c ON ex.category.id = c.id INNER JOIN SubCategory subCat ON ex.subCategoryId.id = subCat.id INNER JOIN PaymentType p ON  ex.payment.id = p.id where c.user.id = :userId ORDER BY ex.id desc")
	List<ExpenseResponse> listExpense(@Param("userId") Long userId);
	
	@Query("select new com.spendbuddy.response.dto.ExpenseResponse(ex.id,ex.expenseAmount,ex.expenseDescription,c.name,subCat.name,p.type,ex.createdAt,ex.updatedAt,ex.expenseDate) from Expense ex INNER JOIN Category c ON ex.category.id = c.id INNER JOIN SubCategory subCat ON ex.subCategoryId.id = subCat.id  INNER JOIN PaymentType p ON  ex.payment.id = p.id where c.user.id = :userId and cast(ex.createdAt AS timestamp) BETWEEN cast(:fromDate AS timestamp) AND cast(:toDate AS timestamp) ORDER BY ex.id desc")
	List<ExpenseResponse> listExpenseByDate(@Param("userId") Long userId,@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	
	
	@Query("select new com.spendbuddy.response.dto.ExpenseResponse(ex.id,ex.expenseAmount,ex.expenseDescription,c.name,subCat.name,p.type,ex.createdAt,ex.updatedAt,ex.expenseDate) from Expense ex INNER JOIN Category c ON ex.category.id = c.id INNER JOIN SubCategory subCat ON ex.subCategoryId.id = subCat.id INNER JOIN PaymentType p ON  ex.payment.id = p.id where c.user.id = :userId and c.id = :categoryId ORDER BY ex.id desc")
	List<ExpenseResponse> listExpenseByCategory(@Param("userId") Long userId, @Param("categoryId") Long categoryId);
	
	@Query("select new com.spendbuddy.response.dto.ExpenseResponse(ex.id,ex.expenseAmount,ex.expenseDescription,c.name,subCat.name,p.type,ex.createdAt,ex.updatedAt,ex.expenseDate) from Expense ex INNER JOIN Category c ON ex.category.id = c.id INNER JOIN SubCategory subCat ON ex.subCategoryId.id = subCat.id INNER JOIN PaymentType p ON  ex.payment.id = p.id where c.user.id = :userId and c.id = :categoryId and cast(ex.createdAt AS timestamp) BETWEEN cast(:fromDate AS timestamp) AND cast(:toDate AS timestamp) ORDER BY ex.id desc")
	List<ExpenseResponse> listExpenseByCategoryAndDate(@Param("userId") Long userId, @Param("categoryId") Long categoryId,@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	
	@Query("select new com.spendbuddy.response.dto.ExpenseResponse(ex.id,ex.expenseAmount,ex.expenseDescription,c.name,subCat.name,p.type,ex.createdAt,ex.updatedAt,ex.expenseDate) from Expense ex INNER JOIN Category c ON ex.category.id = c.id INNER JOIN SubCategory subCat ON ex.subCategoryId.id = subCat.id INNER JOIN PaymentType p ON  ex.payment.id = p.id where c.user.id = :userId and year(ex.createdAt) = :year and month(ex.createdAt) = :month  ORDER BY ex.id desc")
	List<ExpenseResponse> listExpenseCurrentMonth(@Param("userId") Long userId,@Param("month") int month,@Param("year") int year);
	
	
	@Query("select new com.spendbuddy.response.dto.ExpenseResponse(ex.id,ex.expenseAmount,ex.expenseDescription,c.name,subCat.name,p.type,ex.createdAt,ex.updatedAt,ex.expenseDate) from Expense ex INNER JOIN Category c ON ex.category.id = c.id INNER JOIN SubCategory subCat ON ex.subCategoryId.id = subCat.id INNER JOIN PaymentType p ON  ex.payment.id = p.id where c.user.id = :userId and c.id = :categoryId and year(ex.createdAt) = :year and month(ex.createdAt) = :month ORDER BY ex.id desc")
	List<ExpenseResponse> listExpenseCurrentMonthByCategory(@Param("userId") Long userId, @Param("categoryId") Long categoryId,@Param("month") int month,@Param("year") int year);
}





