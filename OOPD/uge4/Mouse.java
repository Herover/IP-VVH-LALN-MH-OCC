import java.util.ArrayList;
import java.util.Random;

public class Mouse extends Entity implements Printable
{
    public String representation()
    { 
	return "m";
    }

    public Mouse(Board aBoard, Position aPosition)
    {
	super(aBoard, aPosition, 0);
    }

    @Override
    /**
     * Moves to a random nearby empty position if possible and outputs the move as text
     */
    public void move()
    {
	ArrayList<Position> emptyPositions = findEmptyNeighbours();
	if(emptyPositions.size() == 0) return;
	Random rand = new Random();
	Position newPos = emptyPositions.get(rand.nextInt(emptyPositions.size() - 1));
	System.out.println("Mouse at " + positionToString(getPosition()) + 
						       " is moved to " 
						       + positionToString(newPos));
	getBoard().moveTo(newPos, this);
    }

    /**
     * @param the position to convert
     * @return a position converted to a string
     */
    private String positionToString(Position pos)
    {
	return "(" + pos.getRow() + ", " + pos.getCol() + ")";
    }

    /**
     * @return a list of all empty positions near own position
     */
    private ArrayList<Position> findEmptyNeighbours()
    {
	int selfRow = getPosition().getRow();
	int selfCol = getPosition().getCol();
	int rowCount = getBoard().getRows();
	int colCount = getBoard().getCols();
	ArrayList<Position> emptyPositions = new ArrayList<Position>();
	
	//Selects a row and then checks columns for empty positions
	for(int r = selfRow - 1; r <= selfRow + 1; r++)
	    {
		if(r < 0) continue;
		if(r >= rowCount) break;
		for(int c = selfCol - 1; c <= selfCol + 1; c++)
		    {
			if(c < 0) continue;
			if(c >= colCount) break;
			Position curPos = new Position(r, c);
			if(getBoard().get(curPos) == null) emptyPositions.add(curPos);
		    }
	    }
	return emptyPositions;
    }
}
