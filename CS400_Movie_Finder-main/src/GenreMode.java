// --== CS400 File Header Information ==--
// Name: Fangjun Zhou
// Email: fzhou48@wisc.edu
// Team: blue
// Role: Front End Developer
// TA: Xi
// Lecturer: Gary Dahl
// Notes to Grader:

import java.util.ArrayList;
import java.util.List;

/**
 * This is the GenreMode
 * @param <BackEndType>
 */
public class GenreMode <BackEndType extends BackendInterface> extends ModeBehaviour{

  // PRIVATE FIELD

  // The Front End object
  private Frontend<BackEndType> frontEnd;
  // All the genres from backend
  private List<String> allGenres;
  // All the selected genres from backend
  private List<String> selectedGenres;

  // CONSTRUCTOR

  public GenreMode(Frontend<BackEndType> frontEnd){
    this.frontEnd = frontEnd;
    this.allGenres = new ArrayList<>();
    this.selectedGenres = new ArrayList<>();
  }

  // ModeBehaviour

  /**
   * Call this method at the first loop
   */
  @Override public void start() {
    // load all the available genres from frontend
    this.allGenres = this.frontEnd.getBackEnd().getAllGenres();
    // run the update once(for the first time)
    printGenreStatus();
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
    }else{
      // Try to parse the user input into genre
      try {
        // get the target index
        int operationIndex = Integer.parseInt(command);
        // check if the index is out of range
        if (operationIndex > this.allGenres.size() || operationIndex < 1){
          System.out.println("Choose index out of range!");
          System.out.println("You can only choose index from 1 to " + this.allGenres.size() + ".");
        }else {
          operateGenre(operationIndex - 1);
        }

      }catch (NumberFormatException NFE){
        System.out.println("If you want to select or deselect a genre, enter the index of that genre");
      }

      printGenreStatus();
    }
    return true;
  }

  // PRIVATE FIELD

  private void printGenreStatus(){
    // update selected genres
    this.selectedGenres = this.frontEnd.getBackEnd().getGenres();

    if (this.frontEnd.isDebugging()){
      System.out.print("All Genres: ");
      System.out.println(this.allGenres);
      System.out.print("Selected Genres: ");
      System.out.println(this.selectedGenres);
    }

    // print all the genre and their status
    int i = 1;
    for (String genre : this.allGenres){
      // selected
      if (this.selectedGenres.contains(genre)){
        System.out.print("\033[33;4m" + "√" + "\033[0m");
        System.out.print(i + ")");
        System.out.print(genre);
        System.out.println();
      }
      // unselected
      else {
        System.out.print("\033[32;4m" + "x" + "\033[0m");
        System.out.print(i + ")");
        System.out.print(genre);
        System.out.println();
      }

      i ++;
    }
  }

  private void operateGenre(int index){
    // Get the target genre
    String targetGenre = this.allGenres.get(index);

    // if the specific genre is selected
    if (this.selectedGenres.contains(targetGenre)){
      this.frontEnd.getBackEnd().removeGenre(targetGenre);
    }else {
      // add the genre to the list
      this.frontEnd.getBackEnd().addGenre(targetGenre);
    }
  }

  // IModeStack INTERFACE

  /**
   * Call this method when the mode is pushed into the stack
   */
  @Override public void onPush() {
    System.out.println("You are currently in Genre Select Mode");
    System.out.println("Enter 'x' to go back to Base Mode");
    System.out.println("Enter the number of Genre to select or deselect the genre");
    start();
  }

  /**
   * Call this method when the mode is popped from the stack
   */
  @Override public void onPop() {
    System.out.println("Exiting Genre Selecting Mode");
  }

  /**
   * Call this method when the mode is being paused(there's a new mode on top of current mode)
   */
  @Override public void onPause() {
    System.out.println("Genre Selecting Mode is now running in the background");
  }

  /**
   * Call this method when the mode is resumed(the mode on top of it is being poped)
   */
  @Override public void onResume() {
    System.out.println("You are currently in Genre Select Mode");
    System.out.println("Enter 'x' to go back to Base Mode");
    System.out.println("Enter the number of Genre to select or deselect the genre");
    start();
  }

  // TO STRING

  @Override public String toString() {
    return "Genre Select Mode";
  }

}
