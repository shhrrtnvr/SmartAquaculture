package com.shhrrtnvr.smartaquaculture.io;

import com.shhrrtnvr.smartaquaculture.bo.Role;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfoUpdateRequest {
  private Long id;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private Role role;
}
