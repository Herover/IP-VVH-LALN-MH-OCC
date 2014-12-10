import org.junit.Test;

import static org.junit.Assert.*;

public class MouseTest {

    @Test
    public void testRepresentation() throws Exception {
        Board board = new Board();
        Mouse mouse = new Mouse(board, new Position(1,1));

        org.junit.Assert.assertEquals("failure mouse not m", mouse.representation(),"m");
        System.out.println("Representation is ok");

    }

    @Test
    public void testMove() throws Exception {
        int oldRow;
        int oldCol;
        int newRow;
        int newCol;

        for(int i = 0; i < 100; i++) {

            Board board = new Board();
            Mouse mouse = new Mouse(board, new Position(1, 1));

            oldRow = mouse.getPosition().getRow();
            oldCol = mouse.getPosition().getCol();
            mouse.move();
            newRow = mouse.getPosition().getRow();
            newCol = mouse.getPosition().getCol();

            org.junit.Assert.assertTrue("Mouse moved more than two moves", Math.abs(oldCol - newCol) <= 1
                    && Math.abs(oldRow - newRow) <= 1);

        }
        System.out.println("Test move is ok");
    }
}