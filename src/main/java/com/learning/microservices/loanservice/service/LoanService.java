package com.learning.microservices.loanservice.service;


import com.learning.microservices.loanservice.client.MemberClient;
import com.learning.microservices.loanservice.model.Loan;
import com.learning.microservices.loanservice.model.MemberResponse;
import com.learning.microservices.loanservice.repository.LoanRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final MemberServiceAdapter memberServiceAdapter;




    public LoanService(LoanRepository loanRepository,
                       MemberServiceAdapter memberServiceAdapter) {
        this.loanRepository = loanRepository;
        this.memberServiceAdapter = memberServiceAdapter;
    }

    public Loan createLoan(Loan loan) {

        MemberResponse member =
                memberServiceAdapter.fetchMemberDetails(loan.getMemberId());
        if (member == null) {
            throw new RuntimeException("Member not found");
        }

        return loanRepository.save(loan);
    }

    public Loan getLoan(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Loan not found: " + id));
    }
}