import java.util.ArrayList;
import java.util.Random;

public class Rabbit extends Entity implements Printable
{
    String representation = "r";

    public Rabbit(Board aBoard, Position aPosition, int aSpeed)
    {
	super(aBoard, aPosition, aSpeed);
    }

    @Override
    public void move()
    {
	ArrayList<Position> emptyPositions = findEmptyNeighbours();
	if(emptyPositions.size() == 0) return;
	Random rand = new Random();
	setPosition(emptyPositions.get(rand.nextInt(emptyPositions.size() - 1)));
    }

    private ArrayList<Position> findEmptyNeighbours()
    {
	int selfRow = getPosition().getRow();
	int selfCol = getPosition().getCol();
	int rowCount = getBoard().getRows();
	int colCount = getBoard().getCols();
	ArrayList<Position> emptyPositions = new ArrayList<Position>();
	
	//Selects a row and then checks columns for empty positions
	for(int r = selfRow + 1; r <= selfRow + 2; r++)
	    {
		if(r < 0) continue;
		if(r > rowCount) break;
		for(int c = selfCol + 1; c <= selfCol + 2; c++)
		    {
			if(c < 0) continue;
			if(c > colCount) break;
			Position curPos = new Position(r, c);
			if(getBoard().get(curPos) == null) emptyPositions.add(curPos);
		    }
	    }
	return emptyPositions;
    }
}
