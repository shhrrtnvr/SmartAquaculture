package com.shhrrtnvr.smartaquaculture.repository;

import com.shhrrtnvr.smartaquaculture.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
  List<Device> findAllByUserId(Long userId);
}
