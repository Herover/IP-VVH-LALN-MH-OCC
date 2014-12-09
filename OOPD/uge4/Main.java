import java.util.Random;
import java.util.ArrayList;
/**
 * A demonstration of how the top most level of a simple simulater could be build.
 *
 * @author Jon Sporring  {@literal<sporring@diku.dk>}
 * @version 1.0
 * @since 2014-12-01
 */


public class Main {
  public static int STEPS = 1; // The number of steps to simulation
  public static int STONES = 5; // The number of stones to place
  public static int MICE = 3; // The number of mice to simulation

  /**
   * Find an random but empty field on the board
   *
   * @param aBoard the board to search
   * @return the index pair of an empty field
   **/
  public static Position findAvailablePosition(Board aBoard) {
    Random generator = new Random();
    ArrayList<Position> possiblePositions = new ArrayList<Position>();

    // We collect all available fields and pick randomly amoung those.
    for(int i = 0; i < aBoard.getCols(); i++) {
      for(int j = 0; j < aBoard.getRows(); j++) {
          Position aPosition = new Position(i, j);
        if(aBoard.get(aPosition) == null) {
          possiblePositions.add(aPosition);
        }
      }
    }

    if(possiblePositions.size()>0) {
      int i = generator.nextInt(possiblePositions.size());
      return possiblePositions.get(i);
    }
    else {
      Position anErrorPosition = new Position(-1, -1);
      return anErrorPosition;
    }
  }

  /**
   * Simulate mice on a board. Initializes a board, steps and prints
   * the time sequnce of the simulation.
   *
   * @param args a non-used list of command line arguments
   **/
  public static void main(String[] args) {
    Board theBoard = new Board();
    Simulator theSimulator = new Simulator(theBoard);
    Entity anEntity;

    // Entities are created and cyclicly linked to the board: Each
    // entity points at a position on the board, and each position on
    // the board points at an entity
    for(int i = 0; i < STONES; i++) {
      Position aPosition = findAvailablePosition(theBoard);
      anEntity = new Stone(theBoard, aPosition);
      theBoard.add(aPosition, anEntity);
    }

    for(int i = 0; i < MICE; i++) {
      Position aPosition = findAvailablePosition(theBoard);
      anEntity = new Mouse(theBoard, aPosition);
      theBoard.add(aPosition, anEntity);
    }

    // The main loop of the simulationer
    for(int i = 0; i < STEPS; i++) {
      System.out.println("Time "+i);
      System.out.println(theSimulator.representation());
      theSimulator.step();
    }
    System.out.println("Time "+STEPS);
    System.out.println(theSimulator.representation());
  }
}
