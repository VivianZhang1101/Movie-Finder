// --== CS400 File Header Information ==--
// Name: Fangjun Zhou
// Email: fzhou48@wisc.edu
// Team: blue
// Role: Front End Developer
// TA: Xi
// Lecturer: Gary Dahl
// Notes to Grader:

import java.util.List;

/**
 * This is the BaseMode
 */
public class BaseMode<BackEndType extends BackendInterface> extends ModeBehaviour{

  // PRIVATE FIELD

  // The Front End object
  private Frontend<BackEndType> frontEnd;

  // the current index
  private int index;

  // CONSTRUCTOR

  public BaseMode(Frontend<BackEndType> frontEnd){
    this.frontEnd = frontEnd;
    this.index = 0;
  }

  // ModeBehaviour

  /**
   * Call this method at the first loop
   */
  @Override public void start() {
    // run the update once(for the first time)
    update("1");
  }

  /**
   * Call this method every loop
   *
   * @param command the command passed into the mode
   * @return true when te mode need to continue,
   * false when the mode need to pop
   */
  @Override public boolean update(String command) {
    if (command.equals("x")){
      // exit the mode
      return false;
    }else if (command.equals("g")){
      // open genre mode
      // push the Genre Mode into the mode stack
      GenreMode<BackEndType> genreMode = new GenreMode<>(frontEnd);
      this.frontEnd.getModeStack().push(genreMode);
    }else if (command.equals("r")){
      // open rating mode
      // push the Rating Mode into the mode stack
      RatingMode<BackEndType> ratingMode = new RatingMode<>(frontEnd);
      this.frontEnd.getModeStack().push(ratingMode);
    }else if (command.equals("debugMode on")){
      // turn on debug mode
      System.out.println("Debug Mode On");
      this.frontEnd.setDebugging(true);
    }else if (command.equals("debugMode off")){
      // turn off the debug mode
      System.out.println("Debug Mode Off");
      this.frontEnd.setDebugging(false);
    }else {
      // process scrolling
      try {
        this.index = Integer.parseInt(command) - 1;
        if (this.index >= this.frontEnd.getBackEnd().getNumberOfMovies() && this.index != 0){
          System.out.println("Rank index out of range, please enter a number from 1 to " +
              this.frontEnd.getBackEnd().getNumberOfMovies() + ".");
          this.index = this.frontEnd.getBackEnd().getNumberOfMovies() - 1;
        }else if (this.index < 0){
          System.out.println("Rank index out of range, please enter a number from 1 to " +
              this.frontEnd.getBackEnd().getNumberOfMovies() + ".");
          this.index = 0;
        }else {
          // Show if in debug mode
          System.out.println("Get three movies from rank: " + (this.index+1));
          System.out.println("Total movie get: " + this.frontEnd.getBackEnd().getNumberOfMovies());
        }
      }catch (NumberFormatException NFE){
        System.out.println("If you want to scroll your view, enter a rank number");
      }

      // get movie from backend
      List<MovieInterface> threeMovies = this.frontEnd.getBackEnd().getThreeMovies(index);

      if (threeMovies != null){
        // List all the three movies
        for (MovieInterface movie : threeMovies){
          // TODO: print all the three movies
          System.out.println("Title: " + movie.getTitle());
          System.out.println("Year: " + movie.getYear());
          System.out.println("Genres: " + movie.getGenres());
          System.out.println("Director: " + movie.getDirector());
          System.out.println("Description: " + movie.getDescription());
          System.out.println("Average Vote: " + movie.getAvgVote());
          System.out.println("===================================");
        }
      }else {
        System.out.println("Back End Interface error, cannot get movies");
      }
    }
    return true;
  }

  // IModeStack INTERFACE

  /**
   * Call this method when the mode is pushed into the stack
   */
  @Override public void onPush() {
    System.out.println("You are currently in Base Mode");
    System.out.println("Enter 'x' to exit");
    System.out.println("Enter 'g' to open genre selection mode");
    System.out.println("Enter 'r' to open ratings selection mode");
    System.out.println("Enter a integer to get three movie from the specific rank");
    start();
  }

  /**
   * Call this method when the mode is popped from the stack
   */
  @Override public void onPop() {
    System.out.println("Exiting Base Mode");
  }

  /**
   * Call this method when the mode is being paused(there's a new mode on top of current mode)
   */
  @Override public void onPause() {
    System.out.println("Base Mode is now running in the background");
  }

  /**
   * Call this method when the mode is resumed(the mode on top of it is being poped)
   */
  @Override public void onResume() {
    System.out.println("You are currently in Base Mode");
    System.out.println("Enter 'x' to exit");
    System.out.println("Enter 'g' to open genre selection mode");
    System.out.println("Enter 'r' to open ratings selection mode");
    System.out.println("Enter a integer to get three movie from the specific rank");
    start();
  }

  // TO STRING

  @Override public String toString() {
    return "Base Mode";
  }
}
