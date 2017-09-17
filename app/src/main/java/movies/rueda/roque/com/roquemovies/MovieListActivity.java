package movies.rueda.roque.com.roquemovies;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import movies.rueda.roque.com.roquemovies.fragments.MovieFragment;
import movies.rueda.roque.com.roquemovies.fragments.MovieFragmentList;
import movies.rueda.roque.com.roquemovies.model.Movie;

public class MovieListActivity extends BaseActivity implements MovieFragmentList.Callbacks {

  @Override
  protected int getLayoutResId() {
    return R.layout.activity_masterdetail;
  }

  @Override
  protected Fragment getFragment() {
    return MovieFragmentList.newInstance();
  }

  @Override
  public void onMovieSelected(Movie m) {
    // Check if the details container exist to launch the activity or not.
    if (findViewById(R.id.detail_fragment_container) == null) {
      Intent intent = MovieDetailActivity.newIntent(this, m.getId());
      startActivity(intent);
    } else {
      Fragment newDetail = MovieFragment.newInstance(m.getId());
      getSupportFragmentManager().beginTransaction()
              .replace(R.id.detail_fragment_container, newDetail)
              .commit();
    }
  }
}
