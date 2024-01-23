package com.shhrrtnvr.smartaquaculture.service;

import com.shhrrtnvr.smartaquaculture.io.WeatherResponse;
import com.shhrrtnvr.smartaquaculture.model.ApiKey;
import com.shhrrtnvr.smartaquaculture.repository.ApiKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class WeatherService {
  private final WebClient webClient;
  private final ApiKeyRepository apiKeyRepository;
  private String apiKey;

  public WeatherResponse getWeatherData(Double latitude, Double longitude, Instant timestamp) {
    if (apiKey == null) {
      updateApiKey();
    }
    var epochTime = timestamp.getEpochSecond();
    var n = 3;
    while (n-- > 0) {
      try {
        var response = getDummyData(latitude, longitude, epochTime, apiKey);
        if (response != null) {
          return response;
        }
      } catch (Exception e) {
        updateApiKey();
      }
    }
    return null;
  }

  private WeatherResponse getDummyData(Double latitude, Double longitude, Long epochTime, String apiKey) {
    return new WeatherResponse()
        .setLongitude(longitude)
        .setLatitude(latitude)
        .setCurrentConditions(
            new WeatherResponse.CurrentConditions()
                .setConditions("Sunny")
                .setDatetime("2021-07-01T12:00:00+05:30")
                .setDatetimeEpoch(1625131800)
                .setFeelslike(32.0)
                .setHumidity(50.0)
                .setPrecip(0.0)
                .setPressure(1000.0)
                .setTemp(30.0)
                .setUvindex(5.0)
        );
  }

  private WeatherResponse callApi(Double latitude, Double longitude, Long epochTime, String apiKey) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(String.format("/%.4f,%.4f/%d", latitude, longitude, epochTime))
            .queryParam("unitGroup", "metric")
            .queryParam("include", "current")
            .queryParam("key", apiKey)
            .queryParam("contentType", "json")
            .build())
        .retrieve()
        .onStatus(
            httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
            clientResponse -> clientResponse.bodyToMono(String.class).map(Exception::new))
        .bodyToMono(WeatherResponse.class)
        .block();
  }

  private void updateApiKey() {
    var keys = new ArrayList<ApiKey>();
    apiKeyRepository.findAll().forEach(keys::add);
    int index;
    for (index = 0; index < keys.size(); index++) {
      if (keys.get(index).isActive()) {
        keys.get(index).setActive(false);
        apiKeyRepository.save(keys.get(index));
        break;
      }
    }
    index = (index + 1) % keys.size();
    keys.get(index).setActive(true);
    apiKeyRepository.save(keys.get(index));
    this.apiKey = keys.get(index).getKey();
  }
}
