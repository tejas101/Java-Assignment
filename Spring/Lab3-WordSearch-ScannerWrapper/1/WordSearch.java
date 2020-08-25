/* WordSearch.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 *This program is WordSearch program which search words from  a puzzle
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Work {
	boolean flag = true; //will be just used to initialized the Char array
	static boolean doneflag = true; //to check if we got the word
	Scanner sc;
	static int n = 0;
	static char[][] puzz;
	int start = 0;
	static String revData = "";
	static int startGroup = 0;
	static int endGroup = 0;
	//read the text file and construct the 2D matrix
	Work() throws FileNotFoundException {
		try {
			System.out.println("Enter the file name: ");
			String fileName = "";
			sc = new Scanner(System.in);
			fileName = sc.nextLine();
			if (fileName.equals("exit")) {
				System.out.println("Exiting the code with exitcode 1");
				System.exit(1);
			}
			File file = new File(fileName);
			sc = new Scanner(file);
			String temp = "-";
			while (temp != null && temp.length() > 0) {
				temp = "";
				if (sc.hasNextLine()) {
					if (flag) {
						temp = sc.next();
						n = temp.length();
						puzz = new char[n][n];
						fillIt(temp, start++, 0);
						flag = false;

					} else {
						temp = sc.next();
						fillIt(temp, start++, 0);

					}

				}
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("File  was not found! Try again.");
		}

	}

	public void fillIt(String temp, int i, int j) {
		/***
		 * This method take the a single line from the txt file and fills
		 * it in the puzzle matrix.
		 */
		//as I is row and j is col, hence iterate over J
		for (int k = j; k < n; k++) {
			puzz[i][k] = temp.charAt(k);

		}
	}

	public void takeIP() {
		/***
		 * This method take the input from the user. Its the word which 
		 * needs to be searched in the puzzle's txt file.
		 */
		String data = "0";
		while (true) {
			System.out.println("Enter the word to be searched");
			sc = new Scanner(System.in);
			data = sc.nextLine();
			if (data.equals("exit")) {
				System.out.println("exit pressed, exiting the program with exit code 2");
				System.exit(2);
			}
			System.out.println("Entred word is " + data);
			if (data.length() > n) {
				System.out.println("No word found");
			} else {
				initialSearch(data);

			}
		}

	}

	public void initialSearch(String data) {
		/***
		 * This is the vital part of the program. Here, searching starts here for 
		 * all direction- Horizontal/Vertical/Digonal(forward and backward for all ) 
		 */
		String buffer = "";
		revData = ""; //reverse the word given by user to search (used for backward search)
		boolean b;
		//reverse the input data for backward search
		for (int k = data.length() - 1; k > -1; k--) {
			revData += data.charAt(k);
		}

		//horizontal search- forward
		for (int i = 0; i < puzz.length; i++) {
			for (int j = 0; j < puzz.length && doneflag; j++) {
				buffer += puzz[i][j];
			}


			if (checkPattern(buffer, data)) {
				System.out.println(data + " found at row " + i);
				doneflag = true;
				return;
			}

			//horizontal search-backward

			if (checkPattern(buffer, revData)) {
				System.out.println(data + " found at row " + i + " in reverse direction");
				doneflag = true;
				return;
			}
			buffer = ""; //reset buffer
		}

		//Vertical search
		for (int j = 0; j < puzz.length; j++) {
			for (int i = 0; i < puzz.length && doneflag; i++) {
				buffer += puzz[i][j];
			}
			//vertical-forward
			if (checkPattern(buffer, data)) {
				System.out.println(data + " found at col " + j);
				doneflag = true;
				return;
			}
			//vertical-reverse
			if (checkPattern(buffer, revData)) {
				System.out.println(data + " found at col " + j + " reverse direction");
				doneflag = true;
				return;
			}
			buffer = "";
		}
		//digonal down to up left to right
		//lower triangle I guess
		for (int i = puzz.length - 1; i > 0; i--) {

			for (int j = 0, k = i; k <= puzz.length - 1; j++, k++) {
				buffer = buffer + puzz[k][j];

			}

			if (checkPattern(buffer, data)) {

				System.out.println(data + " found at col " + startGroup + ". Read in" +
						" South-East direction ");
				doneflag = true;
				return;
			}

			if (checkPattern(buffer, revData)) {

				System.out.println(data + " found at col " + (n - 1 - i) + ". Read in" +
						" North-West Direction");
				doneflag = true;
				return;
			}

			buffer = "";
		}

		//digonal down to up left to right
		//upper triangle I guess
		for (int i = 0; i <= puzz.length - 1; i++) {

			for (int j = 0, k = i; k <= puzz.length - 1; j++, k++) {
				buffer = buffer + puzz[j][k];

			}

			if (checkPattern(buffer, data)) {
				System.out.println(data + " found at  row " + startGroup + ". Read " +
						"South-West duirection");
				doneflag = true;
				return;
			}

			if (checkPattern(buffer, revData)) {
				System.out.println(data + " found at  row " + (endGroup - 1) + ". Read " +
						"in North-West  direction");
				doneflag = true;
				return;
			}

			buffer = "";

		}

		//Digonal right to left
		//upper triangle
		for (int i = n - 1; i > -1; i--) {
			for (int j = 0, k = i; j <= i; j++, k--) {

				buffer = buffer + puzz[k][j];
			}

			if (checkPattern(buffer, data)) {
				System.out.println(data + " found at  row " + (endGroup - 1) + "Read" +
						" in North-East direction");
				doneflag = true;
				return;
			}

			if (checkPattern(buffer, revData)) {
				System.out.println(data + " found at  col " + i + ".Read in " +
						"South-West direction");
				doneflag = true;
				return;
			}

			buffer = "";
		}
		//Digonal right to left
		//lower triangle
		for (int k = n - 2; k >= 0; k--) {
			for (int j = 0; j <= k; j++) {
				int i = k - j;

				buffer = buffer + puzz[n - j - 1][n - i - 1];
			}

			if (checkPattern(buffer, data)) {
				System.out.println(data + " found at col " + (n - 1 - k) + ". Read " +
						"in north-east direction ");
				doneflag = true;
				return;
			}

			if (checkPattern(buffer, revData)) {
				System.out.println(data + " found at row " + (n - 1 - k) + ". Read " +
						"in South-West direction ");
				doneflag = true;
				return;
			}

			buffer = "";
		}

		//final check
		if (doneflag) {
			System.out.println(data + " not found");
		}
	}

	public boolean checkPattern(String buffer, String data) {

		Pattern checkRegex = Pattern.compile("^.*(" + data + ").*$");
		Matcher regexMatcher = checkRegex.matcher(buffer);
		boolean b = regexMatcher.find();

		if (b) {
			startGroup = regexMatcher.start(1);
			endGroup = regexMatcher.end(1);
			doneflag = false;
			return true;
		}

		return false;
	}
}
public class WordSearch {
	/**
	 *The main program.
	 *
	 */
	public static void main(String args[]) throws FileNotFoundException {

		while (Work.puzz == null) {
			Work w = new Work();

			if (Work.puzz == null)
				continue;
			System.out.println("Display Puzzle");

			for (int i = 0; i < Work.puzz.length; i++) {
				for (int j = 0; j < Work.puzz.length; j++) {
					System.out.print(Work.puzz[i][j] + " ");
				}
				System.out.println("");
			}
			w.takeIP();
		}

	}
}