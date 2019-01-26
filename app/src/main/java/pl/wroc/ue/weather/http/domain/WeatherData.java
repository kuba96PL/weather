package pl.wroc.ue.weather.http.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class WeatherData {

  private final double temperature;
  private final double pressure;
  private final int humidity;
}
