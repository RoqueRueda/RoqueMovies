package movies.rueda.roque.com.roquemovies.model;

/**
 * Represents a movie from the movie db api.
 *
 * @author roquerueda
 * @version 1.0
 * @since 16/09/17
 */
public class Movie {

  private int id;
  private String title;
  private String posterPath;
  private float voteAvg;
  private int voteCount;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public float getVoteAvg() {
    return voteAvg;
  }

  public void setVoteAvg(float voteAvg) {
    this.voteAvg = voteAvg;
  }

  public int getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(int voteCount) {
    this.voteCount = voteCount;
  }
}
