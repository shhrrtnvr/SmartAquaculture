package com.shhrrtnvr.smartaquaculture.io;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginResponse {
    private String accessToken;
}
