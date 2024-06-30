package Test;
import java.util.Scanner;
public class Olympics {
	public static void main(String [] args) {
		Scanner input= new Scanner(System.in);
		HeadTails headtail= new HeadTails();
		Hangman hangman = new Hangman();
		RockPaperScissor rockpaperscissor = new RockPaperScissor();
		Bingo bingo = new Bingo();
		System.out.println("---------------Welcome-------------------");
		System.out.println("You can choose to play one of the following games-");
		System.out.println("1. Head-Tails(HT): For cricket lovers, a blast of childhood nostalgia.");
		System.out.println("2. Bingo(B): For table lovers, a throwback to school time games");
		System.out.println("3. Hangman(H): For movie fanatics, the classic guessing game.");
		System.out.println("4. Rock-Paper-Scissor(RPS): For everyone, the all time classic");
		System.out.println("Please Choose Your Preferred Game (type in game name or it's initials): ");
		String choice = input.next();
		if((choice.equalsIgnoreCase("HT"))||(choice.equalsIgnoreCase("HeadTails"))||(choice.equalsIgnoreCase("Head-Tails"))) {
			headtail.play();
		}
		else if((choice.equalsIgnoreCase("B"))||(choice.equalsIgnoreCase("bingo"))) {
			bingo.play();
		}
		else if((choice.equalsIgnoreCase("H"))||(choice.equalsIgnoreCase("hangman"))) {
			hangman.play();
		}
		else if((choice.equalsIgnoreCase("RPS"))||(choice.equalsIgnoreCase("rockpaperscissor"))||(choice.equalsIgnoreCase("rock-paper-scissor"))||(choice.equalsIgnoreCase("rock-paperscissor"))||(choice.equalsIgnoreCase("rockpaper-scissor")))
		{
		rockpaperscissor.play();
		}
		
	}
}
