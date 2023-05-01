package com.bezkoder.springjwt.client.model;


import com.bezkoder.springjwt.account.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE client SET flag_deleted = true WHERE id=?")
@Where(clause = "flag_deleted=false")
public class Client {
    @Id
    @GeneratedValue(generator = "client_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq", allocationSize = 1)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String fullName;

    @Column
    private String email;

    @Column
    private String nid;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "flag_deleted",columnDefinition = "boolean default false")
    private boolean flagDeleted;
}
