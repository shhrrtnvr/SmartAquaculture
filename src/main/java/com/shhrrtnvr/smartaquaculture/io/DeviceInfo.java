package com.shhrrtnvr.smartaquaculture.io;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DeviceInfo {
  private Long id;
  private String locationName;
  private String locationAddress;
  private Double latitude;
  private Double longitude;
}
