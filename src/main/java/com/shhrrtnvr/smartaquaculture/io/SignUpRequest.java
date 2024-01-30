package com.shhrrtnvr.smartaquaculture.io;

import com.shhrrtnvr.smartaquaculture.bo.Role;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SignUpRequest {
  @NonNull
  private String username;
  @NonNull
  private String password;
  private String firstName = "";
  private String lastName = "";
  private Role role = Role.USER;
}
