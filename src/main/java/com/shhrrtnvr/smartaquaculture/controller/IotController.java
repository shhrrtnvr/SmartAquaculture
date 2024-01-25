package com.shhrrtnvr.smartaquaculture.controller;

import com.shhrrtnvr.smartaquaculture.bo.DeviceMessage;
import com.shhrrtnvr.smartaquaculture.constants.ControllerRoute;
import com.shhrrtnvr.smartaquaculture.constants.IotRoute;
import com.shhrrtnvr.smartaquaculture.constants.RoutingKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(ControllerRoute.IOT_ROUTE)
@RequiredArgsConstructor
public class IotController {
  private final RabbitTemplate rabbitTemplate;

  @GetMapping(IotRoute.POST_DATA)
  public ResponseEntity<Boolean> postData(
      @PathVariable Long deviceId,
      @PathVariable Double ph,
      @PathVariable Double temperature
  ) {
    var message = new DeviceMessage()
        .setDeviceId(deviceId)
        .setPh(ph)
        .setTemperature(temperature);
    rabbitTemplate.convertAndSend("amq.topic", RoutingKeys.DEVICE_DATA, message);
    return ResponseEntity.ok(true);
  }
}
