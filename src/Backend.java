// --== CS400 File Header Information ==--
// Name: Vivian Zhang
// Email: wzhang556@wisc.edu
// Team: blue
// Role: Back End Developer
// TA: Xi
// Lecturer: Gary Dahl
// Notes to Grader:
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

/**
 * this class manage the backend of the Movei_Finder project
 * 
 * @author zhang
 *
 */
public class Backend implements BackendInterface {
  private List<MovieInterface> data;
  private List<String> genresList;
  private List<String> avgRatingsList;
  private List<String> allGenresList;
  private List<MovieInterface> threeMoviesList;
  HashTableMap<String, ArrayList<MovieInterface>> map;

  /**
   * command line constructor
   * @param args
   * @throws IOException
   * @throws DataFormatException
   */
  public Backend(String[] args) throws IOException, DataFormatException {
    genresList = new ArrayList<String>();
    avgRatingsList = new ArrayList<String>();
    threeMoviesList = new ArrayList<MovieInterface>();
    allGenresList = new ArrayList<String>();
    MovieDataReader readData = new MovieDataReader();
    data = readData.readDataSet(new FileReader(args[0]));
    // complete the allGenresList
    for (int i = 0; i < data.size(); i++) {
      for (int j = 0; j < data.get(i).getGenres().size(); j++) {
        if (!allGenresList.contains(data.get(i).getGenres().get(j))) {
          allGenresList.add(data.get(i).getGenres().get(j));
        }
      }
    }

    // map the movie list into the hashmap
    map = new HashTableMap<String, ArrayList<MovieInterface>>(100);
    String key = null;
    ArrayList<MovieInterface> movieType = new ArrayList<MovieInterface>();
    for (int i = 0; i < data.size(); i++) {
      for (int j = 0; j < data.get(i).getGenres().size(); j++) {
        key = data.get(i).getGenres().get(j);
        if (!map.containsKey(key)) {
          movieType = getList(key, data);
          map.put(key, movieType);
        }
      }
    }
  }
  /**
   * Constructor for FileReader
   * 
   * @throws FileNotFoundException
   * @throws IOException
   * @throws DataFormatException
   */
  public Backend(Reader readFile)
      throws FileNotFoundException, IOException, DataFormatException {
    // initialize the private field
    genresList = new ArrayList<String>();
    avgRatingsList = new ArrayList<String>();
    threeMoviesList = new ArrayList<MovieInterface>();
    allGenresList = new ArrayList<String>();
    MovieDataReader readData = new MovieDataReader();
    data = readData.readDataSet(readFile);
    // complete the allGenresList
    for (int i = 0; i < data.size(); i++) {
      for (int j = 0; j < data.get(i).getGenres().size(); j++) {
        if (!allGenresList.contains(data.get(i).getGenres().get(j))) {
          allGenresList.add(data.get(i).getGenres().get(j));
        }
      }
    }

    // map the movie list into the hashmap
    map = new HashTableMap<String, ArrayList<MovieInterface>>(100);
    String key = null;
    ArrayList<MovieInterface> movieType = new ArrayList<MovieInterface>();
    for (int i = 0; i < data.size(); i++) {
      for (int j = 0; j < data.get(i).getGenres().size(); j++) {
        key = data.get(i).getGenres().get(j);
        if (!map.containsKey(key)) {
          movieType = getList(key, data);
          map.put(key, movieType);
        }
      }
    }
  }

  /**
   * A private method to find the list of movies according to the given key and data set.
   * 
   * @param key  key of the movies
   * @param data the data set
   * @return movieType a list of movies that have the given key
   */
  private ArrayList<MovieInterface> getList(String key, List<MovieInterface> data) {
    ArrayList<MovieInterface> movieType = new ArrayList<MovieInterface>();
    for (int i = 0; i < data.size(); i++) {
      for (int j = 0; j < data.get(i).getGenres().size(); j++) {
        if (key.equals(data.get(i).getGenres().get(j))) {
          movieType.add(data.get(i));
        }
      }
    }
    return movieType;
  }


  /**
   * add the genre to the genresList if the genre is not null, allGenresList contains this genre,
   * and genresList does not contain this genre yet.
   * 
   * @param genre to be added
   */
  @Override
  public void addGenre(String genre) {
    if (genre != null) {
      if (allGenresList.contains(genre) && !genresList.contains(genre)) {
        genresList.add(genre);
      }
    }
  }

