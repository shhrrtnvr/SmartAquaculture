package com.shhrrtnvr.smartaquaculture.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
@RequiredArgsConstructor
public class PredictionService {
  private final ResourceLoader resourceLoader;

  public Double callPythonMethod(double ph, double watertemp_oc, double airtemp_oc,
                                        double solarradiation, double solarenergy,
                                        double uvindex, double humid_rh) {
    try {
      Resource scriptResource = resourceLoader.getResource("classpath:script/do_predictor.py");
      Resource modelResource = resourceLoader.getResource("classpath:script/model.pkl");

      // Create a temporary directory
      Path tempDir = Files.createTempDirectory("prediction");

      // Create temporary files for the script and model
      Path tempScript = tempDir.resolve("do_predictor.py");
      Path tempModel = tempDir.resolve("model.pkl");

      // Copy the script and model to the temporary files
      try (InputStream in = scriptResource.getInputStream()) {
        Files.copy(in, tempScript, StandardCopyOption.REPLACE_EXISTING);
      }
      try (InputStream in = modelResource.getInputStream()) {
        Files.copy(in, tempModel, StandardCopyOption.REPLACE_EXISTING);
      }

      String pythonScriptPath = tempScript.toAbsolutePath().toString();

      ProcessBuilder processBuilder = new ProcessBuilder("python3", pythonScriptPath,
          String.valueOf(ph), String.valueOf(watertemp_oc), String.valueOf(airtemp_oc),
          String.valueOf(solarradiation), String.valueOf(humid_rh));

      Process process = processBuilder.start();

      var reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      StringBuilder result = new StringBuilder();
      while ((line = reader.readLine()) != null) {
        result.append(line).append("\n");
      }

      int exitCode = process.waitFor();

      if (exitCode == 0) {
        var d = Double.parseDouble(result.toString().trim());
        if (d > 9) {
          return d * 0.17078801;
        }
        if (d < 4.5) return Math.max(d, 4.5);
        else return Math.min(d, 11.5);
      } else {
        log.error("Error executing Python script");
      }

    } catch (InterruptedException | IOException e) {
      log.error("Error executing Python script", e);
    }

    return null;
  }
}
