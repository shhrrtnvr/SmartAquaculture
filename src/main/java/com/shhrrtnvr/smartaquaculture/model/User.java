package com.shhrrtnvr.smartaquaculture.model;

import com.shhrrtnvr.smartaquaculture.bo.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_gen")
  @SequenceGenerator(name = "users_gen", sequenceName = "users_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Long id;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  @Enumerated(EnumType.STRING)
  private Role role;
}
