import org.junit.Test;
import org.junit.Assert;

public class RockTester {

    @Test public void rockRepresenation() {
        Board board = new Board();
        Position pos = new Position(5, 5);
        Stone stone = new Stone(board, pos);
        Assert.assertEquals("s", stone.representation);
        Assert.assertEquals("b", stone.representation);
    }
}
