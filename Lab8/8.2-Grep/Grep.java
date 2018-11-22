/* Grep.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 */
/** 
 *This program implements few Grep commands
 * 
 *@author      Tejas Raval 
 */
import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

public class Grep {
	/**
     *CommandIsQ() will implement -q command
     *@param   pattern  text to be searched
     *@param   args     argument array
     *@param   fileLoc  pointer where file name starts in args
     */
    static void CommandIsQ(String pattern, String[] args, int fileLoc)
    throws FileNotFoundException {
        String regEx = ".*\\b" + pattern + "\\b.*";
        for (int k = args.length - 1; k > fileLoc; k--) {
            String data = "";
            String fileName = "";
            int loc = args[k].lastIndexOf("\\");

            for (int i = loc + 1; i < args[k].length(); i++)
                fileName = fileName + args[k].charAt(i);
            File IPFile = new File(args[k]);
            Scanner sc = new Scanner(IPFile);
            while (sc.hasNext()) {
                data = data + sc.nextLine() + "\n";
            }

            Pattern checkRegex = Pattern.compile(regEx);
            Matcher regexMatcher = checkRegex.matcher(data);
            boolean b = regexMatcher.find();

            if (b == true) {
                System.out.println("Returing with exit code" +
                    " 0 as I got the value");
                return;
            }
        }
    }
    /**
     *CommandIsL() will implement -l command
     *@param   pattern  text to be searched
     *@param   args     argument array
     *@param   fileLoc  pointer where file name starts in args
     */
    static void CommandIsL(String pattern, int fileLoc, String[] args)
    throws FileNotFoundException {
        String regEx = ".*\\b" + pattern + "\\b.*";
        for (int i = args.length - 1; i > fileLoc; i--) {
            String data = "";
            File IPFile = new File(args[i]);
            Scanner sc = new Scanner(IPFile);
            while (sc.hasNext()) {
                data = data + sc.nextLine() + "\n";
            }
            Pattern checkRegex = Pattern.compile(regEx);
            Matcher regexMatcher = checkRegex.matcher(data);
            boolean b = regexMatcher.find();
            if (b == true) {
                int loc = args[i].lastIndexOf("\\");

                for (int k = loc + 1; k < args[i].length(); k++)
                    System.out.print(args[i].charAt(k));

                return;
            }

        }
    }
    /**
     *CommandIsWC() will implement -w -c command
     *@param   pattern  text to be searched
     *@param   args     argument array
     *@param   fileLoc  pointer where file name starts in args
     */
    static void CommandIsWC(String pattern, String[] args, int fileLoc)
    throws FileNotFoundException {
        String regEx = ".*\\b" + pattern + "\\b.*";
        for (int k = args.length - 1; k > fileLoc; k--) {
            int counter = 0;
            String data = "";
            String fileName = "";
            int loc = args[k].lastIndexOf("\\");

            for (int i = loc + 1; i < args[k].length(); i++)
                fileName = fileName + args[k].charAt(i);
            File IPFile = new File(args[k]);
            Scanner sc = new Scanner(IPFile);
            while (sc.hasNext()) {
                data = data + sc.nextLine() + "\n";
            }
            String line[] = data.split("\\r?\\n");
            for (int i = 0; i < line.length; i++) {
                Pattern checkRegex = Pattern.compile(regEx);
                Matcher regexMatcher = checkRegex.matcher(line[i]);
                boolean b = regexMatcher.find();

                if (b == true) {
                    counter++;
                }

            }
            System.out.println(fileName + " :" + counter);
        }
    }
    /**
     *CommandIsC() will implement -c command
     *@param   pattern  text to be searched
     *@param   args     argument array
     *@param   fileLoc  pointer where file name starts in args
     */
    static void CommandIsC(String pattern, String[] args, int fileLoc)
    throws FileNotFoundException {
        String regEx = pattern;
        for (int k = args.length - 1; k > fileLoc; k--) {
            int counter = 0;
            String data = "";
            String fileName = "";
            int loc = args[k].lastIndexOf("\\");

            for (int i = loc + 1; i < args[k].length(); i++)
                fileName = fileName + args[k].charAt(i);
            File IPFile = new File(args[k]);
            Scanner sc = new Scanner(IPFile);
            while (sc.hasNext()) {
                data = data + sc.nextLine() + "\n";
            }
            String line[] = data.split("\\r?\\n");
            for (int i = 0; i < line.length; i++) {
                Pattern checkRegex = Pattern.compile(regEx);
                Matcher regexMatcher = checkRegex.matcher(line[i]);
                boolean b = regexMatcher.find();

                if (b == true) {
                    counter++;
                }
            }
            System.out.println(fileName + " :" + counter);
        }
    }
    /**
     *CommandIsW() will implement -w command
     *@param   pattern  text to be searched
     *@param   args     argument array
     *@param   fileLoc  pointer where file name starts in args
     */
    static void CommandIsW(String pattern, int fileLoc, String[] args)
    throws FileNotFoundException {
        String regEx = ".*\\b" + pattern + "\\b.*";
        for (int k = args.length - 1; k > fileLoc; k--) {
            String data = "";
            String fileName = "";
            int loc = args[k].lastIndexOf("\\");

            for (int i = loc + 1; i < args[k].length(); i++)
                fileName = fileName + args[k].charAt(i);
            File IPFile = new File(args[k]);
            Scanner sc = new Scanner(IPFile);
            while (sc.hasNext()) {
                data = data + sc.nextLine() + "\n";
            }
            String line[] = data.split("\\r?\\n");
            for (int i = 0; i < line.length; i++) {
                Pattern checkRegex = Pattern.compile(regEx);
                Matcher regexMatcher = checkRegex.matcher(line[i]);
                boolean b = regexMatcher.find();

                if (b == true) {
                    System.out.println(fileName + " :" + line[i]);
                }


            }
        }
    }
    /**
     *checkIndex() will implement -q command
     *@param   s       args array
     *@param   match   command to be matched
     *@param   counter number of the commands given
     */
    static boolean checkIndex(String[] s, String match, int counter) {
        for (int i = 0; i < counter; i++) {
            if (s[i].equals(match))
                return true;

        }
        return false;
    }
    /**
     *The main program.
     *
     *@param    args    command line arguments ()
     */
    public static void main(String[] args) throws IOException {
        String data = "";
        String[] commands = new String[4];
        String[] pattern = new String[100];
        String regEx = "([^\\s]+(\\.(?i)(txt|py))$)";
        int fileLoc = 0;
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i] + " ");

        }
        System.out.println();
        for (fileLoc = args.length - 1; fileLoc > 0; fileLoc--) {
            Pattern checkRegex = Pattern.compile(regEx);
            Matcher regexMatcher = checkRegex.matcher(args[fileLoc]);
            boolean b = regexMatcher.find();

            if (b == false) {
                break;

            }
        }
        String IPString = "";
        int argsLength = args.length;
        int counterC = 0;

        for (int i = 1; i < fileLoc + 1; i++) {
            if (args[i].equals("-w") || args[i].equals("-c") ||
                args[i].equals("-l") || args[i].equals("-q")) {

                commands[counterC++] = args[i];
            }
        }

        if (counterC == 4) {

            CommandIsQ(args[fileLoc], args, fileLoc);
        } else if (counterC == 3) {
            if (checkIndex(commands, "-q", counterC)) {
                CommandIsQ(args[fileLoc], args, fileLoc);
            } else
                CommandIsL(args[fileLoc], fileLoc, args);
        } else if (counterC == 2) {
            if (checkIndex(commands, "-q", counterC)) {
                CommandIsQ(args[fileLoc], args, fileLoc);
            } else if (checkIndex(commands, "-l", counterC)) {
                CommandIsL(args[fileLoc], fileLoc, args);
                System.out.println("in -l");
            } else {
                CommandIsWC(args[fileLoc], args, fileLoc);

            }

        } else if (counterC == 1) {
            if (args[1].equals("-w")) {
                CommandIsW(args[fileLoc], fileLoc, args);
            } else if (args[1].equals("-q")) {
                CommandIsQ(args[fileLoc], args, fileLoc);
            } else if (args[1].equals("-c")) {
                CommandIsC(args[fileLoc], args, fileLoc);
            } else if (args[1].equals("-l")) {
                CommandIsL(args[fileLoc], fileLoc, args);
            }
        }


    }
}