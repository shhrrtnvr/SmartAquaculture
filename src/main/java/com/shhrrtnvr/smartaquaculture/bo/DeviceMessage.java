package com.shhrrtnvr.smartaquaculture.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class DeviceMessage {
  private Instant timestamp = Instant.now();
  private Long deviceId;
  private Double ph;
  private Double temperature;
}
