package pl.wroc.ue.weather.utils;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TemperatureConverter {

  public double convertFahrenheitToCelsius(final double fahrenheitTemp) {
      return (fahrenheitTemp - 32) / 1.8;
  }

  public static String tempToStringWithRound(final double temperature) {
    return format(ENGLISH, "%.2f", temperature);
  }
}
