import org.junit.Test;

import static org.junit.Assert.*;

public class StoneTest {

    @Test
    public void testRepresentation() throws Exception {
        Board board = new Board();
        Stone stone = new Stone(board, new Position(1,1));

        org.junit.Assert.assertEquals("failure stone not s", stone.representation(),"s");
        System.out.println("Representation is ok");

    }
}