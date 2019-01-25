package pl.wroc.ue.weather.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import pl.wroc.ue.weather.http.domain.Clouds;
import pl.wroc.ue.weather.http.domain.Coordinates;
import pl.wroc.ue.weather.http.domain.CurrentWeatherResponse;
import pl.wroc.ue.weather.http.domain.WeatherData;
import pl.wroc.ue.weather.http.domain.WeatherDetails;
import pl.wroc.ue.weather.http.domain.Wind;

public class CurrentWeatherResponseJsonDeserializer implements JsonDeserializer<CurrentWeatherResponse> {

  @Override
  public CurrentWeatherResponse deserialize(
      final JsonElement jsonElement,
      final Type type,
      final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    final JsonObject jsonObject = jsonElement.getAsJsonObject();

    final String base = jsonObject.get("base").getAsString();
    final Coordinates coordinates = new Coordinates(
        jsonObject.getAsJsonObject("coord").get("lon").getAsDouble(),
        jsonObject.getAsJsonObject("coord").get("lat").getAsDouble()
    );

    final JsonObject weatherDetails = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject();

    final WeatherDetails details = new WeatherDetails(
        weatherDetails.get("id").getAsInt(),
        weatherDetails.get("main").getAsString(),
        weatherDetails.get("description").getAsString(),
        weatherDetails.get("icon").getAsString()
    );

    final WeatherData weatherData = new WeatherData(
        jsonObject.getAsJsonObject("main").get("temp").getAsDouble(),
        jsonObject.getAsJsonObject("main").get("pressure").getAsDouble(),
        jsonObject.getAsJsonObject("main").get("humidity").getAsInt()
    );
    final Wind wind = new Wind(
        jsonObject.getAsJsonObject("wind").get("speed").getAsDouble(),
        jsonObject.getAsJsonObject("wind").get("deg").getAsInt()
    );
    final Clouds clouds = new Clouds(
        jsonObject.getAsJsonObject("clouds").get("all").getAsInt()
    );
    final DateTime forecastDateTime = new DateTime(jsonObject.get("dt").getAsLong() * 1000,
        DateTimeZone.forID("+01:00"));
    final DateTime sunrise = new DateTime(jsonObject.getAsJsonObject("sys").get("sunrise").getAsLong() * 1000,
        DateTimeZone.forID("+01:00"));
    final DateTime sunset = new DateTime(jsonObject.getAsJsonObject("sys").get("sunset").getAsLong() * 1000,
        DateTimeZone.forID("+01:00"));
    final String countryCode = jsonObject.getAsJsonObject("sys").get("country").getAsString();
    final String city = jsonObject.get("name").getAsString();

    return new CurrentWeatherResponse(
        base,
        coordinates,
        details,
        weatherData,
        wind,
        clouds,
        forecastDateTime,
        sunrise,
        sunset,
        countryCode,
        city);
  }
}
