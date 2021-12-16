package com.example.demo.controller;


import com.example.demo.entity.pojo;
import com.example.demo.service.DomainService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SparkController {
    final DomainService domainService;

    public SparkController(@Qualifier("queueDecorator") DomainService domainService) {
        this.domainService = domainService;
    }

    @RequestMapping(value = "/spark", method = RequestMethod.POST)
    public void add(@RequestBody List<pojo> params) {
        params.forEach(it -> domainService.add(it.getUrl(), it.getCount()));
    }

}
