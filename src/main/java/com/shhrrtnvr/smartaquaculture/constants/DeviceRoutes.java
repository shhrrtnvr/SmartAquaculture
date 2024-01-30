package com.shhrrtnvr.smartaquaculture.constants;

public interface DeviceRoutes {
  String ADD_DEVICE = "/add";
  String ADD_DEVICE_BY_ID = "/add/{userId}";
  String GET_DEVICE = "/{deviceId}";
  String GET_ALL_DEVICES = "/all";
  String GET_CURRENT_DATA = "/{deviceId}/data/current";
  String GET_RANGE_DATA = "/{deviceId}/data/range/{timeframe}";
  String UPDATE_DEVICE = "/update";
  String DELETE_DEVICE = "/delete/{deviceId}";
}
