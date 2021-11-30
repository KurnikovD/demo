package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DomainService {

    private static final HashMap<String, Integer> domains = new HashMap<String, Integer>();
    private static final List<String> urlList = new ArrayList<>();

    public void add(String url) {
        try {
            String[] host = url.split("\\.");

            if (domains.containsKey(host[host.length - 2])) {
                domains.compute(host[host.length - 2], (k, v) -> v = v + 1);
            } else {
                domains.put(host[host.length - 2], 1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Wrong URL!", e);
        }
    }

    public List<String> top(int n) {
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
