package pl.wroc.ue.weather.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import pl.wroc.ue.weather.http.domain.Coordinates;
import pl.wroc.ue.weather.http.domain.CurrentWeatherResponse;
import pl.wroc.ue.weather.http.domain.WeatherDetails;

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
    final WeatherDetails details = new WeatherDetails(
        jsonObject.getAsJsonObject("weather").get("id").getAsInt(),
        jsonObject.getAsJsonObject("weather").get("main").getAsString(),
        jsonObject.getAsJsonObject("weather").get("description").getAsString(),
        jsonObject.getAsJsonObject("weather").get("icon").getAsString()
    );


    return new CurrentWeatherResponse(base, coordinates, details);
  }
}
