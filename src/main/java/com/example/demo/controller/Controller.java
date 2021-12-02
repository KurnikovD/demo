package com.example.demo.controller;

import com.example.demo.service.DomainService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


@RestController
public class Controller {

    private final DomainService domainService;

    public Controller(DomainService domainService) {
        this.domainService = domainService;
    }

    @RequestMapping(value = "/add/**")
    public void add(HttpServletRequest request) {

        String url = request.getRequestURI().split("/add/", 2)[1];
        domainService.add(url);
    }

    @RequestMapping(value = "/top/{num}")
    public List<String> top(@PathVariable String num) {
        int n;
        try {
            n = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error! Is not a number", e);
        }
        if (n <= 0) {
            throw new IllegalArgumentException("Error! The number must be greater than 0, but was " + n);
        }
        return domainService.top(n);
    }

    @RequestMapping(value = "/domain")
    public HashMap<String, Integer> getDomains() {
        return domainService.domain();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String argumentError(Exception e) {
        return (e.getMessage());
    }
}
