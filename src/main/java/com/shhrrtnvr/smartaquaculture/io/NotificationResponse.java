package com.shhrrtnvr.smartaquaculture.io;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class NotificationResponse {
    private String message;
    private String type;
    private String title;
    private Instant timestamp;
    private Long deviceId;
}
