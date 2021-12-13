package com.example.demo.service;

import com.example.demo.entity.DomainTop;
import com.example.demo.repository.DomainRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DomainDataSourceServiceImp implements DomainService {

    final DomainRepository domainRepository;

    public DomainDataSourceServiceImp(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @Override
    public void add(String url) {
        String hostName = getHostNameFromURL(url);

        if (domainRepository.existsDomainTopByDomain(hostName)) {
            DomainTop domainTopByDomain = domainRepository.findDomainTopByDomain(hostName);
            domainTopByDomain.increaseCount();
            domainRepository.save(domainTopByDomain);
        } else {
            domainRepository.save(new DomainTop(hostName, 1));
        }
    }

    private String getHostNameFromURL(String url) {
        String[] host = url.split("\\.");
        String hostName;
        try {
            hostName = host[host.length - 2];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Wrong URL!", e);
        }
        return hostName;
    }

    @Override
    public List<String> top(Integer n) {
        return domainRepository.getAllByOrderByCount().stream()
                .limit(n)
                .map(DomainTop::getDomain)
                .collect(Collectors.toList());
    }

    @Override
    public HashMap<String, Integer> domain() {
        HashMap<String, Integer> domain = new HashMap<>();
        domainRepository.findAllBy().forEach(it -> domain.put(it.getDomain(), it.getCount()));
        return domain;
    }
}
