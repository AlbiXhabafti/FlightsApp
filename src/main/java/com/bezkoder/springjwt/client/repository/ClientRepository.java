package com.bezkoder.springjwt.client.repository;

import com.bezkoder.springjwt.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    public Client findByNid(String nid);

    public boolean existsByEmail(String email);
    public boolean existsByNid(String nid);

}
