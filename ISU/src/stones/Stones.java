/*
 * Justin Chow
 * ICS3U
 * Jan 15, 2020
 * Summative Culminating Assignment - Stones
 */

package stones;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class creates and shows the GUI version of the game, Stones. The game is also runs from this class.
 *
 */
public class Stones implements ActionListener{
	final int HIGH = 13;
	final int LOW = 8;

	JFrame game;
	JFrame start;
	JFrame winFrame;
	
	JPanel contentPane, startPane, winPane;
	JButton startTwo,startThree,startFour,startFive;
	
	JRadioButton twoPlayer;
	JRadioButton comp;
	ButtonGroup group;
	
	JLabel startInfo, winInfo, info;
	JButton filler, filler2;

	JLabel playerHome;
	JButton bin1, bin2, bin3, bin4, bin5, bin6;
	JButton restart, quit;

	JLabel opponentHome;
	JButton bin7, bin8, bin9, bin10, bin11, bin12;
	
	int aiLogic, aiChoice, stoneAmt;
	int win, stone1,stone2,stone3,stone4,stone5,stone6,stone7,stone8,stone9,stone10,stone11,stone12;
	Player player = new Player();
	int[] array;
	int opponentMove, again = 0;
	String end = "";
	int twoPlay = 0;
	int playerTurn = 0;
	
	
	/**
	 * Constructor
	 * Pre: none
	 * Post: All of the GUI objects are initialized.
	 */
	public Stones(){
		//START GAME----------------
		start = new JFrame("Rocks");
		start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startPane = new JPanel();
		startPane.setPreferredSize(new Dimension(400,600));
		startPane.setLayout(new GridLayout(0,1,0,5));
		
		startInfo = new JLabel("<html>Stones<br>Created by Justin Chow<br>Select how many stones you wish to start the game off:</html>");
		startInfo.setVisible(true);
		startInfo.setAlignmentX(JButton.CENTER_ALIGNMENT);
		startPane.add(startInfo);
		
		startTwo = new JButton("2");
		startTwo.setActionCommand("2 start");
		startTwo.addActionListener(this);
		startTwo.setAlignmentX(JButton.BOTTOM_ALIGNMENT);
		startPane.add(startTwo);
		
		startThree = new JButton("3");
		startThree.setActionCommand("3 start");
		startThree.addActionListener(this);
		startThree.setAlignmentX(JButton.BOTTOM_ALIGNMENT);
		startPane.add(startThree);
		
		startFour = new JButton("4");
		startFour.setActionCommand("4 start");
		startFour.addActionListener(this);
		startFour.setAlignmentX(JButton.BOTTOM_ALIGNMENT);
		startPane.add(startFour);
		
		startFive = new JButton("5");
		startFive.setActionCommand("5 start");
		startFive.addActionListener(this);
		startFive.setAlignmentX(JButton.BOTTOM_ALIGNMENT);
		startPane.add(startFive);
		
		twoPlayer = new JRadioButton();
		twoPlayer.setText("Two Player Mode");
		startPane.add(twoPlayer);
		
		comp = new JRadioButton();
		comp.setText("Computer Mode");
		comp.setSelected(true);
		startPane.add(comp);
		
		quit = new JButton("Quit");
		quit.setActionCommand("Quit");
		quit.addActionListener(this);
		startPane.add(quit);
		
		start.setContentPane(startPane);
		start.pack();
		start.setVisible(true);
		
		group = new ButtonGroup();
		group.add(twoPlayer);
		group.add(comp);
		//----------------------------------

		
		//GAME PORTION------------------- (use of filler buttons are for formating purposes; no actual function)
		 game = new JFrame("Rocks");
		 game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 
		 contentPane = new JPanel();
		 contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		 contentPane.setLayout(new GridLayout(0,8,1,10));
		 contentPane.setPreferredSize(new Dimension(1100,300));
		 
		 filler = new JButton("Filler");
		 filler.setVisible(false);
		 filler.setAlignmentX(JButton.LEFT_ALIGNMENT);
		 contentPane.add(filler);
		 
		 
		 bin1 = new JButton("Bin 1");
		 bin1.setActionCommand("1");
		 bin1.addActionListener(this);
		 bin1.setAlignmentX(JButton.TOP_ALIGNMENT);
		 contentPane.add(bin1);
		 
		 bin2 = new JButton("Bin 2");
		 bin2.setActionCommand("2");
		 bin2.addActionListener(this);
		 bin2.setAlignmentX(JButton.TOP_ALIGNMENT);
		 contentPane.add(bin2);
		 
		 bin3 = new JButton("Bin 3");
		 bin3.setActionCommand("3");
		 bin3.addActionListener(this);
		 bin3.setAlignmentX(JButton.TOP_ALIGNMENT);
		 contentPane.add(bin3);
		 
		 bin4 = new JButton("Bin 4");
		 bin4.setActionCommand("4");
		 bin4.addActionListener(this);
		 bin4.setAlignmentX(JButton.TOP_ALIGNMENT);
		 contentPane.add(bin4);
		 
		 bin5 = new JButton("Bin 5");
		 bin5.setActionCommand("5");
		 bin5.addActionListener(this);
		 bin5.setAlignmentX(JButton.TOP_ALIGNMENT);
		 contentPane.add(bin5);
		 
		 bin6 = new JButton("Bin 6");
		 bin6.setActionCommand("6");
		 bin6.addActionListener(this);
		 bin6.setAlignmentX(JButton.TOP_ALIGNMENT);
		 contentPane.add(bin6);
		 
		 filler = new JButton("Filler");
		 filler.setVisible(false);
		 filler.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		 contentPane.add(filler);
		
		 //------------------------------------
		 opponentHome = new JLabel("<html>Opponent Pit<br>0 stones</html>");
		 opponentHome.setAlignmentX(JButton.LEFT_ALIGNMENT);
		 contentPane.add(opponentHome);
		 
		 filler = new JButton("Filler");
		 filler.setVisible(false);
		 filler.setAlignmentX(JButton.LEFT_ALIGNMENT);
		 contentPane.add(filler);
		 filler = new JButton("Filler");
		 filler.setVisible(false);
		 filler.setAlignmentX(JButton.LEFT_ALIGNMENT);
		 contentPane.add(filler);
		 info = new JLabel("Player's 1 turn");
		 info.setVisible(true);
		 info.setAlignmentX(JButton.LEFT_ALIGNMENT);
		 contentPane.add(info);
		 filler = new JButton("Filler");
		 filler.setVisible(false);
		 filler.setAlignmentX(JButton.LEFT_ALIGNMENT);
		 contentPane.add(filler);
		 filler = new JButton("Filler");
		 filler.setVisible(false);
		 filler.setAlignmentX(JButton.LEFT_ALIGNMENT);
		 contentPane.add(filler);
		 filler = new JButton("Filler");
		 filler.setVisible(false);
		 filler.setAlignmentX(JButton.LEFT_ALIGNMENT);
		 contentPane.add(filler);
		 
		 playerHome = new JLabel("<html>Player Pit<br>0 stones</html>");
		 playerHome.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		 contentPane.add(playerHome);
		 
		 filler = new JButton("Filler");
		 filler.setVisible(false);
		 filler.setAlignmentX(JButton.LEFT_ALIGNMENT);
		 contentPane.add(filler);
		 
		 //--------------------------------------
		 bin7 = new JButton("Bin 7");
		 bin7.setActionCommand("8");
		 bin7.addActionListener(this);
		 bin7.setAlignmentX(JButton.TOP_ALIGNMENT);
		 contentPane.add(bin7);
		 
		 bin8 = new JButton("Bin 8");
		 bin8.setActionCommand("9");
		 bin8.addActionListener(this);
		 bin8.setAlignmentX(JButton.TOP_ALIGNMENT);
		 contentPane.add(bin8);
		 
		 bin9 = new JButton("Bin 9");
		 bin9.setActionCommand("10");
		 bin9.addActionListener(this);
		 bin9.setAlignmentX(JButton.TOP_ALIGNMENT);
		 contentPane.add(bin9);
		 
		 bin10 = new JButton("Bin 10");
		 bin10.setActionCommand("11");
		 bin10.addActionListener(this);
		 bin10.setAlignmentX(JButton.TOP_ALIGNMENT);
		 contentPane.add(bin10);
		 
		 bin11 = new JButton("Bin 11");
		 bin11.setActionCommand("12");
		 bin11.addActionListener(this);
		 bin11.setAlignmentX(JButton.TOP_ALIGNMENT);
		 contentPane.add(bin11);
		 
		 bin12 = new JButton("Bin 12");
		 bin12.setActionCommand("13");
		 bin12.addActionListener(this);
		 bin12.setAlignmentX(JButton.TOP_ALIGNMENT);
		 contentPane.add(bin12);
		 
		 filler = new JButton("Filler");
		 filler.setVisible(false);
		 filler.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		 contentPane.add(filler);
		 
		 filler = new JButton("Filler");
		 filler.setVisible(false);
		 filler.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		 contentPane.add(filler);
		 
		 filler = new JButton("Filler");
		 filler.setVisible(false);
		 filler.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		 contentPane.add(filler);
		 
		 filler = new JButton("Filler");
		 filler.setVisible(false);
		 filler.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		 contentPane.add(filler);
		 
		 restart = new JButton("Restart");
		 restart.setActionCommand("Restart");
		 restart.addActionListener(this);
		 restart.setAlignmentX(JButton.TOP_ALIGNMENT);
		 contentPane.add(restart);
		 
		 quit = new JButton("Quit");
		 quit.setActionCommand("Quit");
		 quit.addActionListener(this);
		 quit.setAlignmentX(JButton.TOP_ALIGNMENT);
		 contentPane.add(quit);
		 
		 filler = new JButton("Filler");
		 filler.setVisible(false);
		 filler.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		 contentPane.add(filler);
		 
		 filler = new JButton("Filler");
		 filler.setVisible(false);
		 filler.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		 contentPane.add(filler);
		 
		 filler = new JButton("Filler");
		 filler.setVisible(false);
		 filler.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		 contentPane.add(filler);

		 game.setContentPane(contentPane);
		 
		 
		 game.pack();
		 game.setVisible(false);
		 
		 //------------------------------------------
		 
		 //End Frame
		 winFrame = new JFrame("Stones");
		 winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 winPane = new JPanel();
		 winPane.setPreferredSize(new Dimension(250,100));
		 
		 winInfo = new JLabel();
		 winInfo.setVisible(true);
		 
		 winPane.add(winInfo);
		 
		 restart = new JButton("Restart");
		 restart.setActionCommand("Restart");
		 restart.addActionListener(this);
		 winPane.add(restart);
		 
		 quit = new JButton("Quit");
		 quit.setActionCommand("Quit");
		 quit.addActionListener(this);
		 winPane.add(quit);
		 
		 winFrame.setContentPane(winPane);
		 winFrame.pack();
		 winFrame.setVisible(false);

	}
	
