package movies.rueda.roque.com.roquemovies.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import movies.rueda.roque.com.roquemovies.R;

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

  public boolean getPopularMovies() {
    SharedPreferences preferences = getActivity().
            getSharedPreferences(MOVROQ_PREF, Context.MODE_PRIVATE);
    return preferences.getBoolean(LIST_POPULAR, true);
  }

  public static MovieFragmentList newInstance() {
    MovieFragmentList fragment = new MovieFragmentList();
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setRetainInstance(true);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_movie_list, container, false);
    mMoviesRecyclerView = (RecyclerView) v.findViewById(R.id.movie_list);
    mMoviesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    return v;
  }
}
