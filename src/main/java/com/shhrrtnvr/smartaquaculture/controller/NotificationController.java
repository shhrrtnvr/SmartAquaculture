package com.shhrrtnvr.smartaquaculture.controller;

import com.shhrrtnvr.smartaquaculture.auth.RequestingUser;
import com.shhrrtnvr.smartaquaculture.bo.JwtClaim;
import com.shhrrtnvr.smartaquaculture.constants.NotificationRoute;
import com.shhrrtnvr.smartaquaculture.io.NotificationResponse;
import com.shhrrtnvr.smartaquaculture.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(NotificationRoute.BASE_ROUTE)
@RequiredArgsConstructor
public class NotificationController {
  private final NotificationService notificationService;

  @GetMapping(NotificationRoute.GET_NOTIFICATIONS)
  public ResponseEntity<List<NotificationResponse>> getNotifications(
      @RequestingUser JwtClaim claim
  ) {
    var notifications = notificationService.getNotifications(claim.getUserId());
    return ResponseEntity.ok(notifications);
  }
}
