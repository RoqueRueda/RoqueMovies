package movies.rueda.roque.com.roquemovies.themoviedb;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Retrieves information from the movie db API
 * this application is in charge of all movie related
 * operations.
 *
 * @author roquerueda
 * @version 1.0
 * @since 16/09/17
 */
public class TheMovieDbFetcher {

  private static final String TAG = "TheMovieDbFetcher";
  private static final String API_KEY = "1955c1ed51f525946d01daa11acac033";

  /**
   * Gets the bytes response from the given endpoint
   * @param urlEndPoint String url to connect to
   * @return Bytes response from the connection
   * @throws IOException if the response code is not OK
   */
  public byte[] getUrlDataBytes(String urlEndPoint) throws IOException {
    // Creates the url
    URL url = new URL(urlEndPoint);
    // Connects to the url.
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setReadTimeout(10000 /* milliseconds */);
    connection.setConnectTimeout(15000 /* milliseconds */);
    connection.setRequestMethod("GET");
    connection.addRequestProperty("Accept", "application/json"); // Required to get TMDB to play nicely.
    connection.setDoInput(true);
    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      InputStream in = connection.getInputStream();
      // If our response is not OK then throw an exception
      // to inform that something wrong happen
      if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
        throw new IOException(connection.getResponseMessage() +
                ": with " + urlEndPoint);
      }

      // The connection is ok, we should read it.
      int bytesRead = 0;
      byte[] buffer = new byte[1024];
      // Read from the connection.
      while ((bytesRead = in.read(buffer)) > 0) {
        out.write(buffer, 0, bytesRead);
      }
      out.close();
      return out.toByteArray();
    } finally {
      connection.disconnect();
    }
  }

  /**
   * Gets the data from the endpoint as a String object
   * @param urlEndPoint Url to connect to
   * @return String response from the endpoint
   * @throws IOException Exception in case of invalid status.
   */
  public String getUrlDataString(String urlEndPoint) throws IOException {
    return new String(getUrlDataBytes(urlEndPoint));
  }

  public void fetchMovie(int movieId) {
    try {
      String url = Uri.parse("http://api.themoviedb.org/3/movie/" + movieId)
              .buildUpon()
              .appendQueryParameter("api_key", API_KEY)
              .build().toString();

      String jsonString = getUrlDataString(url);
      Log.i(TAG, "Received JSON: " + jsonString);
    } catch (IOException ioe) {
      Log.e(TAG, "Failed to fetch items", ioe);
    }
  }


}
