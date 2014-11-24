public class TicTacToe
{
    public static char[][] board = new char[3][3];

    public static void main(String[] args) {

    }

    public static void newBoard() {
        board = new char[3][3];
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                board[x][y] = ' ';
            }
        }
    }

    public static void printBoard() {
        System.out.println(" |   |   |   | ");
        for(int x = 0; x < 3; x++) {
            System.out.println("-+---+---+---+-");
            System.out.println(" |   |   |   | ");
            for(int y = 0; y < 3; y++) {
                System.out.print(" | " + board[x][y]);
            }
            System.out.println(" | ");
            System.out.println(" |   |   |   | ");
        }
        System.out.println("-+---+---+---+-");
        System.out.println(" |   |   |   | ");
    }

    public static char get(int row, int column) {

        return ' ';
    }

    public static void set(int row, int column, char val) {

    }

    public static boolean isGameOver() {

        return true;
    }
}
