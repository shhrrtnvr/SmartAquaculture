package com.shhrrtnvr.smartaquaculture.repository;

import com.shhrrtnvr.smartaquaculture.model.DeviceData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceDataRepository extends CrudRepository<DeviceData, Long> {
  Optional<DeviceData> findFirstByDeviceIdOrderByTimestampDesc(Long deviceId);
}
