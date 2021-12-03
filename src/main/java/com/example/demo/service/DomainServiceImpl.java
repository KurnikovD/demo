package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class DomainServiceImpl implements DomainService {

    private static final HashMap<String, Integer> domains = new HashMap<>();

    @CacheEvict(value = "top", allEntries = true)
    public void add(String url) {
        String[] host = url.split("\\.");
        try {
            if (domains.containsKey(host[host.length - 2])) {
                domains.compute(host[host.length - 2], (k, v) -> v = v + 1);
            } else {
                domains.put(host[host.length - 2], 1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Wrong URL!", e);
        }
    }

    @Cacheable(value = "top")
    public List<String> top(Integer n) {
        return domains.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .limit(n)
                .collect(Collectors.toList());
    }

    public HashMap<String, Integer> domain() {
        return domains;
    }

}