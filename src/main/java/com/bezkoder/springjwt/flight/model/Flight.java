package com.bezkoder.springjwt.flight.model;

import com.bezkoder.springjwt.account.models.User;
import com.bezkoder.springjwt.flight.enums.ClassEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String departure;

    @Column
    private String destination;

    @Column
    private LocalDateTime departureTime;

    @Enumerated(EnumType.STRING)
    private ClassEnum aClassEnum;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(  name = "booked",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();



}
