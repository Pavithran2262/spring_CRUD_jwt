package com.example.CRUD.TransactionFilter;


import jakarta.servlet.ServletRequest;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/*")
public class FilterClass implements Filter {
    Logger log = LoggerFactory.getLogger(FilterClass.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Pre-processing logic goes here

        log.info("Fiter class Started " );
        // Perform some operations before the request reaches the target servlet or controller

        chain.doFilter(request, response); // Forward the request to the next filter or servlet in the chain

        // Post-processing logic goes here
        log.info("Fiter class Ended" );

        // Perform some operations after the response is generated
    }

    // Other methods (init, destroy) can be added if needed
}
