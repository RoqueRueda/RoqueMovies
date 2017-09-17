package movies.rueda.roque.com.roquemovies;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import movies.rueda.roque.com.roquemovies.fragments.MovieFragment;
import movies.rueda.roque.com.roquemovies.fragments.MovieFragmentList;

public class MovieListActivity extends BaseActivity {


  @Override
  protected int getLayoutResId() {
    return R.layout.activity_masterdetail;
  }

  @Override
  protected Fragment getFragment() {
    return MovieFragmentList.newInstance();
  }
}
