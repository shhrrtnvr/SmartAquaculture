package com.shhrrtnvr.smartaquaculture.bo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JwtClaim {
  private Integer userId;
}
