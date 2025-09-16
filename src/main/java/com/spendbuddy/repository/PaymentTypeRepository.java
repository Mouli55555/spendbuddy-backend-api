package com.spendbuddy.repository;

import com.spendbuddy.entity.expensetracker.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long>{
	boolean existsById(Long id);
	
}
