package movies.rueda.roque.com.roquemovies.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import movies.rueda.roque.com.roquemovies.R;
import movies.rueda.roque.com.roquemovies.model.Movie;

/**
 * Fragment used to display movie details from The Movie Db.
 *
 * @author roquerueda
 * @version 1.0
 * @since 16/09/17
 */
public class MovieFragment extends Fragment {

  private Movie mMovie;

  /////////////////////////////////
  // Views
  /////////////////////////////////
  private TextView mTitle;
  private ImageView mPoster;
  private TextView mReleaseDate;
  private TextView mDuration;
  private TextView mScore;
  private Button mMarkAsFavorite;
  private TextView mOverview;
  private RecyclerView mTrailers;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mMovie = new Movie();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_movie_detal, container, false);
    // Get the reference to the views.
    wireUpViews(v);

    return v;
  }

  /**
   * Gets the references to the corresponding views
   * @param rootView Root View that contains all the widgets
   */
  private void wireUpViews(View rootView) {
    mTitle = rootView.findViewById(R.id.movie_title);
    mPoster = rootView.findViewById(R.id.movie_poster);
    mReleaseDate = rootView.findViewById(R.id.release_year);
    mDuration = rootView.findViewById(R.id.duration);
    mScore = rootView.findViewById(R.id.score);
    mMarkAsFavorite = rootView.findViewById(R.id.favorite);
    mOverview = rootView.findViewById(R.id.overview);
    mTrailers = rootView.findViewById(R.id.trailers);
  }
}
