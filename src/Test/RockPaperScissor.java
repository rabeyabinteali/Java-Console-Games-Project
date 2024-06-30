package Test;
import java.util.Random;
import java.util.Scanner;
public class RockPaperScissor {
static String [] moves= new String[] {"rock","paper","scissor"};
static int scoreComputer=0;
static int scorePlayer=0;
static int gamePoint=0;
static Scanner input = new Scanner(System.in);
static Random random = new Random();
//method1-----------------------------------------------------------
	public static void setGamePoint() {
		System.out.println("Set target: ");
		gamePoint=input.nextInt();
	}
//method2-----------------------------------------------------------
	public static String generateComputerMove() {
		int x= random.nextInt(3);
		System.out.println("Computer's move: "+moves[x]);
		return moves[x].toLowerCase();
	}
//method3-----------------------------------------------------------
	public static String generatePlayerMove() {
		System.out.println("Enter your move: ");
		String move= input.next();
		for(int i=0; i<3;i++) {
			if(move.equalsIgnoreCase(moves[i])) {
				move=moves[i];
				}
			}
		return move.toLowerCase();
	}
//method4-----------------------------------------------------------
	public static void scoreCard() {
		System.out.println("Player Score: "+scorePlayer);
		System.out.println("Points needed to win for Player: "+(gamePoint-scorePlayer));
		System.out.println("Computer Score "+scoreComputer);
		System.out.println("Points needed to win for Computer: "+(gamePoint-scoreComputer));
	}
//method5-----------------------------------------------------------
	public static void updateScore(String movePlayer,String moveComputer) {
		if(moveComputer== moves[0]) {
			//moveCoumpter is Rock
			if(movePlayer==moveComputer) {
				System.out.println("No one scores!");
			}
			else if(movePlayer== moves[1]) {
				scorePlayer++;
				System.out.println("Player scores!");
			}
			else if(movePlayer==moves[2]) {
				scoreComputer++;
				System.out.println("Computer scores!");
			}
			else {
				System.out.println("Invalid input.");
			}
		}
		else if(moveComputer==moves[1]) {
			//moveCompuer is Paper
			if(movePlayer==moveComputer) {
				System.out.println("No one scores!");
			}
			else if(movePlayer== moves[2]) {
				scorePlayer++;
				System.out.println("Player scores!");
			}
			else if(movePlayer==moves[0]) {
				scoreComputer++;
				System.out.println("Computer scores!");
			}
			else {
				System.out.println("Invalid input.");
			}
		}
		else if(moveComputer==moves[2]) {
			//moveCompuer is Scissor
			if(movePlayer==moveComputer) {
				System.out.println("No one scores!");
			}
			else if(movePlayer== moves[0]) {
				scorePlayer++;
				System.out.println("Player scores!");
			}
			else if(movePlayer==moves[1]) {
				scoreComputer++;
				System.out.println("Computer scores!");
			}
			else {
				System.out.println("Invalid input.");
			}
		}
	}
	/*
	 -------------------------------------------------------------
	 */
	public static void play() {
		System.out.println("Rules: ");
		System.out.println("1. Type 'rock', 'paper' or 'scissor' to make your move. It is NOT case sensitive.");
		System.out.println("2. First one to scoare target wins.");
		System.out.println("------------------------------Good Luck----------------------------");
		setGamePoint();
		do {
		updateScore( generatePlayerMove(),generateComputerMove());
		scoreCard();
		}
		while((scoreComputer<gamePoint)&&(scorePlayer<gamePoint));
		System.out.println("------------------------------Game Ends-----------------------------");
		if(scoreComputer>scorePlayer) {
			System.out.println("Computer wins!");
		}
		else {
			System.out.println("Player wins!");
		}
	}
}
