package com.shhrrtnvr.smartaquaculture.controller;

import com.shhrrtnvr.smartaquaculture.auth.RequestingUser;
import com.shhrrtnvr.smartaquaculture.bo.JwtClaim;
import com.shhrrtnvr.smartaquaculture.bo.Role;
import com.shhrrtnvr.smartaquaculture.constants.ControllerRoute;
import com.shhrrtnvr.smartaquaculture.constants.DeviceRoutes;
import com.shhrrtnvr.smartaquaculture.factory.mapper.DeviceMapper;
import com.shhrrtnvr.smartaquaculture.io.DeviceDataResponse;
import com.shhrrtnvr.smartaquaculture.io.DeviceInfo;
import com.shhrrtnvr.smartaquaculture.io.Timeframe;
import com.shhrrtnvr.smartaquaculture.service.DeviceService;
import jakarta.security.auth.message.AuthException;
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

  @PostMapping(DeviceRoutes.ADD_DEVICE_BY_ID)
  public ResponseEntity<DeviceInfo> addDeviceById(
      @PathVariable Long userId,
      @RequestBody DeviceInfo deviceInfo,
      @RequestingUser JwtClaim claim
  ) throws AuthException {
    if (claim.getRole() != Role.ADMIN) {
      throw new AuthException("Unauthorized");
    }
    var device = deviceService.addDevice(userId, deviceInfo);
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

  @GetMapping(DeviceRoutes.GET_ALL_DEVICES)
  public ResponseEntity<List<DeviceInfo>> getAllDevices(
      @RequestingUser JwtClaim claim
  ){
    var devices = deviceService.getAllDevices(claim.getUserId());
    var response = devices.stream()
            .map(DeviceMapper::toDeviceInfo)
            .toList();
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

  @PutMapping(DeviceRoutes.UPDATE_DEVICE)
  public ResponseEntity<DeviceInfo> updateDevice(
      @RequestBody DeviceInfo deviceInfo
  ){
    var device = deviceService.updateDevice(deviceInfo);
    var response = DeviceMapper.toDeviceInfo(device);
    return ResponseEntity.ok(response);
  }

  @PostMapping(DeviceRoutes.DELETE_DEVICE)
  public ResponseEntity<Boolean> deleteDevice(
      @PathVariable Long deviceId,
      @RequestingUser JwtClaim claim
  ) throws AuthException {
    if (claim.getRole() != Role.ADMIN) {
      throw new AuthException("Unauthorized");
    }
    var response = deviceService.deleteDevice(deviceId);
    return ResponseEntity.ok(response);
  }

}
