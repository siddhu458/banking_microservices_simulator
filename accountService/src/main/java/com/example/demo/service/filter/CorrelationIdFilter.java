package com.example.demo.service.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationIdFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain)
            throws ServletException, IOException {

        String correlation = req.getHeader("X-Correlation-Id");
        if (correlation == null || correlation.isEmpty()) {
            correlation = UUID.randomUUID().toString();
        }

        // Put into MDC so logback can use it
        MDC.put("correlationId", correlation);

        // Propagate back in response
        res.setHeader("X-Correlation-Id", correlation);

        try {
            chain.doFilter(req, res);
        } finally {
            MDC.remove("correlationId");
        }
    }
}
