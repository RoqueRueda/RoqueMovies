package movies.rueda.roque.com.roquemovies;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import movies.rueda.roque.com.roquemovies.fragments.MovieFragment;

public class MovieListActivity extends FragmentActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_list);

    // Get fragment manager
    FragmentManager fm = getSupportFragmentManager();
    // Check for a current fragment
    Fragment fragment = fm.findFragmentById(R.id.fragment_container);

    if (fragment == null) {
      fragment = new MovieFragment();
      fm.beginTransaction()
              .add(R.id.fragment_container, fragment)
              .commit();
    }
  }
}
