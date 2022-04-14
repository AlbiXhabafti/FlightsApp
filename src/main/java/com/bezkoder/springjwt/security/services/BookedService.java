package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.BookedFlight;
import com.bezkoder.springjwt.repository.BookedRepo;
import com.bezkoder.springjwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service

public class BookedService {
    @Autowired
    BookedRepo bookedRepo;

    public Iterable<BookedFlight> get(){
        return bookedRepo.findAll();
    }
    public BookedFlight add(BookedFlight bookedFlight){
        return bookedRepo.save(bookedFlight);
    }
    public List<BookedFlight> getPageOne()
    {

        // First page with 5 items
        Pageable paging = PageRequest.of(
                0, 5, Sort.by("id").ascending());
        Page<BookedFlight> page = bookedRepo.findAll(paging);

        // Retrieve the items
        return page.getContent();
    }

}
