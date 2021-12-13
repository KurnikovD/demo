package com.example.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name = "domain_top")
public class DomainTop {
    @Id
    @Column(name = "domain", nullable = false)
    private String domain;

    @Column(name = "count", nullable = false)
    private Integer count;

    public DomainTop(String domain, Integer count) {
        this.domain = domain;
        this.count = count;
    }

    public DomainTop() {
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void increaseCount() {
        this.count++;
    }
}
