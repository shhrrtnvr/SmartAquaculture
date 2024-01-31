package com.shhrrtnvr.smartaquaculture.io;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DeviceDataResponse {
    private Long id;
    private String timestamp;
    private Long deviceId;
    private Double ph;
    private Double waterTemperature;
    private Double airTemperature;
    private Double humidity;
    private Double tds;
    private Double solarRadiation;
    private Double solarEnergy;
    private Double uvIndex;

    @JsonSerialize(using = CustomDoubleSerializer.class)
    private Double dissolvedOxygen;
}
