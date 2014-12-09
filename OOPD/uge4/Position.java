
/**
 * A class for representing positions on a 2D plane.
 * The position is represented as to integer coordinates x and y.
 * Positions are imutable.
 */
public class Position {
    private int row;
    private int col;

    /**
     * Constructor
     * @param aRow row position
     * @param aCol coloumn position
     */
    public Position(int aRow, int aCol) {
        row = aRow;
        col = aCol;
    }

    /**
     * @return The x coordinate of the position
     */
    public int getRow() {
        return row;
    }

    /**
     * @return The y coordinate of the position
     */
    public int getCol() {
        return col;
    }
}
