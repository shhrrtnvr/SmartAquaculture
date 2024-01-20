package com.shhrrtnvr.smartaquaculture.bo;

import com.shhrrtnvr.smartaquaculture.model.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JwtClaim {
  private Long userId;
  private String username;
  private Role role;
}
