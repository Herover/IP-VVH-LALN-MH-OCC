import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class TicTacToe
{
    public static char[][] board = new char[3][3];

    public static char turn = 'x';
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int[] action;
        while(true) {
            newBoard();
            while(true) {
                System.out.println(turn + "'s tur");
                action = getHumanAction(turn);
                set(action[0], action[1], turn);
                printBoard();
                turn = turn=='x'?'o':'x';
                if(isGameOver()) {
                    System.out.println(turn + " tabte!");
                    break;
                }
            }
            System.out.println("Vil du spille igen? j/n");
            if(scan.nextLine() == "n") {
                break;
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

    public static boolean isGameOver()
    {
	return isGameOver(board);
    }

    public static boolean isGameOver(char[][] board)
    {
	int rows = board.length;
	int columns = board[0].length;
	int winCount = 3;
	int i = 0;
	char c = '.';

	//Horizontal check
	for (int x = 0; x < rows; x++)
	    {
		for (int y = 0; y < columns; y++)
		    {
			if(board[x][y] == c) i++;
			else if(board[x][y] != c && board[x][y] != ' ')
			    {
				c = board[x][y];
				i = 1;
			    }
			else i = 0;
			
			if(i == winCount) return true;
		    }
		i = 0;
	    }

	//Vertical check
	for (int y = 0; y < columns; y++)
	    {
		for (int x = 0; x < rows; x++)
		    {
			if(board[x][y] == c) i++;
			else if(board[x][y] != c && board[x][y] != ' ')
			    {
				c = board[x][y];
				i = 1;
			    }
			else i = 0;
			
			if(i == winCount) return true;
		    }
		i = 0;
	    }

	//Diagonal check
	if(board[0][0] != ' ')
	    {
		c = board[0][0];
		if(board[1][1] == c && board[2][2] == c)
		    {
			return true;
		    }
	    }

	//Anti-Diagonal check
	if(board[0][2] != ' ')
	    {
		c = board[0][2];
		if(board[1][1] == c && board[2][0] == c)
		    {
			return true;
		    }
	    }

	//Board filled check
	for (int x = 0; x < rows; x++)
	    {
		for (int y = 0; y < columns; y++)
		    {
			if(board[x][y] == ' ') break;
		    }
		return true;
	    }
	return false;
    }

    public static char[] ai(char playerSymbol)
    {
	int rows = board.length;
	int columns = board[0].length;
	
	//Try for a winning move
        for (int x = 0; x < board.length; x++)
	    {
		for (int y = 0; y < board[x].length; y++)
		    {
			if(board[x][y] != ' ') continue;
			char[][] testBoard = board.clone();
			testBoard[x][y] = playerSymbol;
			if(isGameOver(testBoard))
			    {
				char[] move = new char[] {(char) x, (char) y};
				return move;
			    }
		    }
	    }

	//Try to prevent opponents from winning
        for (int x = 0; x < board.length; x++)
	    {
		for (int y = 0; y < board[x].length; y++)
		    {
			if(board[x][y] != ' ' | board[x][y] == playerSymbol) continue;
			char[][] testBoard = board.clone();
			testBoard[x][y] = playerSymbol;
			if(isGameOver(testBoard))
			    {
				char[] move = new char[] {(char) x, (char) y};
				return move;
			    }
		    }
	    }

	//Do random move
	ArrayList<char[]>  emptyPositions  = new ArrayList<char[]>();
	for (int x = 0; x < rows; x++)
	    {
		for (int y = 0; y < columns; y++)
		    {
			if(board[x][y] == ' ')
			    {
				char[] move = new char[] {(char) x, (char) y};
				emptyPositions.add(move);
			    }
		    }
		Random random = new Random();
		int n = random.nextInt(8);
		return emptyPositions.get(n);
	    }
	return null;
    }
}
