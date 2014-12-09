public class Stone extends Entity implements Printable {
    
    public String representation()
    {
	return "s";
    }

    public Stone(Board aBoard, Position aPosition) {
        super(aBoard, aPosition, 0);
    }

}
