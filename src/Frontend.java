// --== CS400 File Header Information ==--
// Name: Fangjun Zhou
// Email: fzhou48@wisc.edu
// Team: blue
// Role: Front End Developer
// TA: Xi
// Lecturer: Gary Dahl
// Notes to Grader:

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

/**
 * The frond end class
 * @author fangjunzhou
 * @version 1.0
 */
public class Frontend<BackEndType extends BackendInterface> {

  // PUBLIC FIELD

  // PRIVATE FIELD

  // The scanner for user input
  Scanner scanner;
  // The back end class with BackendInterface
  private BackEndType backEnd;
  // The mode stack
  private ModeStack<ModeBehaviour> modeStack;
  // Debug Mode
  private boolean isDebugging = false;

  // CONSTRUCTOR

  /**
   *
   */
  public Frontend(){
    this.backEnd = null;
    this.scanner = new Scanner(System.in);
  }

  /**
   * Frontend constructor with backend
   * @param backEnd the backend pass into the frontend
   */
  public Frontend(BackEndType backEnd){
    this.backEnd = backEnd;
    this.scanner = new Scanner(System.in);
  }

  // MAIN
  /**
   * The main method of frond end
   * @param args
   */
  public static void main(String[] args) {
    // TODO: Change the Generic here after Back End finished!!!!
    try{
      Frontend<Backend> frontEnd = new Frontend<Backend>(new Backend(new FileReader(args[0])));

      frontEnd.initiate();
      frontEnd.mainLoop();
    }catch (FileNotFoundException FNFE){
      System.out.println("FileNotFoundException");
    }catch (IOException IOE){
      System.out.println("IOException");
    }catch (DataFormatException DFE){
      System.out.println("DataFormatException");
    }
  }

  // PUBLIC METHODS

  public BackEndType getBackEnd(){
    return this.backEnd;
  }

  public ModeStack<ModeBehaviour> getModeStack(){
    return this.modeStack;
  }

  /**
   * Get the debug status
   * @return true if is debugging
   */
  public boolean isDebugging(){
    return this.isDebugging;
  }

  /**
   * Set the debug status
   * @param isDebugging the new debug status
   */
  public void setDebugging(boolean isDebugging){
    this.isDebugging = isDebugging;
  }

  /**
   * set the backend of the frontend class
   *
   * @param backend the backend to set
   */
  public void run(Backend backend){
    Frontend<Backend> frontEnd = new Frontend<Backend>(backend);

    frontEnd.initiate();
    frontEnd.mainLoop();
  }

  // PUBLIC LIFE CYCLE METHODS

  /**
   * initiate the mode stack and load BaseMode
   */
  public void initiate(){
    // Print welcome message
    System.out.println("Welcome to Movie Finder");
    System.out.println("This project is made by CG group blue team");
    System.out.println("=======================");

    // initiate the mode stack
    this.modeStack = new ModeStack<>();
    // create a BaseMode
    BaseMode<BackEndType> baseMode = new BaseMode<BackEndType>(this);
    // push the new BaseMode into the stack
    this.modeStack.push(baseMode);
  }

  /**
   * keep looping until the mode stack has been cleared
   */
  public void mainLoop(){
    while (this.modeStack.size() != 0){
      // get the mode on the top of the mode stack
      ModeBehaviour currentMode = this.modeStack.peek();
      // get the user command
      String command = this.scanner.nextLine();
      // pass the command to the current mode
      boolean isContinue = currentMode.update(command);

      // check if the mode need to continue
      if (!isContinue){
        this.modeStack.pop();
      }

      // Debug Mode
      if (this.isDebugging){
        this.modeStack.printStack();
      }
    }
  }

}
