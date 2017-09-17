package movies.rueda.roque.com.roquemovies;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import movies.rueda.roque.com.roquemovies.fragments.MovieFragment;

/**
 * Base class for the application.
 *
 * @author roquerueda
 * @version 1.0
 * @since 16/09/17
 */
public abstract class BaseActivity extends AppCompatActivity {

  protected Toolbar mMoviesToolBar;

  @LayoutRes
  protected int getLayoutResId() {
    return R.layout.activity_movie_list;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResId());

    mMoviesToolBar = (Toolbar) findViewById(R.id.movies_toolbar);
    setSupportActionBar(mMoviesToolBar);
    mMoviesToolBar.setTitle("RoqueMovies");

    // Get fragment manager
    FragmentManager fm = getSupportFragmentManager();
    // Check for a current fragment
    Fragment fragment = fm.findFragmentById(R.id.fragment_container);

    if (fragment == null) {
      fragment = getFragment();
      fm.beginTransaction()
              .add(R.id.fragment_container, fragment)
              .commit();
    }
  }

  protected abstract Fragment getFragment();

}
