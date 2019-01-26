package pl.wroc.ue.weather.json;

import com.google.gson.GsonBuilder;
import pl.wroc.ue.weather.http.domain.CurrentWeatherResponse;

public class HttpResponseMapper {

  public CurrentWeatherResponse parseCurrentWeatherResponse(final String response) {
    final GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(CurrentWeatherResponse.class, new CurrentWeatherResponseJsonDeserializer());

    return gsonBuilder.create().fromJson(response, CurrentWeatherResponse.class);
  }
}
