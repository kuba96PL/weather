package pl.wroc.ue.weather.fragments;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import pl.wroc.ue.weather.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentWeatherFragment extends Fragment {

  private RelativeLayout currentWeatherLayout;
  private AnimationDrawable animationDrawable;

  public CurrentWeatherFragment() {
  }

  @Override
  public View onCreateView(
      final LayoutInflater inflater,
      final ViewGroup container,
      final Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_current_weather, container, false);
  }

  @Override
  public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    currentWeatherLayout = getView().findViewById(R.id.currentWeatherLayout);
    animationDrawable = (AnimationDrawable) currentWeatherLayout.getBackground();
  }

  @Override
  public void onResume() {
    super.onResume();
    animationDrawable.start();
  }

  @Override
  public void onPause() {
    super.onPause();
    animationDrawable.stop();
  }
}
