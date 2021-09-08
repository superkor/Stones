/*
 * Justin Chow
 * ICS3U
 * Jan 15, 2020
 * Summative Culminating Assignment - Stones
 */

package stones;

/**
 * This class contains most of the logic behind the game, Stones.
 */
public class Player {
	private int[] board;
	private final int PLAYER_HOME = 7;
	private final int OPPONENT_HOME = 0;
	private int moveStones, lastPit;
	private int oppPit;
	private int plaPit;
	
	private int oppCounter;
	//private Scanner input = new Scanner(System.in);
	
	/**
	 * Constructor
	 * Pre: none
	 * Post: Initalizes the board array.
	 */
	public Player(){
		board = new int[14];
	}
	
	/**
	 * Sets starting amount of stones to the board
	 * Pre: none
	 * Post: All pits are given the starting amount of stones.
	 */
	public void setStones(int start){
		for (int x = 0; x < board.length; x++){
			if (x != PLAYER_HOME && x != OPPONENT_HOME){
				board[x] = start;
			} else {
				board[x] = 0;
			}

		}
		
		//System.out.println();
	}
	
	
	/*DEBUG -- SHOWS THE BOARD IN CONSOLE
	public void showBoard(){
		for (int x = 0; x < board.length; x++){
			System.out.print(board[x]+"\t");
			if (x == OPPONENT_HOME-1){
				System.out.println();
			}
	
		}
		System.out.println();
	}
	*/
	
	/**
	 * Moves the stones
	 * Pre: none
	 * Post: Moves the stones from the selected pit and calculates how much times the stones are moved, and which pits are added once. It checks
	 * if the last stone landed in the player's home pit, the player gets another turn by returning True. If it doesn't, returns false. It also checks 
	 * if the last stone was landed in an empty pit (on the player's side). If so, it gives the pit on the opposite side all its stones plus the stone that
	 * landed in the empty pit to the player's home pit.
	 */
	public boolean moveStones(int move){
		//This variable keeps track where the stones are on the opposite side of the board (not the player's pits)
		oppPit = -1;
		moveStones = 0;
		lastPit = 0;
		
			if (board[move] == 0) {
				return(false);
			}
		
		moveStones = board[move];
		board[move] = 0;
		
		
		for (int x = 1; x <= moveStones; x++){
			//System.out.println("----------------------------------");
			if (move+x <= PLAYER_HOME){
				//System.out.println("Pit no: "+(x+move)+" "+x+" "+move);
				board[move+x] += 1;
			} else if (move+x > PLAYER_HOME) {
				//System.out.println("Pit no: "+(12-oppPit)+" "+x+" "+move);
				oppPit += 1;
				board[13-oppPit] += 1;
				if (13-oppPit == OPPONENT_HOME) {
					moveStones += 1;
				}

			}
			lastPit = x+move;
		}
		
		if (lastPit >= 14){
			lastPit -= 14;
		}
		
		if (lastPit == PLAYER_HOME){
			return(true);
		} else {
			if (lastPit < PLAYER_HOME && lastPit > OPPONENT_HOME){
				if (board[lastPit] == 1) {
					//System.out.println("Landed in empty spot.");
					board[PLAYER_HOME]+=board[lastPit+7];
					board[lastPit+7] = 0;
				}
			}
			return(false);
		}
	}

	/**
	 * Moves stones
	 * Pre: none
	 * Post: Does the same as the moveStones method, just for the computer.
	 */
	public boolean opponentMoveStones(int move) {
		moveStones = 0;
		lastPit = 0;
		oppCounter = 0;
		
		//This variable keeps track where the stones are on the opposite side of the board (not the opponent's pits)
		plaPit = -1;

		
		//System.out.println("OPPONENT MOVE " + move);
		
		moveStones = board[move];
		board[move] = 0;
		
		//System.out.println("--------------");

		for (int x = moveStones; x > 0; x--){
			oppCounter += 1;
			//System.out.println("----------------------------------");
			if (move-oppCounter > PLAYER_HOME){
				//System.out.println("Pit no: "+(move-oppCounter)+" "+oppCounter+" "+move);
				board[move-oppCounter] += 1;
			} else if (move-oppCounter == PLAYER_HOME) {
				board[OPPONENT_HOME] += 1;
			} else {
				//System.out.println("Pit no: "+(2+plaPit)+" "+oppCounter+" "+move);
				plaPit += 1;
				board[1+plaPit] += 1;
				if (1+plaPit == PLAYER_HOME) {
					moveStones += 1;
				}

			}
			lastPit = move-oppCounter;
		}
		
		if (lastPit >= 14){
			lastPit -= 14;
		}
		
		//System.out.println("LASTPIT: "+ (lastPit));
		//System.out.println(lastPit-7==OPPONENT_HOME);
		
		if (lastPit-7 == OPPONENT_HOME){
			return(true);
		} else {
			//System.out.println("Last pit " + lastPit);
			if (lastPit > OPPONENT_HOME && lastPit > PLAYER_HOME){
				if (board[lastPit] == 1) {
					//System.out.println("Landed in empty spot.");
					board[OPPONENT_HOME]+= board[lastPit - 7];
					board[lastPit-7] = 0;
				}
			}
			return(false);
		}	
	}
	
	/**
	 * Gives the board array.
	 * Pre: none
	 * Post: returns the board array
	 * 	 
	 */
	public int[] getBoard() {
		return(board);
	}
	
	
	/**
	 * Checks which home pit has the most stones.
	 * Pre: none
	 * Post: Calculates which home pit has the most stones. If the player won (has more stones), returns 2. If the computer won, returns 1. 
	 * If it is a tie, returns 0.
	 */
	public int win() {
		int playerPit = 0,opponentPit = 0;
		
		for (int x = 1; x < 7; x++) {
			//System.out.println("Player"+x+" "+board[x]);
			playerPit += board[x];
		}
		
		for (int x = 8; x < 14; x++) {
			//System.out.println("Opponent"+x+" "+board[x]);
			opponentPit += board[x];
		}
		
		//System.out.println(playerPit != 0 || opponentPit != 0);
		
		if (playerPit == 0 || opponentPit == 0) {

			for (int x = 1; x < 7; x++) {
				board[PLAYER_HOME]+=board[x] ;
				board[x] = 0;
			}
			
			for (int x = 8; x < 14; x++) {
				board[OPPONENT_HOME] += board[x];
				board[x] = 0;
			}
			//System.out.println("---------------------------------\n\n");
			playerPit = board[PLAYER_HOME];
			opponentPit = board[OPPONENT_HOME];

			//System.out.println("---------------------------------------------------------------------------\n\n");
			if (playerPit > opponentPit) {
				//showBoard();
				//System.out.println("You win!");
				return(2);
			} else if (opponentPit > playerPit) {
				//showBoard();
				//System.out.println("You lose!");
				return(1);
			} else {
				//showBoard();
				//System.out.println("It's a draw!");
				return(0);
			}		
		} else {
			return(-1);
		}
	}
	
	/**
	 * Returns number of stones in pit
	 * Pre: none
	 * Post: returns number of stones
	 */
	public int getStones(int pit) {
		return(board[pit]);
	}
}
