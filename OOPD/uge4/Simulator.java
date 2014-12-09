import java.util.ArrayList;
import java.util.Random;

/**
* Overall simulator, that links together all the pieces of the simulation.
*/
public class Simulator implements Printable {
  private Board board;

  /**
   * Constructor
   * @param aBoard the board for the simulation
   */
  public Simulator(Board aBoard) {
    board = aBoard;
  }

  /**
   * Gives the string representation of the simulation
   * @return string representation of the simulation
   */
  public String representation() {
    StringBuffer buffer = new StringBuffer();

    for(int i = 0; i < board.getCols(); i++) {
      for(int j = 0; j < board.getRows(); j++) {
        Position aPosition = new Position(i, j);
        Entity anEntity = board.get(aPosition);
        buffer.append("[");
        if(anEntity == null) {
          buffer.append(" ");
        }
        else {
          buffer.append(anEntity.representation());
        }
        buffer.append("]");
      }
      buffer.append("\n");
    }
    return buffer.toString();
  }

  /**
   * Moves all entities on the board
   */
  public void step() {
    ArrayList<Entity> listOfEntities = board.getEntities();

    for(int i = 0; i < listOfEntities.size(); i++) {
      Entity anEntity = listOfEntities.get(i);
      anEntity.move();
    }
  }
}
