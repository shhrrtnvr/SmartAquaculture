package com.shhrrtnvr.smartaquaculture.io;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ErrorResponse {
  String errorMessage;
}
