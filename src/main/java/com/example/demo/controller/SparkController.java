package com.example.demo.controller;


import com.example.demo.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SparkController {
    final
    DomainService domainService;

    public SparkController(@Qualifier("domainSparkService") DomainService domainService) {
        this.domainService = domainService;
    }

    @Value("${spark.fileName}")
    String fileName;

    @RequestMapping(value = "/spark")
    public void add(){
        domainService.add(fileName);
    }

}
