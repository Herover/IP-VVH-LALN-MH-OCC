import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class TicTacToe
{
    public static char[][] board = new char[3][3];

    public static char turn = 'x';
    private static Scanner scan = new Scanner(System.in);

    private static int playerX;
    private static int playerO;

    private static final int PLAYER_HUMAN = 0;
    private static final int PLAYER_ROBOT = 1;
    private static final int PLAYER_DRUNK_ROBOT = 2;

    public static void main(String[] args) {
        int[] action;
        
        while(true) {
            newBoard();
            System.out.println("Angiv spiller X:");
            System.out.println("0: menneske");
            System.out.println("1: ai");
            System.out.println("2: drunken ai");
            playerX = scan.nextInt();
            System.out.println("Spiller O:");
            playerO = scan.nextInt();

            turn = 'x';
            while(true) {
                System.out.println(turn + "'s tur");
                action = getAction(turn);
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

    public static int[] getAction(char symbol) {
        if(symbol == 'x') {
            if (playerX == PLAYER_HUMAN)
                return getHumanAction(symbol);
            else if(playerX == PLAYER_ROBOT)
                return getAIAction(symbol);
            else
                return getDrunkenAIAction(symbol);
        }
        else {
            if (playerO == PLAYER_HUMAN)
                return getHumanAction(symbol);
            else if(playerO == PLAYER_ROBOT)
                return getAIAction(symbol);
            else
                return getDrunkenAIAction(symbol);
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

    public static boolean isGameOver(char[][] board) {
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
        int numSymbols = 0;
	    for (int x = 0; x < rows; x++)
        {
		    for (int y = 0; y < columns; y++)
	        {
			    if(board[x][y] == ' ') numSymbols ++;
	        }
        }
	    return numSymbols == 9;
    }

    private static char[][] getBoardCopy() {
        char[][] copy = new char[board.length][board.length];
        for(int x = 0; x < board.length; x ++) {
            for(int y = 0; y < board.length; y ++) {
                copy[x][y] = board[x][y];
            }
        }
        return copy;
    }

    public static int[] getAIAction(char playerSymbol)
    {
	    int rows = board.length;
	    int columns = board[0].length;
	
	    //Try for a winning move
        for (int x = 0; x < board.length; x++)
        {
		    for (int y = 0; y < board[x].length; y++)
		    {
			    if(board[x][y] != ' ') continue;
			    char[][] testBoard = getBoardCopy();
			    testBoard[x][y] = playerSymbol;
			    if(isGameOver(testBoard))
			    {
				    int[] move = new int[] {x, y};
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
			    char[][] testBoard = getBoardCopy();
			    testBoard[x][y] = playerSymbol;
			    if(isGameOver(testBoard))
			        {
				    int[] move = new int[] {x, y};
				    return move;
			        }
		        }
	        }

	    //Do random move
	    ArrayList<Integer[]>  emptyPositions  = new ArrayList<Integer[]>();
	    for (Integer x = 0; x < rows; x++)
        {
		    for (Integer y = 0; y < columns; y++)
	        {
			    if(board[x][y] != ' ')
		        {
				    Integer[] move = new Integer[] {x, y};
				    emptyPositions.add(move);
		        }
	        }
            if(emptyPositions.size() == 0) System.out.println("ARG INGEN LEDIGE PLADSER");
		    Random random = new Random();
		    Integer n = random.nextInt(emptyPositions.size());
            int[] mv = new int[] {(int) emptyPositions.get(n)[0], (int) emptyPositions.get(n)[1]};
		    return mv;
        }
        return null;
    }

    public static int[] getDrunkenAIAction(char playerSymbol)
    {
        Random rand = new Random();
        int[] action = new int[2];
        action[0] = -1;
        action[1] = -1;
        while(!isValidCoord(action[0], action[1])) {
            action[0] = rand.nextInt(3);
            action[1] = rand.nextInt(3);
        }
        return action;
    }

}
