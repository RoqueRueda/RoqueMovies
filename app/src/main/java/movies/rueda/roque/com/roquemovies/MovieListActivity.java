package movies.rueda.roque.com.roquemovies;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import movies.rueda.roque.com.roquemovies.fragments.MovieFragment;

public class MovieListActivity extends BaseActivity {


  @Override
  protected Fragment getFragment() {
    int movieId = 550;
    return MovieFragment.newInstance(movieId);
  }
}
