package com.shhrrtnvr.smartaquaculture.service;

import com.shhrrtnvr.smartaquaculture.io.DeviceInfo;
import com.shhrrtnvr.smartaquaculture.model.Device;
import com.shhrrtnvr.smartaquaculture.model.DeviceData;
import com.shhrrtnvr.smartaquaculture.repository.DeviceDataRepository;
import com.shhrrtnvr.smartaquaculture.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {
  private final DeviceRepository deviceRepository;
  private final DeviceDataRepository deviceDataRepository;

  public Device addDevice(DeviceInfo deviceInfo){
    var device = new Device();
    device.setLocationName(deviceInfo.getLocationName())
          .setLocationAddress(deviceInfo.getLocationAddress())
          .setLatitude(deviceInfo.getLatitude())
          .setLongitude(deviceInfo.getLongitude());
    device = deviceRepository.save(device);
    return device;
  }

  public Device getDevice(Long deviceId) {
    return deviceRepository.findById(deviceId).orElseThrow(
        () -> new RuntimeException("Device not found")
    );
  }

  public boolean addDeviceData(DeviceData deviceData) {
    deviceDataRepository.save(deviceData);
    return true;
  }

  public DeviceData getCurrentData(Long deviceId) {
    return deviceDataRepository.findFirstByDeviceIdOrderByTimestampDesc(deviceId).orElseThrow(
        () -> new RuntimeException("Device data not found")
    );
  }
}