  /**
   * add the rating to the avgRatingsList, if this rating is not null, it is between 0 and 10, and
   * avgRatingsList does not contain this rating yet.
   */
  @Override
  public void addAvgRating(String rating) {
    if (rating != null) {
      if (Integer.parseInt(rating) >= 0 && Integer.parseInt(rating) <= 10) {
        if (!avgRatingsList.contains(rating))
          avgRatingsList.add(rating);
      }
    }
  }

  /**
   * remove this genre, if this genre is not null, and genresList contain this genre
   */
  @Override
  public void removeGenre(String genre) {
    if (genre != null) {
      if (genresList.contains(genre)) {
        genresList.remove(genre);
      }
    }
  }

  /**
   * remove this rating, if this rating is not null, and avgRatingsList contain this rating
   */
  @Override
  public void removeAvgRating(String rating) {
    if (rating != null) {
      if (avgRatingsList.contains(rating)) {
        avgRatingsList.remove(rating);
      }
    }
  }

  /**
   * find the movies according to the selected genres and ratings.
   * 
   * @return finalList the selected movie list
   */
  private List<MovieInterface> finalMovieList() {
    List<MovieInterface> finalList = new ArrayList<MovieInterface>();
    if (genresList.size() == 0) {
      return finalList;
    }
    ArrayList<MovieInterface> allMovies = new ArrayList<MovieInterface>();
    // find all the movies (duplicated) according to the genres in the genresList
    for (int i = 0; i < genresList.size(); i++) {
      allMovies.addAll(map.get(genresList.get(i)));
    }
    // remove the duplicated movies in the allMovies list
    List<MovieInterface> newList = allMovies.stream().distinct().collect(Collectors.toList());
    // if no rating is selected, return the no duplicate movie list (newList)
    if (avgRatingsList.size() == 0) {
      return newList;
    }
    // add the movies which satisfied the rating to the finalList
    for (int i = 0; i < newList.size(); i++) {
      for (int j = 0; j < avgRatingsList.size(); j++) {
        if (newList.get(i).getAvgVote() >= Integer.parseInt(avgRatingsList.get(j))
            && newList.get(i).getAvgVote() <= Integer.parseInt(avgRatingsList.get(j)) + 1) {
          finalList.add(newList.get(i));
          continue;
        }
      }
    }
    Collections.sort(finalList);
    return finalList;
  }

  /**
   * return the number of the movies finded by the selected genres and ratings return finalList.size
   */
  @Override
  public int getNumberOfMovies() {
    List<MovieInterface> finalList = new ArrayList<MovieInterface>();
    finalList = finalMovieList();
    if (finalList == null) {
      return 0;
    }
    return finalList.size();
  }

  /**
   * return all the selected genres
   */
  @Override
  public List<String> getGenres() {
    return genresList;
  }

  /**
   * return all the selected ratings
   */
  @Override
  public List<String> getAvgRatings() {
    return avgRatingsList;
  }

  /**
   * returns a list of three movies starting at (and including) the movie at the startingIndex of
   * the resulting set ordered by average movie rating in descending order. If there are less than
   * three movies left at the startingIndex, the resulting list contains all those movies (the list
   * may be empty).
   */
  @Override
  public List<MovieInterface> getThreeMovies(int startingIndex) {
    // initialize a new threeMoviesList
    threeMoviesList = new ArrayList<MovieInterface>();
    List<MovieInterface> finalList = new ArrayList<MovieInterface>();
    finalList = finalMovieList();
    // if the finalList is empty, return the empty list
    if (finalList.isEmpty()) {
      return finalList;
    } else if (startingIndex >= finalList.size()) { // the starting index is large then
                                                    // finalLize.size
      return null;
    } else if (startingIndex + 1 == finalList.size()) { // the last element in the finalList
      threeMoviesList.add(finalList.get(startingIndex));
      return threeMoviesList;
    } else if (startingIndex + 2 == finalList.size()) { // the last, and second-last element in the
                                                        // finalList
      threeMoviesList.add(finalList.get(startingIndex));
      threeMoviesList.add(finalList.get(startingIndex + 1));
      return threeMoviesList;
    } else { // return 3 movies starting at the startingindex
      threeMoviesList.addAll(finalList.subList(startingIndex, startingIndex + 3));
      return threeMoviesList;
    }
  }

  /**
   * return all the genres of the given data set.
   */
  @Override
  public List<String> getAllGenres() {
    return allGenresList;
  }


}
