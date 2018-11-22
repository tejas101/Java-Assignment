/*
 * RegExTest.java 
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
 *     
 *    
 */
/** 
  * This program performs String operations and 
  * uses Regular expression to match string, find 
  * palindromes etc
  * 
  * @author      Tejas Raval 
  */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.*;
public class RegExTest {
	/**
      *testOwn1 this method will check the if "a" is the part
      *of the string or word has atleast 1 "a" by using 
      *String class
      *@param      aString    String which needs to be checked.
      *@param      comment1   Message to show on screen if 
      *                        atleast 1 a is present
      *@param      comment2   Message to show on screen if 
      *                        a is part of the String.
      */

    public static void testOwn1(String aString,
    		String comment1, String comment2) {
        if (aString.indexOf('a') >= 0) {
            System.out.println("Own Test 1:");
            System.out.println("\tInput line: " + aString);
            System.out.println("\t" + comment1);
            System.out.println("\t" + comment2);

        }
    }
    /**
      *testPattern1 this method will check the if "a" is the part
      *of the string or word has atleast 1 "a" by
      *using Regular expressions
      *@param      regEx      Regular expression for matching
      *@param      aString    String which needs to be checked.
      *@param      comment1   Message to show on screen if 
      *                        atleast 1 a is present
      *@param      comment2   Message to show on screen if 
      *                        a is part of the string.
      */
    public static void testPattern1(String regEx,
    		String aString, String comment1, String comment2) {

        Pattern checkRegex = Pattern.compile(regEx);
        Matcher regexMatcher = checkRegex.matcher(aString);
        boolean b = regexMatcher.find();

        if (b) {
            System.out.println("RegEx Test 1:");
            System.out.println("\tInput line: " + aString);
            System.out.println("\t Pattern:" + regEx);
            System.out.println("\t" + comment1);
            System.out.println("\t" + comment2);
        }
    }
    /**To find the palindrome at the start and
      *end of a String, we have created 2 methods
      *checkOnLeft and checkRight. As the name 
      *suggest, Left works from the start of the 
      *String while Right works on the end.
      *Initially, left will start by taking 2 chars from 
      *the string eg: Left will take 1st 2 chars. Then, it
      *will check if these 2 characters are palindrome.
      *If yes then the Right method will be called to do
      *the same thing from the end. If the left methods
      *fails in the first iteration, then it takes first 
      *3 Characters and checks for the palindrome. If no
      *palindrome is detected, then next 4 chars will be
      *processed and this will be done till string ends.
      *Checking of Palindroms is done by 2 ways.
      *1)By String class(checkOnLeftOwn() and checkOnRightOwn())
      *The string in question is divided in 2 parts, ie
      *Stored in 2 strings S1 and S2.Later,
      *one of the string is reversed and is matched with 
      *the other. If they are euqal, we get a palindrome.
      *2nd method is explain later.
      */
    /**
      *testOwn2 This method will check the if a palindrome
      *is anchored at start and end of the String by using
      *String class
      *@param      aString   String which needs to be checked.
      *@param      comment   Message to show on screen if 
      *                      palindrome is acncored at start
      *                      or end of the string
      */

