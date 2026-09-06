package com.learning.microservices.loanservice.client;

import com.learning.microservices.loanservice.model.MemberResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "member-service")
public interface MemberClient {

    @GetMapping("/members/{id}")
    MemberResponse getMember(@PathVariable Long id);
}