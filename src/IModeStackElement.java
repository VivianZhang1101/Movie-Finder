// --== CS400 File Header Information ==--
// Name: Fangjun Zhou
// Email: fzhou48@wisc.edu
// Team: blue
// Role: Front End Developer
// TA: Xi
// Lecturer: Gary Dahl
// Notes to Grader:

/**
 * The interface for mode stack elements
 * @author fangjunzhou
 * @version 1.0
 */
public interface IModeStackElement {
  /**
   * Call this method when the mode is pushed into the stack
   */
  public void onPush();

  /**
   * Call this method when the mode is popped from the stack
   */
  public void onPop();

  /**
   * Call this method when the mode is being paused(there's a new mode on top of current mode)
   */
  public void onPause();

  /**
   * Call this method when the mode is resumed(the mode on top of it is being poped)
   */
  public void onResume();
}