    public static void testOwn2(String aString, 
    		String comment) {
        if (aString.length() >= 2 && aString.charAt(0) != ' ') {
            if (checkOnLeftOwn(aString) && 
            		checkOnRightOwn(aString)) {

                System.out.println("\t" + comment);

            }
        }
    }
    /**
      *checkOnLeftOwn this method will check the a palindrome
      *is present at the start of the string using String
      *class
      *
      *@param      test   String for checking palindrome
      *                   at start.
      *@return            true, if palindrome is present at
      *                   the start of the String    
      */
    public static Boolean checkOnLeftOwn(String test) {
        String dispaly = test;
        test = test.toLowerCase();
        int start = 0;
        String part = "";
        for (int i = 0; i < test.length() - 1; i++) {
            for (int k = 0; k <= i + 1; k++) {
                part = part + test.charAt(start++);

            }
            if (part.length() % 2 == 1) {
                String s1 = part.substring(0, (part.length() 
                		/ 2));
                String s2 = part.substring((part.length() / 2)
                		+ 1, part.length());
                String revString = "";
                for (int p = s2.length() - 1; p >= 0; p--) {
                    revString = revString + s2.charAt(p);
                }
                if (s1.equals(revString)) {
                    System.out.println("Own Test 2:");
                    System.out.println("\tInput line: " + 
                    dispaly);
                    System.out.println("\tGot a Palindrome at"
                    		+ " start: " + part);
                    return true;

                }
            } else {
                String s1 = part.substring(0, (part.length() / 
                		2));
                String s2 = part.substring((part.length() / 2),
                		part.length());
                String revString = "";
                for (int p = s2.length() - 1; p >= 0; p--) {
                    revString = revString + s2.charAt(p);
                }
                if (s1.equals(revString)) {
                    System.out.println("Own Test 2:");
                    System.out.println("\tInput line: " + dispaly);
                    System.out.println("\tGot a palindrome "
                    		+ "at start: " + part);
                    return true;

                }
            }
            start = 0;
            part = "";
        }
        return false;

    }
    /**
      *checkOnRightOwn this method will check the a palindrome
      *is present at the end of the string using String
      *class
      *
      *@param      test   String for checking palindrome
      *                   at end.
      *@return            true, if palindrome is present at
      *                   the end of the string                  
      */
    public static Boolean checkOnRightOwn(String test) {
        String dispaly = test;
        test = test.toLowerCase();
        int end = test.length() - 1;
        String part = "";
        for (int i = 0; i < test.length() - 1; i++) {
            for (int k = 0; k <= i + 1; k++) {
                part = test.charAt(end--) + part;

            }
            if (part.length() % 2 == 1) {
                String s1 = part.substring(0, (part.length() / 2));
                String s2 = part.substring((part.length() / 2) + 1,
                		part.length());
                String revString = "";
                for (int p = s2.length() - 1; p >= 0; p--) {
                    revString = revString + s2.charAt(p);
                }
                if (s1.equals(revString)) {
                    System.out.println("\tGot a palindrome on end: " + part);
                    return true;

                }
            } else {
                String s1 = part.substring(0, (part.length() 
                		/ 2));
                String s2 = part.substring((part.length() / 2), 
                		part.length());
                String revString = "";
                for (int p = s2.length() - 1; p >= 0; p--) {
                    revString = revString + s2.charAt(p);
                }
                if (s1.equals(revString)) {
                    System.out.println("\tGot a Palindrome on"
                    		+ " end: " + part);
                    return true;

                }
            }
            end = test.length() - 1;
            part = "";
        }
        return false;


    }
    /**2)Regex(checkOnLeftPattern() and checkOnRightPattern())
      *The string in question is divided in 2 parts, ie
      *Stored in 2 strings S1 and S2. Later, Regex operators
      *^ and $ are used appropriately to match a regex.
      *For eg: String is pqbqp. Then the regex, to match the 
      *end part will be qp$ while regex to match the start part
      *will be ^pq.
      */
    /**
      *testPattern2 This method will check the if a palindrome
      *is anchored at start or end of the string by using
      *Regular expression
      *@param      test   String which needs to be checked.
      *@param      comment   Message to show on screen if 
      *            palindrome is anchored at start or end 
      *            of the string
      */
    public static void testPattern2(String test, String comment) {
        if (checkOnLeftPattern(test) && checkOnRightPattern(test)) {

            System.out.println("\t" + comment);

        }
    }
    /**
     *checkOnLeftPattern this method will check the a palindrome
     *is present at the start of the string using Regular
     *expression
     *
     *@param      test   String for checking palindrome
     *                   at start.
     * @return           True if palindrome is present
     *                   at start of the string                  
     */
    public static Boolean checkOnLeftPattern(String test) {
        int start = 0;
        String part = "";
        String regex = "$";


        for (int i = 0; i < test.length() - 1; i++) {
            for (int k = 0; k <= i + 1; k++) {
                part = part + test.charAt(start++);

            }
            if (part.length() % 2 == 1) {
                String s1 = part.substring(0, (part.length() / 2));
                String s2 = part.substring((part.length() / 2) + 1,
                		part.length());
                for (int x = 0; x < s1.length(); x++)
                    regex = s1.charAt(x) + regex;
                Pattern checkRegex = Pattern.compile(regex, 
                		Pattern.CASE_INSENSITIVE);
                Matcher regexMatcher = checkRegex.matcher(part);
                if (regexMatcher.find()) {
                    System.out.println("Regex Test 2:");
                    System.out.println("\tInput line: " + test);
                    System.out.println("\tGot a palindrome on "
                    		+ "start:" + part);
                    System.out.println("\tPattern:" + regex);
                    return true;

                }
            } else {
                String s1 = part.substring(0, (part.length() / 2));
                String s2 = part.substring((part.length() / 2), 
                		part.length());
                for (int x = 0; x < s1.length(); x++)
                    regex = s1.charAt(x) + regex;

                Pattern checkRegex = Pattern.compile(regex, 
                		Pattern.CASE_INSENSITIVE);
                Matcher regexMatcher = checkRegex.matcher(part);
                if (regexMatcher.find()) {
                    System.out.println("Regex Test 2:");
                    System.out.println("\tInput line: " + test);
                    System.out.println("\tGot a palindrome at start: " 
                    + part);
                    System.out.println("\tPattern:" + regex);
                    return true;
                }
            }

            start = 0;
            part = "";
            regex = "$";
        }
        return false;

    }
    /**
      *checkOnRightPattern this method will check the a palindrome
      *is present at the end of the string using Regular
      *expression
      *
      *@param      test   String for checking palindrome
      *                   at end.
      *@return            True if palindrome is present
      *                   at end of the string                  
      */
    public static Boolean checkOnRightPattern(String test) {
        int end = test.length() - 1;
        String part = "";
        String regex = "^";


        for (int i = 0; i < test.length() - 1; i++) {
            for (int k = 0; k <= i + 1; k++) {
                part = test.charAt(end--) + part;

            }
            if (part.length() % 2 == 1) {
                String s1 = part.substring(0, (part.length() / 2));
                String s2 = part.substring((part.length() / 2) + 1,
                		part.length());
                for (int x = s1.length() - 1; x >= 0; x--) {
                    regex = regex + s2.charAt(x);

                }

                Pattern checkRegex = Pattern.compile(regex, 
                		Pattern.CASE_INSENSITIVE);
                Matcher regexMatcher = checkRegex.matcher(part);

                if (regexMatcher.find()) {
                    System.out.println("\tGot a palindrome at"
                    		+ " end: " + part);
                    System.out.println("\tPattern:" + regex);
                    return true;

                }
            } else {
                String s1 = part.substring(0, (part.length() / 2));
                String s2 = part.substring((part.length() / 2)
                		, part.length());
                for (int x = s1.length() - 1; x >= 0; x--) {
                    regex = regex + s2.charAt(x);

                }

                Pattern checkRegex = Pattern.compile(regex,
                		Pattern.CASE_INSENSITIVE);
                Matcher regexMatcher = checkRegex.matcher(part);
                if (regexMatcher.find()) {
                    System.out.println("\tGot a palindrome at "
                    		+ "end: " + part);
                    System.out.println("\tPattern:" + regex);
                    return true;
                }
            }
            end = test.length() - 1;
            part = "";
            regex = "^";
        }
        return false;
    }
    
