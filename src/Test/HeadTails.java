package Test;
import java.util.Random;
import java.util.Scanner;
public class HeadTails {
	static Scanner input = new Scanner(System.in);
	static Random random = new Random();
	static int set_wickets=0;
	static int set_target=1;
	static int team1_run=0;
	static int team1_wickets=0;
	static int team2_run=0;
	static int team2_wickets=0;
	static int innings=0;
	static int team1_balls=0;
	static int team2_balls=0;
	static String[] choice=new String[2];
	/*
	 method1--------------------------------------------------------------
	 */
	public static int generateComputerMove() {
		int x=random.nextInt(6);
		x++;
		return x;
	}
	/*
	 method2-------------------------------------------------------------
	 */
	public static int generatePlayerMove() {
		System.out.println("Enter your move: ");
		int x= input.nextInt();
		if((x>0)&&(x<7)) { 
		}
		else {
			System.out.println("Please enter a valid move (1,2,3,4,5 or 6): ");
			x=input.nextInt();
		}
		return x;
	}
	/*
	 method3-------------------------------------------------------------
	 */
	public static boolean continueGameStatus2ndHalf() {
		if((set_wickets==team2_wickets)||(set_target<=team2_run)||(innings*6==team2_balls)){
			return false;
		}
		else {
			return true;
		}
	}
	/*
	 method4-------------------------------------------------------------
	 */
	public static void update1stHalfActivity(int batting_move, int balling_move) {
		team1_balls++;
		if(batting_move!=balling_move) {
			team1_run+=batting_move;
		}
		else if(batting_move==balling_move) {
			team1_wickets++;
		}
		if((team1_wickets==set_wickets)||(team1_balls==innings)) {
			set_target= team1_run+1;
		}
		System.out.println("Batsman: "+batting_move);
		System.out.println("Baller: "+balling_move);
	}
	/*
	 method5-------------------------------------------------------------
	 */
	public static void update2ndHalfActivity(int batting_move, int balling_move) {
		team2_balls++;
		if(batting_move!=balling_move) {
			team2_run+=batting_move;
		}
		else if(batting_move==balling_move) {
			team2_wickets++;
		}
		System.out.println("Batsman: "+batting_move);
		System.out.println("Baller: "+balling_move);
	}
	/*
	 method6-------------------------------------------------------------
	 */
	public static void setWicketsInnings() {
		System.out.println("Please set wickets: ");
		int x=input.nextInt();
		System.out.println("Please set innings: ");
		int y=input.nextInt();
		set_wickets=x;
		innings=y;
	}
	/*
	 method7-------------------------------------------------------------
	 */
	public static boolean playerWonCoinToss() {
		System.out.println("------------------Tossing coin------------------");
		int x=random.nextInt(2);
		if(x==0) {
			return false;
		}
		else {
			return true;
		}
	}
	/*
	 method8-------------------------------------------------------------
	 */
	public static boolean computerChoseToBat() {
		int x=random.nextInt(2);
		if(x==0) {
			return true;
		}
		else {
			return false;
		}
	}
	/*
	 method9-------------------------------------------------------------
	 */
	public static void scoreCard1stHalf() {
		System.out.println("Run: "+team1_run);
		System.out.println("Wickets: "+team1_wickets);
		System.out.println("Current over: "+((team1_balls/6)+1));
		System.out.println("Balls left : "+(innings*6-(team1_balls)));
		System.out.println("Overs left: "+ (innings-(team1_balls/6)-1));
		System.out.println("---------");
	}
	/*
	 method10-------------------------------------------------------------
	 */
	public static void scoreCard2ndHalf() {
		System.out.println("Run: "+team2_run);
		System.out.println("Wickets: "+team2_wickets);
		if(set_target>team2_run) {
		System.out.println("Runs Needed: "+(set_target-team2_run)+" run");
		}
		System.out.println("Current over: "+((team2_balls/6)+1));
		System.out.println("Balls left : "+(innings*6-(team2_balls)));
		System.out.println("Overs left: "+ (innings-(team2_balls/6)-1));
		System.out.println("---------");
	}
	/*
	 -------------------------------------------------------------
	 */
	public static void play(){
				setWicketsInnings();
				if(playerWonCoinToss()) {
					System.out.println("You won the toss.\nChoose between batting or balling.\nWhat do you want to do first?");
					String empty=input.nextLine();
					choice[0]=input.next();
					if(choice[0].equalsIgnoreCase("balling")) {
						choice[1]="batting";
						choice[0]="balling";
					}
					else {
						choice[1]="balling";
						choice[0]="batting";
					}
				}
				else {
					System.out.println("You lost the toss.");
					if(computerChoseToBat()){
						System.out.println("Computer chose to bat first.");
							choice[1]="batting";
							choice[0]="balling";
						}
						else {
							System.out.println("Computer chose to ball first.");
							choice[1]="balling";
							choice[0]="batting";
						}
					}
				if(choice[0]=="balling") {
					//team1 is computer
					do {
						System.out.println("Computer's batting.");
						update1stHalfActivity(generateComputerMove(),generatePlayerMove());
						System.out.println("---------\nBatting team stats: ");
						scoreCard1stHalf();
						}
					while((set_wickets>team1_wickets)&&(innings*6>team1_balls));
						System.out.println("------------------Your turn to bat.------------------\nTarget: "+set_target);
					do {
						System.out.println("You are batting.");
						update2ndHalfActivity(generatePlayerMove(),generateComputerMove());
						System.out.println("---------\nBatting team stats: ");
						scoreCard2ndHalf();
						}
					while(continueGameStatus2ndHalf()==true);
				}
				else 
				{
					do {
						System.out.println("You are batting.");
						update1stHalfActivity(generatePlayerMove(),generateComputerMove());
						System.out.println("---------\nBatting team stats: ");
						scoreCard1stHalf();
						}
					while((set_wickets>team1_wickets)&&(innings*6>team1_balls));
						System.out.println("------------------Computer's turn to bat.------------------\nTarget: "+set_target);
					do {
						System.out.println("Computer's batting.");
						update2ndHalfActivity(generateComputerMove(),generatePlayerMove());
						System.out.println("---------\nBatting team stats: ");
						scoreCard2ndHalf();
						}
					while(continueGameStatus2ndHalf()==true);
				}
				
				if(choice[0]=="balling") {
						if((team2_wickets<set_wickets)&&(team2_run>=set_target)) {
							System.out.println("------------------You won by "+(set_wickets-team2_wickets)+" wicket.------------------");
						}
						else if(team2_run!=team1_run) {
							System.out.println("------------------Computer won by "+(set_target-team2_run)+" run.------------------");
						}
						else {
							System.out.println("It's a Draw!");
						}
					}
				else {
					if((team2_wickets<set_wickets)&&(team2_run>=set_target)){
						System.out.println("------------------Computer won by "+(set_wickets-team2_wickets)+" wicket.------------------");
					}
					else if(team2_run!=team1_run) {
						System.out.println("---------------------------You won by "+(set_target-team2_run)+" run.------------------");
					}
					else {
						System.out.println("------------------It's a Draw!------------------");
					}
				}
					
		}
}
