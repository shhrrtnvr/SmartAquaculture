package com.shhrrtnvr.smartaquaculture.repository;

import com.shhrrtnvr.smartaquaculture.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
}
