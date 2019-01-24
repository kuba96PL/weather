package pl.wroc.ue.weather.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.wroc.ue.weather.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AirPollutionFragment extends Fragment {


  public AirPollutionFragment() {}


  @Override
  public View onCreateView(
      final LayoutInflater inflater,
      final ViewGroup container,
      final Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_air_pollution, container, false);
  }

}
