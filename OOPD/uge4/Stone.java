public class Stone extends Entity implements Printable {

    /**
     *
     * @return Charecter to use for visual representation
     */
    public String representation()
    {
        return "s";
    }

    /**
     *
     * @param aBoard Board this Stone exists on
     * @param aPosition Initial position of stone
     */
    public Stone(Board aBoard, Position aPosition) {
        super(aBoard, aPosition, 0);
    }

}
