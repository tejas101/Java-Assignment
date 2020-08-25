/* MyScanner.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 *This program works as a wrapper for Java's Scanner class.
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyScanner {
	MyScanner(String fileName) throws FileNotFoundException{
		File file = new File(fileName);
		sc = new Scanner(file);
		initCounter(fileName);	
	}
	Scanner sc;
	static int lineCount=0;
	static int charCount=0;

	static void initCounter(String fileName) throws FileNotFoundException {
		/**
		 * This method calculates line and the character count.
		 */
		File file = new File(fileName);
		Scanner scInit = new Scanner(file);
		int count=0;
		String currentLine="_";
		while(currentLine!=null && currentLine.length()>0) {
			if(scInit.hasNextLine()){
				currentLine=scInit.nextLine();
				++lineCount;
				charCount+=currentLine.length();
			}
			else {
				break;
			}
		}
		scInit.close();
	}

	public boolean hasNext() {
		return sc.hasNext();

	}
	public void close() {
		sc.close();

	}
	public String nextLine() {
		String empty="";
		if(sc.hasNextLine())
			return sc.nextLine();

		return empty;

	}
	public int getLineCount() {
		return lineCount;

	}

	public int getCharacterCount() {
		return charCount;

	}
	public static void main(String args[]) throws FileNotFoundException {
		/**
		 * The main Program calls the openConnection function.
		 * 
		 * @param args Name of the input file.
		 */
		String fileName=args[0];

		MyScanner ms = new MyScanner(fileName);
		Boolean hasNext=ms.hasNext();
		System.out.println("ms.hasNext():"+hasNext);

		String nextLine1=ms.nextLine();
		System.out.println("ms.nextLine():"+nextLine1);
		System.out.println("ms.getLineCount():"+ms.getLineCount());
		System.out.println("ms.getCharacterCount():"+ms.getCharacterCount());
		String nextLine2=ms.nextLine();
		System.out.println("ms.nextLine():"+nextLine2);
		String nextLine3=ms.nextLine();
		System.out.println("ms.nextLine():"+nextLine3);
		ms.close();

		System.out.println("------------Test Cases----------------");
		File file = new File(fileName);
		Scanner sc = new Scanner(file);

		if(hasNext==sc.hasNext())
		{System.out.println("hasNext() test case passed");}
		else { System.out.println("hasNext() test case failed");}

		if(nextLine1.equals(sc.nextLine()))
		{System.out.println("nextLine() test case passed for line number 1");}
		else {System.out.println("hasNext() test case failed for line number 1");}

		if(nextLine2.equals(sc.nextLine()))
		{System.out.println("nextLine() test case passed for line number 2");}
		else {System.out.println("hasNext() test case failed for line number 2");}

		if(nextLine3.equals(sc.nextLine()))
		{System.out.println("nextLine() test case passed for line number 3");}
		else {System.out.println("hasNext() test case failed for line number 3");}


		sc.close();


	}

}
