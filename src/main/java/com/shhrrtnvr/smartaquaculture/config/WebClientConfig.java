package com.shhrrtnvr.smartaquaculture.config;

import com.shhrrtnvr.smartaquaculture.constants.Weather;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
  @Bean
  public WebClient getWebClient() {
    return WebClient.builder()
        .baseUrl(Weather.API_URL)
        .build();
  }
}
