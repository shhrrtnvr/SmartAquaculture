package com.shhrrtnvr.smartaquaculture.factory.mapper;

import com.shhrrtnvr.smartaquaculture.io.DeviceDataResponse;
import com.shhrrtnvr.smartaquaculture.io.DeviceInfo;
import com.shhrrtnvr.smartaquaculture.model.Device;
import com.shhrrtnvr.smartaquaculture.model.DeviceData;

public class DeviceMapper {
  public static DeviceInfo toDeviceInfo(Device device) {
    return new DeviceInfo()
            .setId(device.getId())
            .setLocationName(device.getLocationName())
            .setLocationAddress(device.getLocationAddress())
            .setLatitude(device.getLatitude())
            .setLongitude(device.getLongitude());
  }

  public static DeviceDataResponse toDeviceDataResponse(DeviceData data) {
    return new DeviceDataResponse()
            .setId(data.getId())
            .setTimestamp(data.getTimestamp().toString())
            .setDeviceId(data.getDevice().getId())
            .setPh(data.getPh())
            .setWaterTemperature(data.getWaterTemperature())
            .setAirTemperature(data.getAirTemperature())
            .setHumidity(data.getHumidity())
            .setTds(data.getTds())
            .setSolarRadiation(data.getSolarRadiation())
            .setSolarEnergy(data.getSolarEnergy())
            .setUvIndex(data.getUvIndex())
            .setDissolvedOxygen(data.getDissolvedOxygen());
  }
}
