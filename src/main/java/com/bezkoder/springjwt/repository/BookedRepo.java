package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.BookedFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedRepo extends JpaRepository<BookedFlight,Long> {


}
