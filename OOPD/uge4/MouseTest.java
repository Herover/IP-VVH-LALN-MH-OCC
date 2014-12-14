import org.junit.Test;

import static org.junit.Assert.*;

public class MouseTest {

    @Test
    public void testRepresentation() throws Exception {
        Board board = new Board();
        Mouse mouse = new Mouse(board, new Position(1,1));

        org.junit.Assert.assertEquals("failure mouse not m",
                                      mouse.representation(),"m");
    }

    @Test
    public void testMove() throws Exception {
        int oldRow;
        int oldCol;
        int newRow;
        int newCol;

        for(int i = 0; i < 100; i++) {

            Board board1 = new Board();
            Mouse mouse1 = new Mouse(board1, new Position(1, 1));

            oldRow = mouse1.getPosition().getRow();
            oldCol = mouse1.getPosition().getCol();
            mouse1.move();
            newRow = mouse1.getPosition().getRow();
            newCol = mouse1.getPosition().getCol();

            org.junit.Assert.assertTrue("Mouse moved more than two moves",
                                        Math.abs(oldCol - newCol) <= 1
                    && Math.abs(oldRow - newRow) <= 1);

        }

        for(int i = 0; i < 10; i++) {

            Board board2 = new Board();
            Mouse mouse2 = new Mouse(board2, new Position(0, 0));
            Stone stone1 = new Stone(board2, new Position(0, 1));
            Stone stone2 = new Stone(board2, new Position(1, 1));
            Stone stone3 = new Stone(board2, new Position(1, 0));

            oldRow = mouse1.getPosition().getRow();
            oldCol = mouse1.getPosition().getCol();
            mouse1.move();
            newRow = mouse1.getPosition().getRow();
            newCol = mouse1.getPosition().getCol();

            org.junit.Assert.assertTrue("Mouse moved even though it can't",
                                        oldRow = newRow && oldCol = newCol);

        }



    }
}
