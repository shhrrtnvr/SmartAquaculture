package com.shhrrtnvr.smartaquaculture.repository;

import com.shhrrtnvr.smartaquaculture.model.DeviceData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceDataRepository extends CrudRepository<DeviceData, Long> {
  Optional<DeviceData> findFirstByDeviceIdOrderByTimestampDesc(Long deviceId);
  List<DeviceData> findAllByDeviceIdAndTimestampBetween(Long device_id, Instant timestamp, Instant timestamp2);
}
