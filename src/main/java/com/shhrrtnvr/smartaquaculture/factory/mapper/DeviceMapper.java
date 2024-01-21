package com.shhrrtnvr.smartaquaculture.factory.mapper;

import com.shhrrtnvr.smartaquaculture.io.DeviceInfo;
import com.shhrrtnvr.smartaquaculture.model.Device;

public class DeviceMapper {
  public static DeviceInfo toDeviceInfo(Device device) {
    return new DeviceInfo()
            .setId(device.getId())
            .setLocationName(device.getLocationName())
            .setLocationAddress(device.getLocationAddress())
            .setLatitude(device.getLatitude())
            .setLongitude(device.getLongitude());
  }
}
