package Test;
import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
public class Hangman{
	static Random random = new Random();
	static Scanner input = new Scanner(System.in);
	static int lives=7;
	static int nameCharCount=0;
	static int charCount=0;
	static int correctCharCount=0;
	static char[] guessedCharacters= new char[36];
	public static ArrayList<String> ReadFromFile(String filePath) throws IOException{
		 	ArrayList<String> arr= new ArrayList<String>();
		    FileInputStream file= null;
		    InputStreamReader filein=null;
		    BufferedReader fileReader= null;
		    String s=null;
		    try{
		    file= new FileInputStream(filePath);
		    filein= new InputStreamReader(file);
		    fileReader= new BufferedReader(filein);
		    }
		    catch(FileNotFoundException e){
		    System.out.println("File Not Found");
		    }
		    do{
		        try {
		         arr.add(fileReader.readLine());
		        } catch (IOException ex) {
		            Logger.getLogger(Hangman.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }
		    while(fileReader.ready()==true);
		        try {
		            filein.close();
		            file.close();
		        } catch (IOException ex) {
		            Logger.getLogger(Hangman.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    return arr;
		    }
	public static String randomNameGenerator() {
		String name=null;
		try {
			ArrayList<String> movies = ReadFromFile("C:\\Users\\user\\eclipse-workspace\\Games\\src\\Test\\MovieName.txt");
			int x=random.nextInt(movies.size());
			name=movies.get(x);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		int y=0;
		for(char i='a'; i<='z'; i++) {
			if(name.toLowerCase().contains(Character.toString(i))) {
				y++;
			}
		}
		for(char j='0'; j<='9';j++) {
			if(name.toLowerCase().contains(Character.toString(j))) {
				y++;
			}
		}
		nameCharCount=y;
		return name.toLowerCase();
		
	}
	public static void printGappedString(String movieName, String guessed) {
		char [] nameUpdate = movieName.toCharArray();
		char [] nameCopy= movieName.toCharArray();
		char [] chars=null;
		if(guessed!=null) {
		 chars = guessed.toCharArray();
		}
		for(int j=0; j<nameUpdate.length;j++) {
			if((nameCopy[j]=='a')||(nameCopy[j]=='e')||(nameCopy[j]=='o')||(nameCopy[j]=='i')||(nameCopy[j]=='u')){
				nameUpdate[j]='*';
			}
			else if(nameCopy[j]==' ') {
				nameUpdate[j]='/';
			}
			else {
				nameUpdate[j]='-';
			}
		}
		if(guessed !=null) {
			for(int i=0; i<chars.length;i++) {
				for(int j=0; j<nameCopy.length;j++) {
					if((chars[i]==nameCopy[j])&&(nameCopy[j]!=' ')){
						nameUpdate[j]=chars[i];
					}
				}
			}
		}
		for(int k=0; k<nameUpdate.length;k++) {
			System.out.print(nameUpdate[k]+" ");
		}
		System.out.println();
	}
	public static char playerEntry() {
		System.out.println("Enter your guess: ");
		String ch= input.next();
		return ch.toLowerCase().charAt(0);
	}
	public static boolean statusCheck(String guess, String name) {
		try {
		charCount= (guess.length())/3;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		if((lives>0)&&(correctCharCount<nameCharCount)) {
			return true;
		}
		else {
			return false;
		}
	}
	public static void play() {
			String name= randomNameGenerator();
			ArrayList<Character> guess = new ArrayList<Character>();
			System.out.println("Rules:");
			System.out.println("1. The stars(*) indicate vowels, the slashes(/) represent spaces.");
			System.out.println("2. Player gets seven lives. Each wrong guess costs a live!");
			System.out.println("3. Player is responsible for choosing the same wrong option twice. Player will loose a live.");
			System.out.println("-------------Good Luck! Here's your movie name------------------");
			printGappedString(name, guess.toString());
			do {
				char ch=playerEntry();
				guess.add(ch);
				if(name.contains(Character.toString(ch))==false) {
					lives--;
					System.out.println("Wrong guess!");
				}
				else {
					correctCharCount++;
					System.out.println("Right guess!");
				}
				System.out.println("-------------------------------------");
				System.out.println("Already guessed: ");
				for(int i=0; i<guess.size(); i++) {
					System.out.print(guess.get(i) +" ");
				}
				System.out.println("\n-------------------------------------");
				System.out.println();
				printGappedString(name, guess.toString());
				statusCheck(guess.toString(),name);
				System.out.println("Lives remaining: "+lives);
			}
			while(statusCheck(guess.toString(),name)!=false);
			System.out.println("-------------------------------------");
			System.out.println("End of the game.\nThe name was '"+name+"'\n---------------------------------------");
			if(correctCharCount==nameCharCount) {
				System.out.println("Player guessed correctly. Player wins!");
			}
			else {
				System.out.println("Player couldn't guess the name. Player lost!");
			}
	}
}
