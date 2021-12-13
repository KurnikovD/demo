package com.example.demo.repository;


import com.example.demo.entity.DomainTop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface DomainRepository extends CrudRepository<DomainTop, Long> {

    List<DomainTop> getAllByOrderByCount();

    boolean existsDomainTopByDomain(String domain);

    DomainTop findDomainTopByDomain(String domain);

    List<DomainTop> findAllBy();
}
