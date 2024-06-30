package Test;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
public class Bingo {
static int[][] computerbox = new int [5][5];//contains computer's numbers
static int[][] playerbox = new int [5][5];//contains player's numbers
static int[][] showcomputerbox= new int[5][5];//contains numbers chosen in computerbox
static int[][] showplayerbox= new int[5][5];//contains numbers chosen in playerbox
static List<List<Integer>>defaultbox = new ArrayList<>();
static int[] r_player=new int[5];
static int[] r_computer=new int[5];
static int[] c_computer=new int[5];
static int[] c_player=new int[5];
static int randomnumber=0;
static int scoreComputer=0;
static int scorePlayer=0;
static Random random = new Random();
static Scanner input = new Scanner(System.in);

/*methods
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

//method1-----------------------------------------------------------
		public static boolean checkIfIntegerIsInArray(int x, int [][] arr) {
			int flag=0;
					for(int k=0; k<arr.length; k++) {
						for(int l=0; l<arr[k].length; l++) {
							if(x==arr[k][l]) {
								flag++;
							}
						}
					}
					if(flag>0) {
						return true;
					}
					else
					{
						return false;
					}
				}
//method2-----------------------------------------------------------	
		public static void setComputerbox(){
					for(int i=0; i<5; i++) {
						for(int j=0; j<5; j++) {
							repeat:
							for(int k=0;k<1;k++) {
							int x=random.nextInt(defaultbox.size());
							if(defaultbox.get(x).size()==0) {
								defaultbox.remove(x);
								j--;
								continue repeat;
							}
							else {
							int y=random.nextInt(defaultbox.get(x).size());;
							computerbox[i][j]=defaultbox.get(x).get(y);
							defaultbox.get(x).remove(y);
							}
					 }
							
				}
			}
		}
//method3-----------------------------------------------------------
		public static void setPlayerbox() {
			for(int i=0; i<5; i++) {
						for(int j=0; j<5; j++) {
							playerbox[i][j]=input.nextInt();
							
						}
						List<Integer> check = new ArrayList<>();
						for(int k=0;k<5;k++) {
							check.add(playerbox[i][k]);
						}
						defaultbox.add(check);
					}
				}
//method4-----------------------------------------------------------
		public static void updateShowComputerBox(int x) {
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
						if(x==computerbox[i][j]){
							showcomputerbox[i][j]=x;
							break;
					}
					else{
						continue;
					}
				}
			}
		}
//method5-----------------------------------------------------------
		public static int generateRandomInt() {
			int rx=-1;
			int cx=-1;
			for(int a=0; a<5;a++) {
				if((r_computer[a]>rx)&&(r_computer[a]!=5)) {
					rx=a;
				}
				else {
					continue;
				}
			}
			for(int a=0; a<5;a++) {
				if((c_computer[a]>cx)&&(c_computer[a]!=5)) {
					cx=a;
				}
				else {
					continue;
				}
			}
				for(int b=0; b<5;b++) {
					if(rx>cx) {
						if(checkIfIntegerIsInArray(computerbox[rx][b],showcomputerbox)) {
							continue;
						}
						else {
							randomnumber=computerbox[rx][b];
							break;
						}
					}
					else {
						if(checkIfIntegerIsInArray(computerbox[b][cx],showcomputerbox)) {
							continue;
						}
						else {
							randomnumber=computerbox[b][cx];
							break;
						}
					}
				}
			return randomnumber;
	}
//method6-----------------------------------------------------------
		public static void showComputerBox() {
			System.out.println("------------");
			System.out.println("Showing Computer box:");
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
						System.out.print(showcomputerbox[i][j]+" ");
					}
				System.out.println();
				}
			System.out.println("------------");
		}
//method7-----------------------------------------------------------
		public static void updateShowPlayerBox(int x) {
			int breaker=0;
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
						if(playerbox[i][j]==x){
							showplayerbox[i][j]=x;
							breaker++;
							break;
					}
					else{
						continue;
					}
				}
				if(breaker>0) {
					break;
				}
			}
		}
//method8-----------------------------------------------------------
		public static void showPlayerBox() {
			System.out.println("------------");
			System.out.println("Showing Player box:");
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					if(playerbox[i][j]==showplayerbox[i][j]){
						System.out.print("|"+playerbox[i][j]+"|");
					}
					else {
						System.out.print(" "+playerbox[i][j]+" ");
					}
					}
				System.out.println();
				}
			System.out.println("------------");
		}
//method9-----------------------------------------------------------
		public static void updateBingo() {
			//row number
			for(int rowc=0; rowc<5;rowc++) {
			int countercomr=0;//counting elements in computer row
			for(int i=0; i<5; i++) {
				if(showcomputerbox[rowc][i]==0) {
						continue;
							}
				else {
					countercomr++;
				}
						}
						r_computer[rowc]=countercomr;
					}
			for(int rowp=0; rowp<5;rowp++) {
				int counterplar=0;//counting elements in player row
				for(int i=0; i<5; i++) {
					if(showplayerbox[rowp][i]==0) {
						continue;
							}
					else {
						counterplar++;
					}
						}
						
							r_player[rowp]=counterplar;
					}
			//column number
			for(int colp=0; colp<5;colp++) {
				int counterplac=0;//countingelementsinplayerrow
				for(int i=0; i<5; i++) {
					if(showplayerbox[i][colp]==0) {
						
							continue;
								}else
							{
								counterplac++;
							}
						}
							c_player[colp]=counterplac;
					}
			for(int colc=0; colc<5;colc++) {
				int countercomc=0;//countingelementsincomputercol
				for(int i=0; i<5; i++) {
					if(showcomputerbox[i][colc]==0) {
						continue;
							}
					else {
						countercomc++;
					}
						}
						
							c_computer[colc]=countercomc;
					}
			}
//method10-----------------------------------------------------------
		public static void checkScore() {
			System.out.println("--Checking Scores--");
			int score1=0;
			int score2=0;
			for(int i=0; i<5;i++){
				if(r_player[i]==5) {
					score2++;
				}
				else {
					continue;
				}
			}
			for(int i=0; i<5;i++){
				if(c_player[i]==5) {
					score2++;
				}
				else {
					continue;
				}
			}
			for(int i=0; i<5;i++){
				if(r_computer[i]==5) {
					score1++;
				}
				else {
					continue;
				}
			}
			for(int i=0; i<5;i++){
				if(c_computer[i]==5) {
					score1++;
				}
				else {
					continue;
				}
			}
			scoreComputer=score1;
			scorePlayer=score2;
		}
//method11-----------------------------------------------------------
		public static void player(int x) {
			updateShowComputerBox(x);
			updateShowPlayerBox(x);
			updateBingo();
			showPlayerBox();
			showComputerBox();
		}
//method12-----------------------------------------------------------
		public static void computer(int y) {
			System.out.println("Computer picked "+y);
			updateShowComputerBox(y);
			updateShowPlayerBox(y);
			updateBingo();
			showPlayerBox();
			showComputerBox();
		}
//method11------------------------------------------------------------
		public static void print2DintArray(int [][]array) {
			for(int i=0; i<array.length;i++) {
				for(int j=0; j<array[i].length;j++) {
					System.out.print(array[i][j]+" ");
				}
				System.out.println();
			}
		}
		/*
		 -------------------------------------------------------------
		 */
		public static void play() {
			System.out.println("Bingo Rules:");
			System.out.println("1: Only horizontal or vertical matches that  is row or column matches count, diagonals do not.");
			System.out.println("2: Player to reach BINGO faster wins, if both reach at the same time it's a draw");
			System.out.println("3: Please follow the instructions given throughout the game, enjoy!");
			System.out.println("Enter your 5X5 bingo chart: ");
			setPlayerbox();
			for(int i=0; i<5; i++) {
				int flag1=0;
				for(int j=0; j<5; j++) {
					int flag2=0;
					for(int k=0; k<5; k++) {
						for(int l=0; l<5; l++) {
							if(playerbox[i][j]==playerbox[k][l]) {
								flag2++;
							}
							if(flag2>1) {
								System.out.println("Repeated Nunber in Player Box.");
								System.out.println("Enter your 5X5 bingo chart again: ");
								break;
								
						}
					}
						if(flag2>1) {
							flag1=flag2;
							break;
						}
				}
					if(flag1>1) {
						break;
					}
					
			}
				if(flag1>1) {
					setPlayerbox();
					break;
				}
		}
			setComputerbox();
			do {
				System.out.println("Enter your number pick: ");
				String empty= input.nextLine();
				player(input.nextInt());
				updateBingo();
				checkScore();
				if(scorePlayer==5) {
					break;
				}
				System.out.println("Player Score: "+scorePlayer+"   Computer Score: "+scoreComputer);
				computer(generateRandomInt());
				updateBingo();
				checkScore();
				if(scoreComputer==5) {
					break;
				}
				System.out.println("Player Score: "+scorePlayer+"   Computer Score: "+scoreComputer);
				
			}
			while(scoreComputer<5&&scorePlayer<5);
			
			if((scoreComputer==5)&&(scorePlayer==5)) {
				System.out.println("It's a Draw");
			}
			else if(scoreComputer==5) {
				System.out.println("Computer wins");
			}
			else if(scorePlayer==5) {
				System.out.println("Player wins");
			}
			else {
				System.out.println("Err: Something went wrong.");
			}
			
		}
}
