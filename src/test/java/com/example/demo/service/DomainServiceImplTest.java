package com.example.demo.service;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DomainServiceImplTest {

    @Test
    void testAddOk() {
        DomainService domainService = new DomainServiceImpl();
        domainService.add("google.com");
        HashMap<String, Integer> expectedResponse = new HashMap<>();
        expectedResponse.put("google", 1);
        assertEquals(expectedResponse, domainService.domain());
    }

    @Test
    void testAddException() {
        DomainService domainService = new DomainServiceImpl();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            domainService.add("google");
        });
        String exceptionMessage = "Wrong URL!";
        String actualMessage = exception.getMessage();
        assertEquals(exceptionMessage, actualMessage);
    }

    @Test
    void testReturnNResultsFromTop() {
        DomainService domainService = new DomainServiceImpl();
        List.of("google.com", "lenta.ru", "vk.com").forEach(domainService::add);
        int n = 2;
        assertEquals(n, domainService.top(n).size());
    }

    @Test
    void testSortingTop() {
        DomainService domainService = new DomainServiceImpl();
        List.of("google.com", "lenta.ru", "lenta.ru", "vk.com", "vk.com", "vk.com")
                .forEach(domainService::add);
        List<String> list = List.of("vk", "lenta", "google");
        assertEquals(list, domainService.top(3));
    }

}