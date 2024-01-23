package com.shhrrtnvr.smartaquaculture.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@Slf4j
@RequiredArgsConstructor
public class PredictionService {
  private final ResourceLoader resourceLoader;
  public Double callPythonMethod(double ph, double watertemp_oc, double airtemp_oc,
                                        double solarradiation, double solarenergy,
                                        double uvindex, double humid_rh) {
    try {
      Resource resource = resourceLoader.getResource("classpath:script/do_predictor.py");
      String pythonScriptPath = resource.getFile().getAbsolutePath();

      ProcessBuilder processBuilder = new ProcessBuilder("python3", pythonScriptPath,
          String.valueOf(ph), String.valueOf(watertemp_oc), String.valueOf(airtemp_oc),
          String.valueOf(solarradiation), String.valueOf(solarenergy),
          String.valueOf(uvindex), String.valueOf(humid_rh));

      Process process = processBuilder.start();

      var reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      StringBuilder result = new StringBuilder();
      while ((line = reader.readLine()) != null) {
        result.append(line).append("\n");
      }

      int exitCode = process.waitFor();

      if (exitCode == 0) {
        return Double.parseDouble(result.toString().trim());
      } else {
        log.error("Error executing Python script");
      }

    } catch (InterruptedException | IOException e) {
      log.error("Error executing Python script", e);
    }

    return null;
  }
}