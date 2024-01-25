package com.shhrrtnvr.smartaquaculture.service;

import com.shhrrtnvr.smartaquaculture.io.DeviceInfo;
import com.shhrrtnvr.smartaquaculture.io.Timeframe;
import com.shhrrtnvr.smartaquaculture.model.Device;
import com.shhrrtnvr.smartaquaculture.model.DeviceData;
import com.shhrrtnvr.smartaquaculture.repository.DeviceDataRepository;
import com.shhrrtnvr.smartaquaculture.repository.DeviceRepository;
import com.shhrrtnvr.smartaquaculture.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {
  private final DeviceRepository deviceRepository;
  private final DeviceDataRepository deviceDataRepository;
  private final UserRepository userRepository;

  public Device addDevice(Long userId, DeviceInfo deviceInfo){
    var user = userRepository.findById(userId).orElseThrow(
        () -> new RuntimeException("User not found")
    );
    var device = new Device();
    device.setLocationName(deviceInfo.getLocationName())
          .setLocationAddress(deviceInfo.getLocationAddress())
          .setLatitude(deviceInfo.getLatitude())
          .setLongitude(deviceInfo.getLongitude())
          .setUser(user);
    device = deviceRepository.save(device);
    return device;
  }

  public Device getDevice(Long deviceId) {
    return deviceRepository.findById(deviceId).orElseThrow(
        () -> new RuntimeException("Device not found")
    );
  }

  public List<Device> getAllDevices(Long userId) {
    return deviceRepository.findAllByUserId(userId);
  }

  public void addDeviceData(DeviceData deviceData) {
    deviceDataRepository.save(deviceData);
  }

  public DeviceData getCurrentData(Long deviceId) {
    return deviceDataRepository.findFirstByDeviceIdOrderByTimestampDesc(deviceId).orElseThrow(
        () -> new RuntimeException("Device data not found")
    );
  }

  public List<DeviceData> getRangeData(Long deviceId, Timeframe timeframe) {
    return deviceDataRepository.findAllByDeviceIdAndTimestampBetween(deviceId, timeframe.getStartDate(), timeframe.getEndDate());
  }
}
