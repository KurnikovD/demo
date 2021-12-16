package com.example.demo.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@EnableCaching
public class CachingDomainServiceImpl implements DomainService {

    private final DomainService target;

    public CachingDomainServiceImpl(@Qualifier("domainServiceImpl") DomainService source) {
        this.target = source;
    }

    @CacheEvict(value = "top", allEntries = true)
    @Override
    public void add(String url, Integer count) {
        target.add(url, count);
    }

    @Cacheable("top")
    @Override
    public List<String> top(Integer n) {
        return target.top(n);
    }

    @Override
    public HashMap<String, Integer> domain() {
        return target.domain();
    }
}
