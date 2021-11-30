package com.example.demo.controller;

import com.example.demo.service.DomainService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestController
public class Controller {

//    private static final HashMap<String, Integer> domains = new HashMap<String, Integer>();
//    private static final List<String> url_list = new ArrayList<>();


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
    public List<String> top(@PathVariable String num){
        try {
            int n = Integer.parseInt(num);
            return domainService.top(n);
        }catch (Exception e){
            return Arrays.asList("Wrong! is not a number!");
        }
    }

    @RequestMapping(value = "/domain")
    public HashMap<String, Integer> getDomains(){
        return domainService.domain();
    }
}
