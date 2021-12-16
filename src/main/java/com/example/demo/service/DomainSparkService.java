package com.example.demo.service;

import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DomainSparkService implements DomainService {

    final DomainService domainService;

    final JavaSparkContext sparkContext;

    public DomainSparkService(@Qualifier("queueDecorator") DomainService domainService, JavaSparkContext sparkContext) {
        this.domainService = domainService;
        this.sparkContext = sparkContext;
    }

    @Override
    public void add(String url, Integer count) {
        domainService.add(url, count);
    }

    @Override
    public List<String> top(Integer n) {
        return null;
    }

    @Override
    public HashMap<String, Integer> domain() {
        return null;
    }
}
