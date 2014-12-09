import org.junit.Test;
import org.junit.Assert;

public class StoneTester {

    @Test public void stoneRepresenation() {
        Board board = new Board();
        Position pos = new Position(5, 5);
        Stone stone = new Stone(board, pos);
        Assert.assertEquals("s", stone.representation);
    }
}
