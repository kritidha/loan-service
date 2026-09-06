package com.learning.microservices.loanservice.contoller;


import com.learning.microservices.loanservice.model.Loan;
import com.learning.microservices.loanservice.service.LoanService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        return loanService.createLoan(loan);
    }

    @GetMapping("/{id}")
    public Loan getLoan(@PathVariable Long id) {
        return loanService.getLoan(id);
    }
}
