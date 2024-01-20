package com.shhrrtnvr.smartaquaculture.io;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class LoginRequest {
  private String username;
  private String password;
}
