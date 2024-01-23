package com.shhrrtnvr.smartaquaculture.constants;

public interface DeviceRoutes {
  String ADD_DEVICE = "/add";
  String GET_DEVICE = "/{deviceId}";
  String GET_CURRENT_DATA = "/{deviceId}/data/current";
  String GET_RANGE_DATA = "/{deviceId}/data/range/{timeframe}";
}
