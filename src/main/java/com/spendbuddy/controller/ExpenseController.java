package com.spendbuddy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.spendbuddy.entity.expensetracker.Expense;
import com.spendbuddy.exception.handler.EntityException;
import com.spendbuddy.request.dto.ExpenseRequest;
import com.spendbuddy.response.dto.ExpenseResponse;
import com.spendbuddy.service.ExpenseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
public class ExpenseController {
	
	@Autowired
	private ExpenseService service;
	
	private Logger logger=LoggerFactory.getLogger(ExpenseController.class);

	@PostMapping("/api/expense")
	public Expense save(@Valid @RequestBody ExpenseRequest request) throws Exception {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return service.save(userDetails,request);
	}
	
	@GetMapping("/api/expense")
	public List<ExpenseResponse> list(@RequestParam(required=false,name="fromDate") String fromDate, @RequestParam(required=false,name="toDate") String toDate){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return service.list(userDetails,fromDate,toDate);
	}
	
	@GetMapping("/api/expense/currentmonth")
	public List<ExpenseResponse> listExpenseCurrentMonth(){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return service.listExpenseCurrentMonth(userDetails);
	}
	
	@GetMapping("/api/expense/category/{categoryId}")
	public List<ExpenseResponse> listExpenseByCategory(@PathVariable Long categoryId,@RequestParam(required=false,name="fromDate") String fromDate,@RequestParam(required=false,name="toDate") String toDate){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return service.listExpenseByCategory(userDetails, categoryId,fromDate,toDate);
	
	}
	
	@GetMapping("/api/expense/currentmonth/category/{categoryId}")
	public List<ExpenseResponse> listExpenseCurrentMonthByCategory(@PathVariable Long categoryId){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return service.listExpenseCurrentMonthByCategory(userDetails, categoryId);
	
	}

	@DeleteMapping("/api/expense/{expenseId}")
	public ResponseEntity<Map<String, Object>> deleteExpense(@PathVariable Long expenseId) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		service.deleteExpense(userDetails, expenseId);
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("message", "Expense deleted successfully");
		return ResponseEntity.ok(response);
	}


	@PutMapping("/api/expense/{expenseId}")
	public ResponseEntity<?> updateExpense(
			@AuthenticationPrincipal UserDetails userDetails,
			@PathVariable Long expenseId,
			@RequestBody ExpenseRequest request) {
		try {
			Expense updatedExpense = service.updateExpense(userDetails, expenseId, request);
			return ResponseEntity.ok(updatedExpense);
		} catch (EntityException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
