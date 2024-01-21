package com.shhrrtnvr.smartaquaculture.listener;

import com.shhrrtnvr.smartaquaculture.bo.DeviceMessage;
import com.shhrrtnvr.smartaquaculture.constants.Queues;
import com.shhrrtnvr.smartaquaculture.model.Device;
import com.shhrrtnvr.smartaquaculture.model.DeviceData;
import com.shhrrtnvr.smartaquaculture.service.DeviceService;
import com.shhrrtnvr.smartaquaculture.service.PredictionService;
import com.shhrrtnvr.smartaquaculture.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeviceDataListener {
  private final DeviceService deviceService;
  private final WeatherService weatherService;
  private final PredictionService predictionService;

  @RabbitListener(queues = Queues.SENSOR_DATA)
  public void sensorDataListener(DeviceMessage deviceMessage){
    log.info("Sensor data received: {}", deviceMessage);
    Device device;
    try {
      device = deviceService.getDevice(deviceMessage.getDeviceId());
      log.info("Device found: {}", device);
    } catch (Exception e) {
      log.error("Device not found for id: {}", deviceMessage.getDeviceId());
      return;
    }

    var weather = weatherService.getWeatherData(device.getLatitude(), device.getLongitude(), deviceMessage.getTimestamp());
    if (weather == null) {
      log.error("Weather data not found for device: {}", device);
      return;
    }
    log.info("Current Weather: {}", weather.getCurrentConditions());

    var prediction = predictionService.callPythonMethod(
        deviceMessage.getPh(),
        deviceMessage.getTemperature(),
        weather.getCurrentConditions().getTemp(),
        weather.getCurrentConditions().getSolarradiation(),
        weather.getCurrentConditions().getSolarenergy(),
        weather.getCurrentConditions().getUvindex(),
        weather.getCurrentConditions().getHumidity());

    if (prediction == null) {
      log.error("Prediction failed for device id: {}", device.getId());
      return;
    }

    log.info("Predicted dissolved oxygen: {}", prediction);

    deviceService.addDeviceData(
        new DeviceData()
            .setTimestamp(deviceMessage.getTimestamp())
            .setDevice(device)
            .setPh(deviceMessage.getPh())
            .setWaterTemperature(deviceMessage.getTemperature())
            .setAirTemperature(weather.getCurrentConditions().getTemp())
            .setHumidity(weather.getCurrentConditions().getHumidity())
            .setSolarRadiation(weather.getCurrentConditions().getSolarradiation())
            .setSolarEnergy(weather.getCurrentConditions().getSolarenergy())
            .setUvIndex(weather.getCurrentConditions().getUvindex())
            .setDissolvedOxygen(prediction)
    );
    log.info("Device data saved successfully");
  }
}
