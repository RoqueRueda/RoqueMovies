package movies.rueda.roque.com.roquemovies.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import movies.rueda.roque.com.roquemovies.MovieDetailActivity;
import movies.rueda.roque.com.roquemovies.R;
import movies.rueda.roque.com.roquemovies.model.Movie;
import movies.rueda.roque.com.roquemovies.themoviedb.TheMovieDbFetcher;

/**
 * Display a list of Movie posters and its related data from the movie db.
 *
 * @author roquerueda
 * @version 1.0
 * @since 17/09/17
 */
public class MovieFragmentList extends Fragment {

  public static final String MOVROQ_PREF =
          "movies.rueda.roque.com.roquemovies.fragments.SHARED_PREFERENCES";
  public static final String LIST_POPULAR = "popular";

  private RecyclerView mMoviesRecyclerView;
  private MoviesAdapter mAdapter;

  public boolean getPopularMovies() {
    SharedPreferences preferences = getActivity().
            getSharedPreferences(MOVROQ_PREF, Context.MODE_PRIVATE);
    return preferences.getBoolean(LIST_POPULAR, true);
  }

  public static MovieFragmentList newInstance() {
    MovieFragmentList fragment = new MovieFragmentList();
    return fragment;
  }

  /**
   * AsyncTask to get the movie data in background
   */
  private class FetchMovieListTask extends AsyncTask<Boolean, Void, List<Movie>> {

    private static final String TAG = "FetchMovieTask";

    @Override
    protected List<Movie> doInBackground(Boolean... params) {
      // Execute the fetch of the movie in background.
      TheMovieDbFetcher fetcher = new TheMovieDbFetcher();
      // Validate the parameters
      if (params != null && params.length > 0) {
        if (params[0]) {
          return fetcher.fetchPopular();
        } else {
          return fetcher.fetchTopRated();
        }
      }

      return null;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
      // Set the result
      //mMovieList = movies;
      mAdapter = new MoviesAdapter(movies);
      mMoviesRecyclerView.setAdapter(mAdapter);
    }
  }

  private class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView mMoviePoster;
    private Movie mCurrentMovie;

    public MovieHolder(View itemView) {
      super(itemView);
      mMoviePoster = (ImageView) itemView;
      itemView.setOnClickListener(this);
    }

    public void bindMovie(Movie m) {
      String posterUrl = "http://image.tmdb.org/t/p/w185"+m.getPosterPath();
      mCurrentMovie = m;
      Picasso.with(getActivity()).load(posterUrl).into(mMoviePoster);
    }

    @Override
    public void onClick(View view) {
      // Launch another activity when the user press on a poster
      Intent intent = MovieDetailActivity.newIntent(getActivity(), mCurrentMovie.getId());
      startActivity(intent);
    }
  }

  private class MoviesAdapter extends RecyclerView.Adapter<MovieHolder> {

    private List<Movie> mMovies;

    public MoviesAdapter(List<Movie> movies) {
      mMovies = movies;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater li = LayoutInflater.from(getActivity());
      View v = li.inflate(R.layout.movie_item, parent, false);
      return new MovieHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
      Movie m = mMovies.get(position);
      holder.bindMovie(m);
    }

    @Override
    public int getItemCount() {
      return mMovies.size();
    }
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    new FetchMovieListTask().execute(getPopularMovies());
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_movie_list, container, false);
    mMoviesRecyclerView = v.findViewById(R.id.movie_list);

    if (getActivity().getResources().getConfiguration().orientation ==
            Configuration.ORIENTATION_PORTRAIT) {
      mMoviesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    } else {
      mMoviesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }

    return v;
  }
}
