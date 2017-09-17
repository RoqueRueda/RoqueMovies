package movies.rueda.roque.com.roquemovies.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import movies.rueda.roque.com.roquemovies.R;
import movies.rueda.roque.com.roquemovies.model.Movie;
import movies.rueda.roque.com.roquemovies.themoviedb.TheMovieDbFetcher;

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

  /////////////////////////////////
  // Helper function for fragment
  // arguments
  /////////////////////////////////

  private static final String ARG_MOVIE_ID = "movie_id";

  /**
   * @return Returns a instance of this fragment with the
   * given arguments
   */
  public static MovieFragment newInstance(int movieId) {
    // Create the arguments.
    Bundle args = new Bundle();
    args.putInt(ARG_MOVIE_ID, movieId);

    // Create the new instance and attach the arguments.
    MovieFragment fragment = new MovieFragment();
    fragment.setArguments(args);
    return fragment;
  }

  /////////////////////////////////
  // Class to get the movies in
  // background
  /////////////////////////////////

  /**
   * AsyncTask to get the movie data in background
   */
  private class FetchMovieTask extends AsyncTask<Integer,Void,Void> {

    private static final String TAG = "FetchMovieTask";

    @Override
    protected Void doInBackground(Integer... params) {
      TheMovieDbFetcher fetcher = new TheMovieDbFetcher();
      // Validate the parameters
      if (params != null && params.length > 0) {
        fetcher.fetchMovie(params[0]);
      }

      return null;
    }
  }

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
    fetchMovie();

    return v;
  }

  private void fetchMovie() {
    // Check if the Network Connection is alive and connected.
//    ConnectivityManager connMrg = (ConnectivityManager)
//            getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//    NetworkInfo networkInfo = connMrg.getActiveNetworkInfo();
//    if (networkInfo != null && networkInfo.isConnected()) {
      int movieId = getArguments().getInt(ARG_MOVIE_ID);
      new FetchMovieTask().execute(movieId);
//    } else {
//      // Display not connected
//    }

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
