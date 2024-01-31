package com.shhrrtnvr.smartaquaculture.service;

import com.shhrrtnvr.smartaquaculture.io.NotificationResponse;
import com.shhrrtnvr.smartaquaculture.repository.DeviceDataRepository;
import com.shhrrtnvr.smartaquaculture.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
  private final UserRepository userRepository;
  private final DeviceDataRepository deviceDataRepository;

  public List<NotificationResponse> getNotifications(Long userId) {
    var user = userRepository.findById(userId).orElseThrow();
    var start = Instant.now().minus(30, ChronoUnit.MINUTES);
    var end = Instant.now();

    return user.getDevices()
        .stream()
        .flatMap(
            device -> deviceDataRepository.findAllByDeviceIdAndTimestampBetween(device.getId(), start, end)
                .stream()
                .filter(data -> data.getDissolvedOxygen() < 5.0)
                .map(
                    data -> new NotificationResponse()
                        .setDeviceId(device.getId())
                        .setTimestamp(data.getTimestamp())
                        .setMessage("Dissolved Oxygen is low")
                )
        )
        .sorted(Comparator.comparing(NotificationResponse::getTimestamp).reversed())
        .toList();
  }
}
