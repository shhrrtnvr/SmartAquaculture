package com.shhrrtnvr.smartaquaculture.factory.mapper;

import com.shhrrtnvr.smartaquaculture.io.UserResponse;
import com.shhrrtnvr.smartaquaculture.model.User;

public class UserMapper {
  public static UserResponse toUserResponse(User user) {
    return new UserResponse()
            .setId(user.getId())
            .setUsername(user.getUsername())
            .setFirstName(user.getFirstName())
            .setLastName(user.getLastName())
            .setRole(user.getRole().toString())
            .setDevices(
                user.getDevices()
                    .stream()
                    .map(DeviceMapper::toDeviceInfo)
                    .toList()
            );
  }

  public static UserResponse toUserResponseWithoutDevices(User user) {
    return new UserResponse()
            .setId(user.getId())
            .setUsername(user.getUsername())
            .setFirstName(user.getFirstName())
            .setLastName(user.getLastName())
            .setRole(user.getRole().toString());
  }
}
