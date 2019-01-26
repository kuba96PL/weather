package pl.wroc.ue.weather.http.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class WeatherDetails {
  private final int id;
  private final String weatherType;
  private final String weatherDescription;
  private final String icon;
}
