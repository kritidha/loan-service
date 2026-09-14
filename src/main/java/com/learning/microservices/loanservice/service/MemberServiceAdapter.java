package com.learning.microservices.loanservice.service;

import com.learning.microservices.loanservice.client.MemberClient;
import com.learning.microservices.loanservice.model.MemberResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceAdapter {

    private final MemberClient memberClient;

    public MemberServiceAdapter(MemberClient memberClient) {
        this.memberClient = memberClient;
    }

    @CircuitBreaker(name = "memberService", fallbackMethod = "getDefaultMember")
    @Retry(name = "memberService", fallbackMethod = "getDefaultMember")
    public MemberResponse fetchMemberDetails(Long memberId) {
        return memberClient.getMember(memberId);
    }

    public MemberResponse getDefaultMember(Long memberId, Throwable throwable) {
        System.out.println("Circuit Breaker Fallback activated! Error: " + throwable.getMessage());
        MemberResponse fallback = new MemberResponse();
        fallback.setId(memberId);
        fallback.setName("Service Unavailable - Fallback");
        fallback.setStatus("UNKNOWN");
        return fallback;
    }
}
