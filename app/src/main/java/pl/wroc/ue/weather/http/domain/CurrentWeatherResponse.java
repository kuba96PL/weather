package pl.wroc.ue.weather.http.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.joda.time.DateTime;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CurrentWeatherResponse {

  private final String base;
  private final Coordinates coordinates;
  private final WeatherDetails weatherDetails;
  private final WeatherData weatherData;
  private final Wind wind;
  private final Clouds clouds;
  private final DateTime forecastDateTime;
  private final DateTime sunrise;
  private final DateTime sunset;
  private final String countryCode;
  private final String city;
}
