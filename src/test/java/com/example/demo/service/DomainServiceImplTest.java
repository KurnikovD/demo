package com.example.demo.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertThrows;


class DomainServiceImplTest {

    @Test
    void testAddOk() {
        DomainService domainService = new DomainServiceImpl();
        domainService.add("google.com");
        assert (domainService.domain().containsKey("google"));
    }

    @Test
    void testAddException() {
        DomainService domainService = new DomainServiceImpl();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            domainService.add("google");
        });
        String exceptionMessage = "Wrong URL!";
        String actualMessage = exception.getMessage();
        assert (actualMessage.contains(exceptionMessage));
    }

    @Test
    void testReturnNResultsFromTop() {
        DomainService domainService = new DomainServiceImpl();
        List.of("google.com", "lenta.ru", "vk.com").forEach(domainService::add);
        int n = 2;
        assert (domainService.top(n).size() == n);
    }

    @Test
    void testContentInList() {
        DomainService domainService = new DomainServiceImpl();
        List.of("google.com", "lenta.ru", "vk.com").forEach(domainService::add);

        List<String> list = List.of("lenta", "vk", "google");
        assert (list.containsAll(domainService.top(2)));
    }


}