package com.shhrrtnvr.smartaquaculture.controller;

import com.shhrrtnvr.smartaquaculture.auth.RequestingUser;
import com.shhrrtnvr.smartaquaculture.bo.JwtClaim;
import com.shhrrtnvr.smartaquaculture.constants.ControllerRoute;
import com.shhrrtnvr.smartaquaculture.constants.DeviceRoutes;
import com.shhrrtnvr.smartaquaculture.factory.mapper.DeviceMapper;
import com.shhrrtnvr.smartaquaculture.io.DeviceDataResponse;
import com.shhrrtnvr.smartaquaculture.io.DeviceInfo;
import com.shhrrtnvr.smartaquaculture.io.Timeframe;
import com.shhrrtnvr.smartaquaculture.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(ControllerRoute.DEVICE_ROUTE)
@RequiredArgsConstructor
public class DeviceController {
  private final DeviceService deviceService;

  @PostMapping(DeviceRoutes.ADD_DEVICE)
  public ResponseEntity<DeviceInfo> addDevice(
      @RequestingUser JwtClaim claim,
      @RequestBody DeviceInfo deviceInfo
  ){
    var device = deviceService.addDevice(claim.getUserId(), deviceInfo);
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
  public ResponseEntity<DeviceDataResponse> getCurrentData(
      @PathVariable Long deviceId
  ){
    var deviceData = deviceService.getCurrentData(deviceId);
    var response = DeviceMapper.toDeviceDataResponse(deviceData);
    return ResponseEntity.ok(response);
  }

  @GetMapping(DeviceRoutes.GET_RANGE_DATA)
  public ResponseEntity<List<DeviceDataResponse>> getRangeData(
      @PathVariable Long deviceId,
      @PathVariable Timeframe timeframe
  ){
    var deviceData = deviceService.getRangeData(deviceId, timeframe);
    var response = deviceData.stream()
            .map(DeviceMapper::toDeviceDataResponse)
            .toList();
    return ResponseEntity.ok(response);
  }

}
