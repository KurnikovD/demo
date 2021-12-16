package com.example.demo.service;

import org.apache.avro.mapred.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class QueueDecorator implements DomainService {

    private final DomainService target;
    Queue<Pair<String, Integer>> addQueue = new LinkedBlockingQueue<>();

    QueueDecorator(@Qualifier("cachingDomainServiceImpl") DomainService source) {
        target = source;
        new Thread(() -> {
            while (true) {
                if (!addQueue.isEmpty()) {
                    Pair<String, Integer> poll = addQueue.poll();
                    target.add(poll.key(), poll.value());
                }
            }
        }).start();
    }

    @Override
    public void add(String url, Integer count) {
        addQueue.add(new Pair<>(url, count));
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
