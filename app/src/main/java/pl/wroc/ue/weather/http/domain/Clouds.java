package pl.wroc.ue.weather.http.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Clouds {

  private final int cloudiness;
}
