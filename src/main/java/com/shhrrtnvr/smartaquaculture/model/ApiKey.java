package com.shhrrtnvr.smartaquaculture.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "api_key")
@Getter
@Setter
@Accessors(chain = true)
public class ApiKey {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api_key_gen")
  @SequenceGenerator(name = "api_key_gen", sequenceName = "api_key_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Long id;
  String key;
  boolean active;
}
