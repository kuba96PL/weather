package pl.wroc.ue.weather.json;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import pl.wroc.ue.weather.http.domain.Coordinates;
import pl.wroc.ue.weather.http.domain.CurrentWeatherResponse;
import pl.wroc.ue.weather.http.domain.WeatherDetails;

public class HttpResponseMapperTest {

  private HttpResponseMapper mapper = new HttpResponseMapper();

  @Test
  public void it_should_parse_json_response_to_domain_object() {
    //  Given
    String response =
        "{'coord':{'lon':139.01,'lat':35.02},'weather':[{'id':800,'main':'Clear','description':'clear sky',"
            + "'icon':'01n'}],'base':'stations','main':{'temp':285.514,'pressure':1013.75,'humidity':100,'temp_min':285.514,"
            + "'temp_max':285.514,'sea_level':1023.22,'grnd_level':1013.75},'wind':{'speed':5.52,'deg':311},'clouds':{'all':0},"
            + "'dt':1485792967,'sys':{'message':0.0025,'country':'JP','sunrise':1485726240,'sunset':1485763863},'id':1907296,'name':'Tawarano','cod':200}";

    //  When
    CurrentWeatherResponse mappedResponse = mapper.parseCurrentWeatherResponse(response);

    //  Then
    assertThat(mappedResponse.getBase()).isEqualTo("stations");
    assertThat(mappedResponse.getCoordinates()).isEqualTo(new Coordinates(139.01, 35.02));
    assertThat(mappedResponse.getWeatherDetails()).isEqualTo(
        new WeatherDetails(
            800,
            "Clear",
            "clear sky",
            "01n")
    );
  }

}