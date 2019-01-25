package pl.wroc.ue.weather.http.client;

import android.util.Log;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.wroc.ue.weather.json.HttpResponseMapper;

public class OpenWeatherMapClient {

  public void fetchCurrentWeather() {
    final OkHttpClient client = new OkHttpClient();

    final Request fetchCurrentWeatherRequest = new Request.Builder()
        .url(
            "https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22")
        .build();

    client.newCall(fetchCurrentWeatherRequest).enqueue(new Callback() {
      @Override
      public void onFailure(final Call call, final IOException e) {
        e.printStackTrace();
      }

      @Override
      public void onResponse(final Call call, final Response response) throws IOException {
        final HttpResponseMapper mapper = new HttpResponseMapper();
        if (response.isSuccessful() && response.body() != null) {
          Log.i("WEATHER!!!", mapper.parseCurrentWeatherResponse(response.body().string()).toString());
        }
      }
    });
  }
}
