package com.shhrrtnvr.smartaquaculture.controller;

import com.shhrrtnvr.smartaquaculture.constants.ControllerRoute;
import com.shhrrtnvr.smartaquaculture.constants.DeviceRoutes;
import com.shhrrtnvr.smartaquaculture.factory.mapper.DeviceMapper;
import com.shhrrtnvr.smartaquaculture.io.DeviceInfo;
import com.shhrrtnvr.smartaquaculture.model.DeviceData;
import com.shhrrtnvr.smartaquaculture.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ControllerRoute.DEVICE)
@RequiredArgsConstructor
public class DeviceController {
  private final DeviceService deviceService;

  @PostMapping(DeviceRoutes.ADD_DEVICE)
  public ResponseEntity<DeviceInfo> addDevice(
      @RequestBody DeviceInfo deviceInfo
  ){
    var device = deviceService.addDevice(deviceInfo);
    var response = DeviceMapper.toDeviceInfo(device);
    return ResponseEntity.ok(response);
  }

  @GetMapping(DeviceRoutes.GET_DEVICE)
  public ResponseEntity<DeviceInfo> getDevice(
      @PathVariable Long deviceId
  ){
    var device = deviceService.getDevice(deviceId);
    var response = DeviceMapper.toDeviceInfo(device);
    return ResponseEntity.ok(response);
  }

  @GetMapping(DeviceRoutes.GET_CURRENT_DATA)
  public ResponseEntity<DeviceData> getCurrentData(
      @PathVariable Long deviceId
  ){
    var deviceData = deviceService.getCurrentData(deviceId);
    return ResponseEntity.ok(deviceData);
  }

}
