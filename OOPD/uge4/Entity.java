import java.util.ArrayList;
import java.util.Random;

/**
* Represent an entity in the simulation, and is the super class off all entities in the simulation.
*/
public class Entity implements Printable {
  private int speed;
  private Position position;
  private Board board;

  /**
   * Constructor
   * @param aBoard the board for the entity
   * @param aPosition the position for the entity
   * @param aSpeed the speed of the entity
   */
  public Entity(Board aBoard, Position aPosition, int aSpeed) {
    board = aBoard;
    position = aPosition;
    speed = aSpeed;
  }

  /**
   * @return the board of the entity
   */
  public Board getBoard() {
    return board;
  }

  /**
   * @return the position of the entity
   */
  public Position getPosition() {
    return position;
  }

  /**
   * set the position for the entity
   * @param aPosition position for the entity
   */
  public void setPosition(Position aPosition) {
    position = aPosition;
  }

  /**
   * @return the speed of the entity
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * Moves the entity by updating the position, if the entity is capable of moving.
   */
  public void move() {
  }

  /**
   * @return string representation of the entity.
   */
  public String representation() {
    return null;
  }
}
