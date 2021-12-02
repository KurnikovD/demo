package com.example.demo.service;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
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

    @Test
    void testSortingTop(){
        DomainService domainService = new DomainServiceImpl();
        List.of("google.com", "lenta.ru", "lenta.ru",  "vk.com", "vk.com", "vk.com")
                .forEach(domainService::add);
        List<String> list = List.of("vk", "lenta", "google");
        Assertions.assertEquals(list, domainService.top(3));
    }

}