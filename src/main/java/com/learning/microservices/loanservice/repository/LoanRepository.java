package com.learning.microservices.loanservice.repository;

import com.learning.microservices.loanservice.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}