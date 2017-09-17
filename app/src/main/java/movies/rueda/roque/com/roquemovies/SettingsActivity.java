package movies.rueda.roque.com.roquemovies;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import movies.rueda.roque.com.roquemovies.fragments.SettingsFragment;

/**
 * Activity used to display the settings to the user.
 *
 * @author roquerueda
 * @version 1.0
 * @since 17/09/17
 */
public class SettingsActivity extends BaseActivity {

  public static Intent newIntent(Context packageContext) {
    Intent intent = new Intent(packageContext, SettingsActivity.class);
    return intent;
  }

  @Override
  protected Fragment getFragment() {
    return SettingsFragment.newInstance();
  }
}
