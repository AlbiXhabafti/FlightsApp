package com.bezkoder.springjwt.account.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
@AllArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE users SET flag_deleted = true WHERE id=?")
@Where(clause = "flag_deleted=false")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  @Column
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  @Column
  private String email;

  @NotBlank
  @Size(max = 120)
  @Column
  private String password;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinTable(  name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<Role> roles = new ArrayList<>();


  @Column
  private String token;

  @Column
  private LocalDateTime tokenCreationDate;

  @Column
  private Boolean flagDeleted;

  public User() {
  }


  public User(Long loggedUser) {
  }

  public <T> User(String username, String password, List<T> user) {
  }
}
