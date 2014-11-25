import java.util.Scanner;

public class TicTacToe
{
    public static char[][] board = new char[3][3];

    public static boolean playing = true;
    public static boolean inGame = true;
    public static char turn = 'x';
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int[] action;
        while(playing) {
            newBoard();
            while(inGame) {
                System.out.println(turn + "'s tur");
                action = getHumanAction(turn);
                set(action[0], action[1], turn);
                printBoard();
                turn = turn=='x'?'o':'x';
                if(isGameOver()) {
                    System.out.println(turn + " tabte!");
                    inGame = false;
                }
            }
            System.out.println("Vil du spille igen? j/n");
            if(scan.nextLine() == "n") {
                playing = false;
            }
        }
    }

    public static boolean isValidCoord(int x, int y) {
        return (-1 < x  && x < 3 && -1 < y  && y < 3 && board[x][y] == ' ');
    }

    public static int[] getHumanAction(char symbol) {
        System.out.println("Skriv X koordinat");
        int x = scan.nextInt() - 1;
        System.out.println("Skriv Y koordinat");
        int y = scan.nextInt() - 1;
        while(!isValidCoord(x, y)) {
            System.out.println("Du kan ikke sætte et " + symbol + " her!");
            System.out.println("Skriv X koordinat");
            x = scan.nextInt() - 1;
            System.out.println("Skriv Y koordinat");
            y = scan.nextInt() - 1;
        }
        int[] action = new int[2];
        action[0] = x;
        action[1] = y;
        return action;
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
        return board[row][column];
    }

    public static void set(int row, int column, char val) {
        board[row][column] = val;
    }

    public static boolean isGameOver() {

        return false;
    }
}
