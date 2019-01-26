package pl.wroc.ue.weather.fragments;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import pl.wroc.ue.weather.R;
import pl.wroc.ue.weather.http.client.OpenWeatherMapClient;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, LocationListener {

  private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
  private ViewPager viewPager;

  private Location location;
  private GoogleApiClient googleApiClient;

  private LocationRequest locationRequest;
  private long UPDATE_INTERVAL = 1000;
  private long FASTEST_INTERVAL = 1000;


  public static class CustomPagerAdapter extends FragmentPagerAdapter {

    private final static int NUM_ITEMS = 3;

    public CustomPagerAdapter(final FragmentManager fragmentManager) {
      super(fragmentManager);
    }

    @Override
    public Fragment getItem(final int position) {
      switch (position) {
        case 0:
          return new WeatherForecastFragment();
        case 1:
          return new CurrentWeatherFragment();
        case 2:
          return new AirPollutionFragment();
        default:
          return null;
      }
    }

    @Override
    public int getCount() {
      return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(final int position) {
      return "TAB " + position;
    }
  }

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    viewPager = findViewById(R.id.viewPager);
    viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
    viewPager.setCurrentItem(1);

      requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, 0);

      googleApiClient = new GoogleApiClient.Builder(this)
          .addApi(LocationServices.API)
          .addConnectionCallbacks(this)
          .addOnConnectionFailedListener(this)
          .build();

    OpenWeatherMapClient client = new OpenWeatherMapClient();
    client.fetchCurrentWeather();
  }

  @Override
  protected void onStart() {
    super.onStart();
    if (googleApiClient != null) {
      googleApiClient.connect();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (!checkPlayServices()) {
      Toast.makeText(this, "Please install Google Play services", Toast.LENGTH_LONG).show();
    }
  }

  @Override
  public void onConnected(@Nullable Bundle bundle) {
    if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        || ActivityCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, 0);
    }
    location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

    startLocationUpdates();
  }

  @Override
  public void onConnectionSuspended(int i) {

  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

  }

  @Override
  public void onLocationChanged(Location location) {
    if (location == null) {
      Log.i("ERROR", "Error while trying to get user location");
    } else {
      Toast.makeText(this, "Location: " + String.valueOf(location.getLongitude()) + " " + String.valueOf(location.getLatitude()), Toast.LENGTH_LONG).show();
    }
  }

  private boolean checkPlayServices() {
    GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
    int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
    if (resultCode != ConnectionResult.SUCCESS) {
      if (apiAvailability.isUserResolvableError(resultCode)) {
        apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
            .show();
      } else {
        finish();
      }
      return false;
    }
    return true;
  }

  protected void startLocationUpdates() {
    locationRequest = new LocationRequest();
    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    locationRequest.setInterval(UPDATE_INTERVAL);
    locationRequest.setFastestInterval(FASTEST_INTERVAL);
    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      Toast.makeText(getApplicationContext(), "Enable Permissions", Toast.LENGTH_LONG).show();
    }

    LocationServices.FusedLocationApi.requestLocationUpdates(
        googleApiClient, locationRequest, this);


  }
}