    /**
      *testOwn3 this method will look for a 
      *palindrome of length 2 using String class
      *
      *
      *@param      aString   String for checking a
      *                      palindrome of length 2
      *@param      comment   Message displayed on screen
      *                      for the user if length 2
      *                      palindrome is found.                  
      */
    public static void testOwn3(String aString, String comment) {
        String display = aString;
        aString = aString.toLowerCase();
        if (aString.length() > 1 && aString.charAt(0) != ' ')
            for (int i = 0; i < aString.length() - 1; i++) {
                if ((aString.charAt(i) == aString.charAt(i + 1))
                		&& aString.charAt(i) != ' ') {
                    System.out.println("Own Test 3:");
                    System.out.println("\tInput line: " + display);
                    System.out.println("\t" + comment);
                    break;
                }
            }
    }
    /**
      *testPattern3 this method will look for a 
      *palindrome of length 2 using Regular expression
      *
      *@param      regex     A regular expression for
      *                      matching
      *@param      aString   String for checking a
      *                      palindrome of length 2
      *@param      comment   Message displayed on screen
      *                      for the user if length 2
      *                      palindrome is found.                  
      */
    public static void testPattern3(String regex, 
    		String aString, String comment) {
        Pattern checkRegex = Pattern.compile(regex,
        		Pattern.CASE_INSENSITIVE);
        Matcher regexMatcher = checkRegex.matcher(aString);
        boolean b = regexMatcher.find();

        if (b) {
            System.out.println("RegEx Test 3:");
            System.out.println("\tInput line: " + aString);
            System.out.println("\t Pattern:" + regex);
            System.out.println("\t" + comment);

        }

    }
    /**
      *testOwn4 this method will look for a 
      *palindrome of length 3 using String class
      *
      *
      *@param      aString   String for checking a
      *                      Palindrome of length 3
      *@param      comment   Message displayed on screen
      *                      for the user if lenght 3
      *                      palindrome is found.                  
      */
    public static void testOwn4(String aString, String comment) {
        if (aString.length() > 2 && aString.charAt(0) != ' ')
            for (int i = 0; i < aString.length() - 2; i++) {
                if ((aString.charAt(i) == aString.charAt(i + 2))
                		&& aString.charAt(i) != ' ') {
                    System.out.println("Own Test 4:");
                    System.out.println("\tInput line: " + aString);
                    System.out.println("\t" + comment);
                    break;
                }
            }
    }
    /**
      *testPattern4 this method will look for a 
      *palindrome of length 3 using Regular expression
      *
      *@param      regex     A regular expression for
      *                      matching
      *@param      aString   String for checking a
      *                      Palindrome of length 3
      *@param      comment   Message displayed on screen
      *                      for the user if lenght 3
      *                      palindrome is found.                  
      */
    public static void testPattern4(String regex, 
    		String aString, String comment) {
        Pattern checkRegex = Pattern.compile(regex,
        		Pattern.CASE_INSENSITIVE);
        Matcher regexMatcher = checkRegex.matcher(aString);
        boolean b = regexMatcher.find();
        if (b) {
            System.out.println("RegEx Test 4:");
            System.out.println("\tInput line: " + aString);
            System.out.println("\t Pattern:" + regex);
            System.out.println("\t" + comment);

        }
    }
    /**
      *testOwn5 this method will check if the string
      *consists of only "a" and "b" by using String
      *class
      *@param      aString   String for checking "a"
      *                      and "b"                   
      *@param      comment   Message displayed on screen
      *                      for the user if only "a" or 
      *                      "b" are present.                 
      */
    public static void testOwn5(String aString, String comment) {
        boolean onlyAB = false;
        if ((aString.length() > 0 && aString.charAt(0) != ' '))
            for (int i = 0; i < aString.length(); i++) {
                if (aString.charAt(i) == 'a' || aString.charAt(i)
                		== 'b' || aString.charAt(i) == ' ') {
                    onlyAB = true;
                } else {
                    onlyAB = false;
                    break;
                }
            }
        if (onlyAB) {
            System.out.println("Own Test 5:");
            System.out.println("\tInput line: " + aString);
            System.out.println("\t" + comment);
        }
    }
    /**
      *testPattern5 this method will check if the string
      *consists of only "a" or "b" by using regular 
      *expression    
      *@param      regex     A regular expression for
      *                      matching
      *@param      aString   String for checking "a"
      *                      and "b"
      *@param      comment   Message displayed on screen
      *                      for the user if "a" and "b"
      *                      ar found                  
      */
    public static void testPattern5(String regEx, String aString, 
    		String comment) {

        Pattern checkRegex = Pattern.compile(regEx);
        Matcher regexMatcher = checkRegex.matcher(aString);
        boolean b = regexMatcher.find();

        if (!b) {
            System.out.println("RegEx Test 5:");
            System.out.println("\tInput line: " + aString);
            System.out.println("\t Pattern:" + regEx);
            System.out.println("\t" + comment);

        }
    }
    /**
      *testOwn6 this method will check if no "a" and "b"
      *are present in the string using String class
      *@param      aString   String for checking "a"
      *                      and "b"
      *@param      comment   Message displayed on screen
      *                      for the user if no "a"
      *                      and "b" are found                
      */
    public static void testOwn6(String aString, String comment) {
       
    	
        if ((aString.length() > 0 && aString.charAt(0) != ' '))
            if ((aString.indexOf('a') < 0 && 
            		aString.indexOf('b') < 0)) {
                System.out.println("Own Test 6:");
                System.out.println("\tInput line: " + aString);
                System.out.println("\t" + comment);
            }
    }
    /**
      *testPattern6 this method will check if the string
      *no  "a" and "b" are present by using regular 
      *expression    
      *@param      regex     A regular expression for
      *                      matching
      *@param      aString   String for checking "a"
      *                      and "b"
      *@param      comment   Message displayed on screen
      *                      for the user if "a" and "b"
      *                      are found.               
      */
    public static void testPattern6(String regEx, String aString,
    		String comment) {

        Pattern checkRegex = Pattern.compile(regEx);
        Matcher regexMatcher = checkRegex.matcher(aString);
        boolean b = regexMatcher.find();

        if (!b) {
            System.out.println("RegEx Test 6:");
            System.out.println("\tInput line: " + aString);
            System.out.println("\t Pattern:" + regEx);
            System.out.println("\t" + comment);

        }
    }
    /**
      *testOwn7 this method will check if "." is present
      *in the String by using String class.
      *@param      aString   String for checking "."
      *@param      comment   Message displayed on screen
      *                      for the user if "." is present               
      */
    public static void testOwn7(String aString, String comment) {

        if (aString.indexOf('.') > -1) {
            System.out.println("Own Test 7:");
            System.out.println("\tInput line: " + aString);
            System.out.println("\t" + comment);
        }
    }
    /**
      *testPattern7 this method will check if "." is present
      *in the String by using Regular expression.    
      *@param      regex     A regular expression for
      *                      matching
      *@param      aString  String for checking "."
      *@param      comment   Message displayed on screen
      *                      for the user if "." is present                 
      */
    public static void testPattern7(String regEx, String aString,
    		String comment) {

        Pattern checkRegex = Pattern.compile(regEx);
        Matcher regexMatcher = checkRegex.matcher(aString);
        boolean b = regexMatcher.find();

        if (b) {
            System.out.println("RegEx Test 7:");
            System.out.println("\tInput line: " + aString);
            System.out.println("\t Pattern:" + regEx);
            System.out.println("\t" + comment);

        }
    }
    /**
      *testOwn8 this method will check if only "." is present
      *in the String by using String class.
      *@param      aString   String for checking only "."
      *@param      comment   Message displayed on screen
      *                      for the user if only "." is present               
      */
    public static void testOwn8(String aString, String comment) {

        if (aString.indexOf('.') == 0 && aString.length() == 1) {
            System.out.println("Own Test 8:");
            System.out.println("\tInput line: " + aString);
            System.out.println("\t" + comment);
        }
    }
    /**
      *testPattern8 this method will check if only "." is present
      *in the String by using Regular expression.    
      *@param      regex     A regular expression for
      *                      matching
      *@param      aString  String for checking only "."
      *@param      comment   Message displayed on screen
      *                      for the user if only "." is present                 
      */
    public static void testPattern8(String regEx, String aString,
    		String comment) {

        Pattern checkRegex = Pattern.compile(regEx);
        Matcher regexMatcher = checkRegex.matcher(aString);
        boolean b = regexMatcher.find();

        if (b) {
            System.out.println("RegEx Test 8:");
            System.out.println("\tInput line: " + aString);
            System.out.println("\t Pattern:" + regEx);
            System.out.println("\t" + comment);

        }
    }
    /**
	  * The main program.
	  *
	  * @param    args    command line arguments (ignored)
	  */
    public static void main(String[] args) throws Exception {
        File IPFile = new File("ip.txt");
        BufferedReader br = new BufferedReader(new 
        		FileReader(IPFile));
        String aString = "";

        while ((aString = br.readLine()) != null) {
        	// Read inputs from he ip.txt file

            testOwn1(aString, "Word has at least"
            		+ " one 'a' ", "'a' is"
            		+ " the part of the word");
            testPattern1("a", aString, "Word has "
            		+ "at least one 'a' ", 
            		"'a' is the part of the word");

            testOwn2(aString, "Palindrome anchored"
            		+ " at the beginning"
            		+ " and end of line.");
            testPattern2(aString, "Palindrome "
            		+ "anchored at the beginning"
            		+ " and end of line.");

            testOwn3(aString, "Include a palindrome "
            		+ "which is 2 "
            		+ "characters long.");
            testPattern3("^(?=.*(.)\\1)[a-zA-Z\\d\\s]*$",
            		aString,
            		"Include a palindrome which is 2 "
            		+ "characters long.");

            testOwn4(aString, "Include a palindrome"
            		+ " which is 3 "
            		+ "characters long.");
            testPattern4("^(?=.*([a-zA-Z\\d])(.)\\1)"
            		+ "[a-zA-Z\\d\\s]*$"
            		, aString, "Include a palindrome "
            				+ "which is 3 characters long.");


            testOwn5(aString, "The word consist "
            		+ "only of ’a’s or ’b’s");
            testPattern5("[c-zA-Z\\p{Punct}\\p{Digit}]", aString,
            		"The word consist only of ’a’s or ’b’s");

            testOwn6(aString, "The word doesn't consist of ’a’s"
            		+ " or ’b’s");
            testPattern6("[ab]", aString, "The word doesn't"
            		+ " consist of ’a’s or ’b’s");

            testOwn7(aString, "The word includes a '.'");
            testPattern7("[.]", aString, "The word includes a '.'");

            testOwn8(aString, "The word is == ’.’");
            testPattern8("^[.]$", aString, "The word is == ’.’");
            System.out.println("--------------------------");
        }
        br.close();
    }
}