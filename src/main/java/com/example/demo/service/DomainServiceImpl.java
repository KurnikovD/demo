package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

public interface DomainServiceImpl {

    void add(String url);

    List<String> top(int n);

    HashMap<String, Integer> domain();
}
