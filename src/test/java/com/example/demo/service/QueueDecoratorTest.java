package com.example.demo.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class QueueDecoratorTest {

    @Test
    void queueTest() {
        DomainService domainService = new QueueDecorator(new CachingDomainServiceImpl(new DomainServiceImpl()));
        String url = "google.com";
        int numberOfIteration = 100;

        CountDownLatch latch = new CountDownLatch(numberOfIteration);
        List<Runnable> runnableTasks = new ArrayList<>();
        for (int i = 0; i < numberOfIteration; i++) {
            runnableTasks.add(() -> {
                latch.countDown();

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                domainService.add(url);
            });
        }

        ExecutorService service = Executors.newFixedThreadPool(numberOfIteration);
        runnableTasks.forEach(service::execute);

        try {
            service.awaitTermination(100L, MILLISECONDS);
            service.shutdown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert (Objects.equals(domainService.domain().get("google"), numberOfIteration));

    }

}
