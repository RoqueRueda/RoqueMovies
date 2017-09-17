package movies.rueda.roque.com.roquemovies.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import movies.rueda.roque.com.roquemovies.R;

/**
 * Settings activity to update the user preferences
 *
 * @author roquerueda
 * @version 1.0
 * @since 17/09/17
 */
public class SettingsFragment extends Fragment implements View.OnClickListener{

  private RadioButton mPopular;
  private RadioButton mTopRated;

  public static SettingsFragment newInstance() {
    SettingsFragment fragment = new SettingsFragment();
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Display the up button
    ((AppCompatActivity)getActivity()).getSupportActionBar()
            .setDisplayHomeAsUpEnabled(true);
    ((AppCompatActivity)getActivity()).getSupportActionBar()
            .setDisplayShowHomeEnabled(true);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_settings, container, false);
    // Get references to the views
    wireUpViews(v);
    return v;
  }

  private void wireUpViews(View rootView) {
    mPopular = rootView.findViewById(R.id.popular);
    mPopular.setOnClickListener(this);
    mTopRated = rootView.findViewById(R.id.top_rated);
    mTopRated.setOnClickListener(this);
    boolean popular = MovieFragmentList.getPopularMovies(getActivity());
    if (popular) {
      mPopular.setChecked(true);
      mTopRated.setChecked(false);
    } else {
      mTopRated.setChecked(true);
      mPopular.setChecked(false);
    }
  }

  @Override
  public void onClick(View view) {
    boolean checked = ((RadioButton) view).isChecked();
    switch (view.getId()) {
      case R.id.popular:
        if (checked) {
          MovieFragmentList.savePopularMovies(getActivity(), true);
        }
        break;
      case R.id.top_rated:
        if (checked) {
          MovieFragmentList.savePopularMovies(getActivity(), false);
        }
        break;
    }
  }
}
