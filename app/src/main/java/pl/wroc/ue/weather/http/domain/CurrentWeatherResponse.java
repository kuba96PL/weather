package pl.wroc.ue.weather.http.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CurrentWeatherResponse {

  private final String base;
  private final Coordinates coordinates;
  private final WeatherDetails weatherDetails;
}
