// --== CS400 File Header Information ==--
// Name: Fangjun Zhou
// Email: fzhou48@wisc.edu
// Team: blue
// Role: Front End Developer
// TA: Xi
// Lecturer: Gary Dahl
// Notes to Grader:

/**
 * This is the interface for mode behaviour
 * @author fangjunzhou
 * @version 1.0
 */
public class ModeBehaviour implements IModeStackElement{
  /**
   * Call this method when the mode is pushed into the stack for the first time
   */
  public void start(){

  }

  /**
   * Call this method every loop
   *
   * @param command the command passed into the mode
   * @return true when te mode need to continue,
   * false when the mode need to pop
   */
  public boolean update(String command){
    return false;
  }

  @Override public void onPush() {

  }

  @Override public void onPop() {

  }

  @Override public void onPause() {

  }

  @Override public void onResume() {

  }
}
