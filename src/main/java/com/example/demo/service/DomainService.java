package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

public interface DomainService {

    void add(String url);

    List<String> top(Integer n);

    HashMap<String, Integer> domain();
}
