package com.shhrrtnvr.smartaquaculture.io;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Getter
@Setter
@Accessors(chain = true)
public class WeatherResponse {
  private int queryCost;
  private double latitude;
  private double longitude;
  private String resolvedAddress;
  private String address;
  private String timezone;
  private double tzoffset;
  private List<Day> days;
  private CurrentConditions currentConditions;

  @Data
  @Getter
  @Setter
  @Accessors(chain = true)
  public static class CurrentConditions {
    private String datetime;
    private long datetimeEpoch;
    private double temp;
    private double feelslike;
    private double humidity;
    private double dew;
    private double precip;
    private double precipprob;
    private double snow;
    private double snowdepth;
    private Object preciptype;
    private double windgust;
    private double windspeed;
    private double winddir;
    private double pressure;
    private double visibility;
    private double cloudcover;
    private double solarradiation;
    private double solarenergy;
    private double uvindex;
    private double severerisk;
    private String conditions;
    private String icon;
    private Object stations;
    private String source;
    private String sunrise;
    private long sunriseEpoch;
    private String sunset;
    private long sunsetEpoch;
    private double moonphase;
  }

  @Data
  @Getter
  @Setter
  @Accessors(chain = true)
  public static class Day {
    private String datetime;
    private long datetimeEpoch;
    private double tempmax;
    private double tempmin;
    private double temp;
    private double feelslikemax;
    private double feelslikemin;
    private double feelslike;
    private double dew;
    private double humidity;
    private double precip;
    private double precipprob;
    private double precipcover;
    private Object preciptype;
    private double snow;
    private double snowdepth;
    private double windgust;
    private double windspeed;
    private double winddir;
    private double pressure;
    private double cloudcover;
    private double visibility;
    private double solarradiation;
    private double solarenergy;
    private double uvindex;
    private double severerisk;
    private String sunrise;
    private long sunriseEpoch;
    private String sunset;
    private long sunsetEpoch;
    private double moonphase;
    private String conditions;
    private String description;
    private String icon;
    private List<String> stations;
    private String source;
  }
}
