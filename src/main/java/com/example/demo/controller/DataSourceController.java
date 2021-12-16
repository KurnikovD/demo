package com.example.demo.controller;

import com.example.demo.service.DomainService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class DataSourceController {

    final DomainService domainService;

    public DataSourceController(@Qualifier("domainDataSourceServiceImp") DomainService domainService) {
        this.domainService = domainService;
    }

    @RequestMapping(value = "/db/add/**")
    public void add(HttpServletRequest request) {

        String url = request.getRequestURI().split("db/add/", 2)[1];
        domainService.add(url, 1);
    }

    @RequestMapping(value = "/db/top/{num}")
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

    @RequestMapping(value = "/db/domain")
    public HashMap<String, Integer> getDomains() {
        return domainService.domain();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String argumentError(Exception e) {
        return (e.getMessage());
    }

}
