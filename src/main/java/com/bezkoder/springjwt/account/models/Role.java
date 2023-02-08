package com.bezkoder.springjwt.account.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private RoleEnum roleEnum;

  public Role() {

  }

  public Role(RoleEnum name) {
    this.roleEnum = name;
  }

}