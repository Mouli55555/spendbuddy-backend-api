package com.spendbuddy.controller;

import java.util.List;


import com.spendbuddy.entity.expensetracker.PaymentType;
import com.spendbuddy.request.dto.PaymentTypeRequest;
import com.spendbuddy.request.dto.UpdatePaymentTypeRequest;
import com.spendbuddy.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
public class PaymentTypeController {

	@Autowired
	private PaymentService service;
	
	@GetMapping("/api/paymenttype")
	public List<PaymentType> list() {
		return service.list(); 
	}
	
	@PostMapping("/api/paymenttype")
	public PaymentType save(@Valid @RequestBody PaymentTypeRequest request) {
		return service.save(request);
	}
	
	
	@PutMapping("/api/paymenttype/{paymentId}")
	public PaymentType update(@Valid @RequestBody UpdatePaymentTypeRequest request, @PathVariable Long paymentId) throws Exception {
		return service.update(paymentId,request);
	}
	
	
}
