package com.example.demo.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueueDecoratorTest {

    @Test
    void queueTest() throws InterruptedException {
        DomainService domainService = new QueueDecorator(new CachingDomainServiceImpl(new DomainServiceImpl()));
        String url = "google.com";
        Integer numberOfIteration = 100;

        CountDownLatch startLatch = new CountDownLatch(numberOfIteration);
        CountDownLatch terminationLatch = new CountDownLatch(numberOfIteration);
        List<Runnable> runnableTasks = new ArrayList<>();
        for (int i = 0; i < numberOfIteration; i++) {
            runnableTasks.add(() -> {
                startLatch.countDown();

                try {
                    startLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                domainService.add(url, 1);
                terminationLatch.countDown();
            });
        }

        ExecutorService service = Executors.newFixedThreadPool(numberOfIteration);
        runnableTasks.forEach(service::execute);

        terminationLatch.await();
        service.shutdown();

        assertEquals(domainService.domain().get("google"), numberOfIteration);

    }

}
