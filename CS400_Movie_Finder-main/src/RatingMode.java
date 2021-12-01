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
public class RatingMode <BackEndType extends BackendInterface> extends ModeBehaviour{

  // PRIVATE FIELD

  // The Front End object
  private Frontend<BackEndType> frontEnd;
  // All the selected genres from backend
  private List<String> selectedRatings;

  // CONSTRUCTOR

  public RatingMode(Frontend<BackEndType> frontEnd){
    this.frontEnd = frontEnd;
    this.selectedRatings = new ArrayList<>();
  }

  // ModeBehaviour

  /**
   * Call this method at the first loop
   */
  @Override public void start() {
    // run the update once(for the first time)
    printRatingStatus();
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
      // Try to parse the user input into rating
      try {
        // get the target index
        int operationIndex = Integer.parseInt(command);
        // check if the index is out of range
        if (operationIndex > 10 || operationIndex < 0){
          System.out.println("Choose index out of range!");
          System.out.println("You can only choose rating from 0 to 10.");
        }else {
          operateRating(operationIndex);
        }

      }catch (NumberFormatException NFE){
        System.out.println("If you want to select or deselect a rating, enter that rating");
      }

      printRatingStatus();
    }
    return true;
  }

  // PRIVATE FIELD

  private void printRatingStatus(){
    // update selected genres
    this.selectedRatings = this.frontEnd.getBackEnd().getAvgRatings();

    if (this.frontEnd.isDebugging()){
      System.out.println("Selected Ratings: ");
      System.out.println(this.selectedRatings);
    }

    // print all the genre and their status

    for(int i=0; i<11; i++){
      // selected
      if (this.selectedRatings.contains(Integer.toString(i))){
        System.out.print("\033[33;4m" + "√" + "\033[0m");
        System.out.print(i);
        System.out.println();
      }
      // unselected
      else {
        System.out.print("\033[32;4m" + "x" + "\033[0m");
        System.out.print(i);
        System.out.println();
      }
    }
  }

  private void operateRating(int index){
    // if the specific genre is selected
    if (this.selectedRatings.contains(Integer.toString(index))){
      this.frontEnd.getBackEnd().removeAvgRating(Integer.toString(index));
    }else {
      // add the genre to the list
      this.frontEnd.getBackEnd().addAvgRating(Integer.toString(index));
    }
  }

  // IModeStack INTERFACE

  /**
   * Call this method when the mode is pushed into the stack
   */
  @Override public void onPush() {
    System.out.println("You are currently in Ratings Select Mode");
    System.out.println("Enter 'x' to go back to Base Mode");
    System.out.println("Enter an integer from 1 to 10 to select or deselect a rating");
    start();
  }

  /**
   * Call this method when the mode is popped from the stack
   */
  @Override public void onPop() {
    System.out.println("Exiting Rating Selecting Mode");
  }

  /**
   * Call this method when the mode is being paused(there's a new mode on top of current mode)
   */
  @Override public void onPause() {
    System.out.println("Rating Selecting Mode is now running in the background");
  }

  /**
   * Call this method when the mode is resumed(the mode on top of it is being poped)
   */
  @Override public void onResume() {
    System.out.println("You are currently in Ratings Select Mode");
    System.out.println("Enter 'x' to go back to Base Mode");
    System.out.println("Enter an integer from 1 to 10 to select or deselect a rating");
    start();
  }

  // TO STRING

  @Override public String toString() {
    return "Rating Select Mode";
  }

}
