import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * TicTacToe med AI!
 */
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

    /**
     * To main loops holder spillet kørende, indtil brugeren vælger at afbryde
     * spillet
     */
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
            //Denne linje er for at konsumere linjeskift efterladt fra nextInt
            scan.nextLine();
            if(scan.nextLine().startsWith("n")) {
                break;
            }
        }
    }

    /**
     * @param symbol: hvems tur det er
     * @returns: array med [row, col] sæt
     * Ud fra tur og indstilling hentes en handling fra en spiller.
     */
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

    /**
     * Check om man må placere en brik på et punkt
     */
    public static boolean isValidCoord(int x, int y) {
        return (-1 < x  && x < 3 && -1 < y  && y < 3 && board[x][y] == ' ');
    }
    /**
     * Modtag handling fra et menneske
     * Samme opførsel som getAction()
     */
    public static int[] getHumanAction(char symbol) {
        System.out.println("Skriv række (1-" + board.length+"-)");
        int x = scan.nextInt() - 1;
        System.out.println("Skriv kolonne (1-"+board[0].length+")");
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

    /**
     * Ryd pladen
     */
    public static void newBoard() {
        board = new char[3][3];
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                board[x][y] = ' ';
            }
        }
    }

    /**
     * Print boardets indhold til skærmen, på en fancy måde
     */
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

    /**
     * @param row
     * @param column
     * @return char
     * Retunerer hvad der er på et punkt på pladen
     */
    public static char get(int row, int column) {
        return board[row][column];
    }

    /**
     * @param row
     * @param column
     * Sætter en char på pladen
     */
    public static void set(int row, int column, char val) {
        board[row][column] = val;
    }

    public static boolean isGameOver()
    {
	    return isGameOver(board);
    }

    /**
     * Undersøger om spillet kan fortsætte.
     * Dette er ikke tilfældet hvis en rækk/kolonne/diagonal er udfyldt af en
     * spiller, eller pladen er fyldt ud.
     */
    public static boolean isGameOver(char[][] board) {
	    int rowCount = board.length;
	    int columnCount = board[0].length;
	    int winCount = 3;
	    int i = 0;
	    char c = '.';

	    //Horizontal check
	    for (int row = 0; row < rowCount; row++)
		{
		    for (int column = 0; column < columnCount; column++)
			{
			    if(board[row][column] == c) i++;
			    else if(board[row][column] != c && board[row][column] != ' ')
				{
				    c = board[row][column];
				    i = 1;
				}
			    else i = 0;
			    if(i == winCount) return true;
			}
		    i = 0;
		}

	    //Vertical check
	    for (int column = 0; column < columnCount; column++)
		{
		    for (int row = 0; row < rowCount; row++)
			{
			    if(board[row][column] == c) i++;
			    else if(board[row][column] != c && board[row][column] != ' ')
				{
				    c = board[row][column];
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
	    for (int row = 0; row < rowCount; row++)
		{
		    for (int column = 0; column < columnCount; column++)
			{
			    if(board[row][column] != ' ') numSymbols ++;
			}
		}
	    return numSymbols == 9;
    }

    /**
     * @return en kopi af pladen der gerne må ændres på.
     */
    private static char[][] getBoardCopy() {
        char[][] copy = new char[board.length][board.length];
        for(int row = 0; row < board.length; row++) {
            for(int column = 0; column < board.length; column++) {
                copy[row][column] = board[row][column];
            }
        }
        return copy;
    }

    /**
     * Opfører sig som getAction, men lader computeren udregne det optimale
     * træk.
     */
    public static int[] getAIAction(char playerSymbol)
    {
	    int rowCount = board.length;
	    int columnCount = board[0].length;
	    int[][] corners = new int[][] {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
	
	    //Try for a winning move
        for (int row = 0; row < rowCount; row++)
        {
		    for (int column = 0; column < columnCount; column++)
		    {
			    if(board[row][column] != ' ') continue;
			    char[][] testBoard = getBoardCopy();
			    testBoard[row][column] = playerSymbol;
			    if(isGameOver(testBoard))
			    {
				int[] move = new int[] {row, column};
				return move;
			    }
		    }
	    }

	//Try to prevent opponent from winning
        for (int row = 0; row < rowCount; row++)
	    {
    		for (int column = 0; column < columnCount; column++)
		    {
			    if(board[row][column] != ' ') continue;
			    char[][] testBoard = getBoardCopy();
			    testBoard[row][column] = 'x' == playerSymbol ? 'o' : 'x';
			    if(isGameOver(testBoard))
			        {
				    int[] move = new int[] {row, column};
				    return move;
			        }
		        }
	        }

	//If center is free and a corner is taken, take center
	if(board[1][1] == ' ')
	    {
		for (int cornerIndex = 0; cornerIndex < corners.length; cornerIndex++)
		    {
			if(board[corners[cornerIndex][0]][corners[cornerIndex][1]] != ' ')
			    {
				int[] move = new int[] {1, 1};
				return move;
			    }
		    }
	    }


	    //Try to place in a corner
	    for (int cornerIndex = 0; cornerIndex < corners.length; cornerIndex++)
        {
		    if(board[corners[cornerIndex][0]][corners[cornerIndex][1]] == ' ')
	        {
			    return corners[cornerIndex];
	        }
        }

	    //Do random move
	    ArrayList<Integer[]> emptyPositions = new ArrayList<Integer[]>();
	    for (Integer row = 0; row < rowCount; row++)
        {
		    for (Integer column = 0; column < columnCount; column++)
		    {
			if(board[row][column] == ' ')
			    {
				    Integer[] move = new Integer[] {row, column};
				    emptyPositions.add(move);
			    }
		    }
	    }
	    if(emptyPositions.size() > 0)
	    {
		    Random random = new Random();
		    Integer n = random.nextInt(emptyPositions.size());
		    int[] mv = new int[] {(int) emptyPositions.get(n)[0], (int) emptyPositions.get(n)[1]};
		    return mv;
	    }
        else return null;
    }

    /**
     * Opfører sig som getAction, men lader computeren udføre et tilfældigt
     * træk.
     */
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
