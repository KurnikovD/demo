package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DomainServiceImpl implements DomainService {

    private static final HashMap<String, Integer> domains = new HashMap<>();

    @Override
    public void add(String url, Integer count) {
        String hostName = getHostNameFromURL(url);

        if (domains.containsKey(hostName)) {
            domains.compute(hostName, (k, v) -> v += count);
        } else {
            domains.put(hostName, count);
        }

    }

    private String getHostNameFromURL(String url) {
        String[] host = url.split("\\.");
        String hostName;
        try {
            hostName = host[host.length - 2];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Wrong URL!", e);
        }
        return hostName;
    }

    @Override
    public List<String> top(Integer n) {
        synchronized (domains) {
            return domains.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).map(Map.Entry::getKey).limit(n).collect(Collectors.toList());
        }
    }

    @Override
    public HashMap<String, Integer> domain() {
        return domains;
    }
}
