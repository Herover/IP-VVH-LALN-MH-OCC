import java.util.ArrayList;


/**
  *The board that we are using for the simulation
*/
public class Board {
  private final int ROWS = 10;
  private final int COLS = 10;
  private Entity[][] board;
  private ArrayList<Entity> listOfEntities;

  /**
   * Constructor
   */
  public Board() {
    board = new Entity[COLS][ROWS];
    listOfEntities = new ArrayList<Entity>();
  }

  /**
   * Gives an entity at a given position
   * @param aPosition The position on the board where we want the entity
   * @return Return the enitity at the given position
   */  
  public Entity get(Position aPosition) {
    return board[aPosition.getRow()][aPosition.getCol()];
  }

  /**
   * Add an entity
   * @param aPosition position of the added entity
   * @param anEntity the entity which is added to the board, and the list of entities
   */  
  public void add(Position aPosition, Entity anEntity) {
    board[aPosition.getRow()][aPosition.getCol()] = anEntity;
    listOfEntities.add(anEntity);
  }


  /**
   * move an entity
   * @param aPosition position the entity is moved to
   * @param anEntity entity to be moved
   */  
  public void moveTo(Position aPosition, Entity anEntity) {
    Position currentPosition = anEntity.getPosition();
    board[currentPosition.getRow()][currentPosition.getCol()] = null;
    board[aPosition.getRow()][aPosition.getCol()] = anEntity;
    anEntity.setPosition(aPosition);
  }

  /**
   * @return the array list of entities on the board
   */    
  public ArrayList<Entity> getEntities() {
    return listOfEntities;
  }
  
  /**
   * @return number of rows on board
   */
  public int getRows(){
    return ROWS;
  }

  /**
   * @return number of columns on board
   */
  public int getCols(){
    return COLS;
  }
}
