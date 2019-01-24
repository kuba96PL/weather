package pl.wroc.ue.weather.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import pl.wroc.ue.weather.R;

public class MainActivity extends AppCompatActivity {

  private ViewPager viewPager;

  public static class CustomPagerAdapter extends FragmentPagerAdapter {

    private final static int NUM_ITEMS = 3;

    public CustomPagerAdapter(FragmentManager fragmentManager) {
      super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
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
    public CharSequence getPageTitle(int position) {
      return "TAB " + position;
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    viewPager = findViewById(R.id.viewPager);
    viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
    viewPager.setCurrentItem(1);
  }
}