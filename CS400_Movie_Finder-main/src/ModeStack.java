// --== CS400 File Header Information ==--
// Name: Fangjun Zhou
// Email: fzhou48@wisc.edu
// Team: blue
// Role: Front End Developer
// TA: Xi
// Lecturer: Gary Dahl
// Notes to Grader:

import java.util.LinkedList;

/**
 * The ModeStack class
 * @author fangjunzhou
 * @version 1.0
 */
public class ModeStack<ModeType extends IModeStackElement> implements IModeStack<ModeType>{

  // PRIVATE FIELD
  private LinkedList<ModeType> stackList;

  // CONSTRUCTOR
  public ModeStack(){
    this.stackList = new LinkedList<>();
  }

  // IModeStack INTERFACE

  /**
   * Push a new mode into the stack
   * @param mode the mode to push
   */
  @Override public void push(ModeType mode) {
    // Pause the current mode
    if (this.stackList.size() != 0){
      this.peek().onPause();
    }
    // Add the new mode into the stack
    this.stackList.addFirst(mode);
    // Call the onPush function of the new mode
    mode.onPush();
  }

  /**
   * Pop the mode on the top of the stack
   * @return the mode popped, null when there's no more mode
   */
  @Override public ModeType pop() {
    // if the stack is already empty
    if (this.stackList.size() == 0){
      return null;
    }

    // call the onPop function of the current mode
    ModeType removedMode = this.stackList.remove(0);
    removedMode.onPop();

    // call the onResume function of the current mode
    if (this.stackList.size() != 0){
      this.peek().onResume();
    }

    return removedMode;
  }

  /**
   * peek the mode on the top of the stack, but not remove it.
   * @return
   */
  @Override public ModeType peek() {
    return this.stackList.get(0);
  }

  /**
   * Get the size of the mode stack
   * @return the siz of the stack
   */
  @Override public int size() {
    return this.stackList.size();
  }

  // DEBUG

  public void printStack(){
    System.out.println("Stack element:");
    for (ModeType mode : this.stackList){
      System.out.print(mode.toString() + "==>");
    }
    System.out.println();
  }
}
