package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class BulkDomainsAddRequest {
    @Getter
    @Setter
    List<DomainCount> domainsList;
}
