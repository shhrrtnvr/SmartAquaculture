package com.shhrrtnvr.smartaquaculture.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "device")
@Getter
@Setter
@Accessors(chain = true)
public class Device {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_gen")
  @SequenceGenerator(name = "device_gen", sequenceName = "device_seq", allocationSize = 1)
  @Column(name = "device_id", nullable = false)
  private Long id;
  private String locationName;
  private String locationAddress;
  private Double latitude;
  private Double longitude;
}
