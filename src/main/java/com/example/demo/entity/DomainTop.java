package com.example.demo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "domain_top")
public class DomainTop {
    @Id
    @Getter
    @Setter
    @Column(name = "domain", nullable = false)
    private String domain;

    @Getter
    @Setter
    @Column(name = "count", nullable = false)
    private Integer count;

    public DomainTop(String domain, Integer count) {
        this.domain = domain;
        this.count = count;
    }

    public DomainTop() {
    }

    public void increaseCount(Integer count) {
        this.count += count;
    }
}
