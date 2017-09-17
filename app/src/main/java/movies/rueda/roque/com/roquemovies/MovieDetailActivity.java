package movies.rueda.roque.com.roquemovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import movies.rueda.roque.com.roquemovies.fragments.MovieFragment;

public class MovieDetailActivity extends BaseActivity {

  public static final String MOVIE_ID = "movies.rueda.roque.com.roquemovies.movie_id";

  public static Intent newIntent(Context packageContext, int movieId) {
    Intent intent = new Intent(packageContext, MovieDetailActivity.class);
    intent.putExtra(MOVIE_ID, movieId);
    return intent;
  }

  @Override
  protected Fragment getFragment() {
    int movieId = getIntent().getIntExtra(MOVIE_ID, 0);
    return MovieFragment.newInstance(movieId);
  }
}
