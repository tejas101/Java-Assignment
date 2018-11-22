/*
 * ArtWork.java 
 * 
 * Version: 
 *     1 
 * 
 * Revisions: 
 *     0 
 *     
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *    
 */
/** 
 * This is a game which user will play to guess the correct word.
 * For every wrong guess, a part of the art work is reveled.
 * 
 * @author      Tejas Raval 
 */
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class ArtWork {
	/**
	  * The main program.
	  *
	  * @param    args    command line arguments (ignored)
	  */
    public static void main(String[] arguments) throws Exception {
        File IPFile = new File("input.txt");
        BufferedReader br1 = new BufferedReader(new FileReader(IPFile));
        String IPString = "";
        //Random number is used to select a word from iput.text file
        Random random = new Random();
        int randomInt = random.nextInt(10);
        int randomCounter = random.nextInt(randomInt);
        while ((IPString = br1.readLine()) != null) {
            randomCounter++;
            if (randomInt == randomCounter)
                playGame(IPString);

        }
        br1.close();
    }
    /**
     * playGame method will start the game and instructs the user
     * to play it.
     *
     * @param      quiz    Word to be guessed 
     */
    public static void playGame(String quiz) throws Exception {
        File file = new File("art.txt");
        //Art Work is drawn in test.txt file
        BufferedReader br = new BufferedReader(new FileReader(file));
        String finalDisplay = quiz;
        quiz = quiz.toLowerCase();
        int guessCounter = quiz.length();
        // Total 9 chances are given to the user.
        int counter = 0;
        char[] outputArray = new char[quiz.length()];
        char[] checkArray = quiz.toCharArray();
        for (int i = 0; i < outputArray.length; i++)
            outputArray[i] = '_';
        System.out.println("Welcome, Game Starts");
        String st;
        int lineCounter = 0;
        while (lineCounter < 9) {
            st = br.readLine();
            System.out.println(st);
            lineCounter++;
        }
        //Hidden/covered art work is shown on the screen
        for (int i = 0; i < outputArray.length; i++) {
            System.out.print(outputArray[i] + " ");
            /**
             *Initially, all blank spaces will be shown 
             *to the user 
             */
            
        }
        System.out.println("");

        while (counter != 9) {
            System.out.println("");
            System.out.println("Enter one letter");
            Scanner sc = new Scanner(System.in);
            Character aLetterIP = sc.next().charAt(0);
            char aLetter = Character.toLowerCase(aLetterIP);
            // System.out.println("Input is"+quiz.indexOf(aLetter));
            if (new String(checkArray).indexOf(aLetter) >= 0) {
                System.out.println("Correct guess");
                /**
                  *If the user makes a correct guess, then replace the "_"
                  *in the outputArray with the correct position of the letter
                  *and remove the letter from checkArray
                  * 
                 */
                while (new String(checkArray).indexOf(aLetter) >= 0) {
                    if (new String(checkArray).indexOf(aLetter) == 0)
                        outputArray[new String(checkArray).
                              indexOf(aLetter)] = Character.
                             toUpperCase(aLetterIP);
                    else
                        outputArray[new String(checkArray).
                                 indexOf(aLetter)] = aLetter;
                    checkArray[new String(checkArray).indexOf(aLetter)] = '_';
                }
                if (new String(outputArray).indexOf('_') < 0) {
                    System.out.println("You won.");
                    break;
                }
                for (int i = 0; i < outputArray.length; i++) {
                    System.out.print(outputArray[i] + " ");
                    //Show the complete word to the user.
                }
            } else {
            	//Code for the wrong guess
                System.out.println("NO buddy. Try again." + 
            (8 - counter) + " chance left");
                for (int i = 0; i < outputArray.length; i++) {
                    System.out.print(outputArray[i] + " ");
                }
                System.out.println("");
                counter++;
                lineCounter = 0;
                while (lineCounter < 9) {
                    st = br.readLine();
                    System.out.println(st);
                    lineCounter++;
                    /**Show the partial artwork every time
                      *this loop runs
                      */
                }
            }
        }
        System.out.println("Word was:");
        for (int i = 0; i < finalDisplay.length(); i++)
            System.out.print(finalDisplay.charAt(i));
        System.out.println("");
        System.out.println("Game over");
        br.close();
    } 	 
    


}