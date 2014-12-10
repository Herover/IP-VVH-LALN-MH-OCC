public class Stone extends Entity implements Printable {
    
    public String representation()
    {
	return "s";
    }

    /**
     *
     * @param aBoard
     * @param aPosition
     */
    public Stone(Board aBoard, Position aPosition) {
        super(aBoard, aPosition, 0);
    }

}
