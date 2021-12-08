package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class QueueDecorator implements DomainService {

    Queue<String> addQueue = new LinkedList<>();
    private final DomainService target;

    QueueDecorator(CachingDomainServiceImpl source) {
        target = source;
        new Thread(() -> {
            while (true) {
                if (!addQueue.isEmpty()) {
                    target.add(addQueue.poll());
                }
            }
        }).start();
    }

    @Override
    public void add(String url) {
        addQueue.add(url);
    }

    @Override
    public List<String> top(Integer n) {
        return target.top(n);
    }

    @Override
    public HashMap<String, Integer> domain() {
        return target.domain();
    }
}
