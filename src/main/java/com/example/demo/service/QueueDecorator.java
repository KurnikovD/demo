package com.example.demo.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class QueueDecorator implements DomainService {

    private final DomainService target;
    Queue<String> addQueue = new LinkedBlockingQueue<>();

    QueueDecorator(@Qualifier("cachingDomainServiceImpl") DomainService source) {
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
