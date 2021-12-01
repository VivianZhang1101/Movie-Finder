// --== CS400 File Header Information ==--
// Name: Fangjun Zhou
// Email: fzhou48@wisc.edu
// Team: blue
// Role: Front End Developer
// TA: Xi
// Lecturer: Gary Dahl
// Notes to Grader:

/**
 * The interface for mode stack
 * @author fangjunzhou
 * @version 1.0
 */
public interface IModeStack<ModeType> {

  /**
   * Push a new mode into the stack
   * @param mode the mode to push
   */
  public void push(ModeType mode);

  /**
   * Pop the mode on the top of the stack
   * @return the mode poped
   */
  public ModeType pop();

  /**
   * Peek the mode on the top of the stack, but not remove it.
   * @return
   */
  public ModeType peek();

  /**
   * Get the size of the mode stack
   * @return the siz of the stack
   */
  public int size();
}