	/**
	 * Handle button click action event
	 * Pre: none
	 * Post: Starts the game (for the first frame). In the second frame/window, the moves entered by the user are calculated and displayed. Then
	 * the opponent (computer) plays. It checks if the the player's/computer's pits are empty. If so, checks who won. If not, it repeats.
	 */
	public void actionPerformed(ActionEvent event) {
		String eventName = event.getActionCommand();
		String checkBin;
		
		if (twoPlayer.isSelected()) {
			twoPlay = 1;
		} else if (comp.isSelected()) {
			twoPlay = 0;
		}

		//Starts the board off with 2,3,4 or 5 stones. Displays the game board.
		if (eventName.equals("2 start")) {
			startStones(2);
			start.setVisible(false);
			game.setVisible(true);
		} else if (eventName.equals("3 start")) {
			startStones(3);
			start.setVisible(false);
			game.setVisible(true);
		} else if (eventName.equals("4 start")) {
			startStones(4);
			start.setVisible(false);
			game.setVisible(true);
		} else if (eventName.equals("5 start")) {
			startStones(5);
			start.setVisible(false);
			game.setVisible(true);
		}

		//Checks if the user clicked one of the 6 bins (to move)
		if (playerTurn == 0) {
			if (eventName.equals("1")||eventName.equals("2")||eventName.equals("3")||eventName.equals("4")||eventName.equals("5")||eventName.equals("6")) {
				array = player.getBoard();
				for (int x = 1; x < 7; x++) {
					
					checkBin = String.valueOf(x);
					if (eventName.equals(checkBin)) {
						
						//Checks if the bin has stones in it
						if (array[x] != 0){
							
							//If the method returns true (because the last stone landed in the player's home bin, the player will go again) else no.
							if (player.moveStones(x)) {
									again = 1;
								} else {
									again = 0;
									playerTurn = 1;
									info.setText("<html>Player 2's turn</html>");
									info.setVisible(true);
								}
						//If the player selected a bin with no stones, the player will go again.	
							} else {
								again = 2;
							}
						}

					}
				
				}
			}
			
			
			//Opponent Move
			if (playerTurn == 1) {
				//player.showBoard();
				updateStones();

				if (twoPlay == 0) {
					//Computer moves if win condition is not met
					do {
						opponentMove = ai();
					} while (player.opponentMoveStones(opponentMove));
					again = 0;
					playerTurn = 0;
					info.setText("<html>Player 1's turn</html>");
					info.setVisible(true);
					updateStones();
	
				} else if (twoPlay == 1) {
					//opponent move
					//Checks if the user clicked one of the 6 bins (to move)
					if (eventName.equals("8")||eventName.equals("9")||eventName.equals("10")||eventName.equals("11")||eventName.equals("12")||eventName.equals("13")) {
						array = player.getBoard();
						for (int x = 7; x < 14; x++) {
									
							checkBin = String.valueOf(x);
							if (eventName.equals(checkBin)) {
										
								//Checks if the bin has stones in it
								if (array[x] != 0){
											
									//If the method returns true (because the last stone landed in the player's home bin, the player will go again) else no.
									if (player.opponentMoveStones(x)) {
											again = 1;
											
										} else {
											again = 0;
											playerTurn = 0;
											info.setText("<html>Player 1's turn</html>");
											info.setVisible(true);
											updateStones();

										}
								//If the player selected a bin with no stones, the player will go again.	
								} else {
									again = 2;
								}
							}
												
						}
					}
				}
			}	
		
		//Check for win
		win = checkStones();	
		if (win > 0) {
			updateStones();
			switch(win) {
				case 2: end = "Player 1 wins!"; break;
				case 1: end = "Player 2 wins!"; break;
				case 0: end = "It's a draw!"; break;
			}
				
			//Display the "game over" window
			game.setVisible(false);
			winFrame.setVisible(true);
			winInfo.setText("<html>"+end+"<br>The score was:<br>"+array[7]+" - "+array[0]+"</html>");
		}
		
		//Changes information text in the board
		if (again == 1) {
			info.setText("Go again!");
			info.setVisible(true);
		} else if (again == 2) {
			info.setText("<html>You can only select bins<br> with stones in them.</html>");
			info.setVisible(true);
		}
			
		updateStones();
		
		//Restart the program when restart is clicked.
		if (eventName.equals("Restart")) {
			playerTurn = 0;
			winFrame.setVisible(false);
			game.setVisible(false);
			start.setVisible(true);
		}
		
		
		//Quit the Program when quit is clicked.
		if (eventName.equals("Quit")) {
			start.dispose();
			game.dispose();
			winFrame.dispose();
		}
	}
	
	 
	 /**
	  * Ai logic
	  * Pre: none
	  * Post: pulled a sneaky on you (: ai move returned
	  */
	public int ai() {
		array = player.getBoard();
		stoneAmt = 0;
		
		aiChoice = (int)((10-1+1)*Math.random()+1);
		//System.out.println("AICHOICE: " + aiChoice);
		
		//Selects the pit that has the last stone land in an empty bin on its side
		switch(aiChoice) {
			case 0: 
			for (int x = 8; x < 14; x++) {
					stoneAmt = array[x];
					
					//Subtracts the amount in the pit from the counter and "predicts" where the last stone will land
					if (stoneAmt-x >7) {
						if (array[stoneAmt-x] == 0) {
							
							//System.out.println("empty bin\nOPPONENT MOVE"+" PIT: "+(x-1)+" STONE AMOUNT: "+ (x-1));
							return(x);
						}
					}
				}
			aiChoice = 0;
			
			case 2: 
			for (int x = 8; x < 14; x++) {
				stoneAmt = array[x];
				//Subtracts the amount in the pit from the counter and "predicts" where the last stone will land
				if (stoneAmt-x == 7) {
					
					//System.out.println("own bin\nOPPONENT MOVE"+" PIT: "+(x-1)+" STONE AMOUNT: "+ (x-1));
					return(x);
				}
			} 	
			aiChoice = 0;
			
			case 3:
			case 4:
			case 5:
			default:
				
				//Random move (default option)
				aiLogic = (int)((HIGH - LOW + 1)*Math.random()+LOW);
				
				do {
					if (array[aiLogic] == 0) {
						aiLogic = (int)((HIGH - LOW + 1)*Math.random()+LOW);
					}
				} while (array[aiLogic] == 0);
				
				//System.out.println("deafult\nOPPONENT MOVE"+" PIT: "+ (aiLogic-1) + " STONE AMOUNT: "+ array[aiLogic]);
				return (aiLogic);
	
		}
	}
	
