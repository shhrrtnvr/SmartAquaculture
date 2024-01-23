package com.shhrrtnvr.smartaquaculture.io;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String role;
    List<DeviceInfo> devices;
}
