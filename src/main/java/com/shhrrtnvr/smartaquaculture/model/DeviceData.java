package com.shhrrtnvr.smartaquaculture.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;

@Entity
@Table(name = "device_data")
@Getter
@Setter
@Accessors(chain = true)
public class DeviceData {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_data_gen")
  @SequenceGenerator(name = "device_data_gen", sequenceName = "device_data_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Long id;
  private Instant timestamp;
  @ManyToOne
  @JoinColumn(name = "device_id", referencedColumnName = "device_id")
  private Device device;
  private Double ph;
  private Double waterTemperature;
  private Double airTemperature;
  private Double humidity;
  private Double tds;
  private Double solarRadiation;
  private Double solarEnergy;
  private Double uvIndex;
  private Double dissolvedOxygen;
}