	/**
	 * Checks if the pits are empty (player or computer side)
	 * Pre: none
	 * Post: Calls the win method in the player class (from the player obj). returns -1 if no player won/tied, 1 if player 1 won, 2 if player 2 won, 0 if tied.
	 */
	private int checkStones() {
		
		return(player.win());
	}

	/**
	 * Updates display
	 * Pre: none
	 * Post: The display (board) gets updated with the number of stones.
	 */
	private void updateStones() {
		//Updates the stones. Stores the amount of stones of each bin into its corresponding variable.
		array = player.getBoard();
		stone1 = array[1];
		stone2 = array[2];
		stone3 = array[3];
		stone4 = array[4];
		stone5 = array[5];
		stone6 = array[6];
		stone7 = array[8];
		stone8 = array[9];
		stone9 = array[10];
		stone10 = array[11];
		stone11 = array[12];
		stone12 = array[13];	
		bin1.setText("<html>Bin 1<br>"+stone1+" stones</html>");
		bin2.setText("<html>Bin 2<br>"+stone2+" stones</html>");
		bin3.setText("<html>Bin 3<br>"+stone3+" stones</html>");
		bin4.setText("<html>Bin 4<br>"+stone4+" stones</html>");
		bin5.setText("<html>Bin 5<br>"+stone5+" stones</html>");
		bin6.setText("<html>Bin 6<br>"+stone6+" stones</html>");
		bin7.setText("<html>Bin 7<br>"+stone7+" stones</html>");
		bin8.setText("<html>Bin 8<br>"+stone8+" stones</html>");
		bin9.setText("<html>Bin 9<br>"+stone9+" stones</html>");
		bin10.setText("<html>Bin 10<br>"+stone10+" stones</html>");
		bin11.setText("<html>Bin 11<br>"+stone11+" stones</html>");
		bin12.setText("<html>Bin 12<br>"+stone12+" stones</html>");	
		playerHome.setText("<html>Player Pit<br>"+array[7]+" stones</html>");
		opponentHome.setText("<html>Opponent Pit<br>"+array[0]+" stones</html>");
		//player.showBoard();
	}
	
	
	/**
	 *Sets starting number of stones
	 *Pre: none
	 *Post: Updates the starting number of stones and updates it to the GUI.
	 */
	private void startStones(int stones) {
		player.setStones(stones);
		bin1.setText("<html>Bin 1<br>"+stones+" stones</html>");
		stone1 = stones;
		bin2.setText("<html>Bin 2<br>"+stones+" stones</html>");
		stone2 = stones;
		bin3.setText("<html>Bin 3<br>"+stones+" stones</html>");
		stone3 = stones;
		bin4.setText("<html>Bin 4<br>"+stones+" stones</html>");
		stone4 = stones;
		bin5.setText("<html>Bin 5<br>"+stones+" stones</html>");
		stone5 = stones;
		bin6.setText("<html>Bin 6<br>"+stones+" stones</html>");
		stone6 = stones;
		bin7.setText("<html>Bin 7<br>"+stones+" stones</html>");
		stone7 = stones;
		bin8.setText("<html>Bin 8<br>"+stones+" stones</html>");
		stone8 = stones;
		bin9.setText("<html>Bin 9<br>"+stones+" stones</html>");
		stone9 = stones;
		bin10.setText("<html>Bin 10<br>"+stones+" stones</html>");
		stone10 = stones;
		bin11.setText("<html>Bin 11<br>"+stones+" stones</html>");
		stone11 = stones;
		bin12.setText("<html>Bin 12<br>"+stones+" stones</html>");
		stone12 = stones;
		
		playerHome.setText("<html>Player Pit<br>0 stones</html>");
		opponentHome.setText("<html>Opponent Pit<br>0 stones</html>");
		//player.showBoard();
	}
	
	/**
	 * Displays GUI
	 * Pre: none
	 * Post: Create and show the GUI.
	 */
	 private static void runGUI() {
		 JFrame.setDefaultLookAndFeelDecorated(true);
		 Stones gamePlay = new Stones();
	 }
	 
	 /**
	  * Runs the program.
	  * Pre: none
	  * Post: Runs the program and calls runGUI()
	  */
	 public static void main(String[] args) {

		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
		 public void run() {
			 runGUI();
		 }
		 });
	 }
}
