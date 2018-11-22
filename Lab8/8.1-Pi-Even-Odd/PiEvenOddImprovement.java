/* PiEvenOddImprovement.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 */
/** 
 *This program counts Even and Odd number in a given file
 * 
 *@author      Tejas Raval 
 */
import java.io.BufferedReader;
import java.util.zip.GZIPInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class FileNotFound extends Exception {

    public FileNotFound(String message) {
        super(message);
    }
}

class NoNumberException extends Exception {

    public NoNumberException(String message) {
        super(message);
    }
}
class FileEmptyException extends Exception {

    public FileEmptyException(String message) {
        super(message);
    }
}

public class PiEvenOddImprovement extends Exception {
	/**
     *The main program.
     *
     *@param    args    command line arguments ()
     */
    public static void main(String[] args) throws IOException,
    FileNotFound, NoNumberException, FileEmptyException {
        Scanner sc = new Scanner(System.in);
        String data = "";
        if (args.length == 0) {

            System.out.println("Enter the data");
            while (true) {
                String line = sc.nextLine();
                if (line.equalsIgnoreCase("#")) {
                    break;
                }
                data = data + line;
            }
            System.out.println("data from STID is" + data);
        } //if ends
        else {
            String a = args[0];
            String datacopy = "";
            if (a.substring(a.length() - 2).equals("gz")) {
                System.out.println("Reading GZ");
                FileInputStream fileIP = new FileInputStream(a);
                GZIPInputStream GzIP = new GZIPInputStream(fileIP);
                InputStreamReader IPSR = new InputStreamReader(GzIP);
                BufferedReader scan = new BufferedReader(IPSR);

                while ((datacopy = scan.readLine()) != null) {
                    data = datacopy;
                } //while ends

            } //if ends
            else {
                System.out.println("Reading TXT");
                File file = new File(a);
                sc = new Scanner(file);
                while (sc.hasNext()) {
                    data = sc.nextLine();
                } //while ends
            } //else ends
            data = data.replaceAll("\\s", "");
        } //else ends
        Long even = 0l;
        Long odd = 0l;

        if (data.length() == 0)
            throw new FileEmptyException("Hey, File is empyt. "
            		+ "Please check");
        for (int i = 0; i < data.length(); i++) {
            int k = (int) data.charAt(i);
            if (k <= 57 && k >= 48)
                if (k % 2 == 0) {
                    even++;
                } // if ends
            else {
                odd++;
            } //else ends
        } //for ends
        if (even == 0 && odd == 0)
            throw new NoNumberException("There are no numbers here");

        System.out.println("Even numbers " + even);
        System.out.println("Odd numbers " + odd);

        if (even != 0)
            System.out.println("Odd/even :" + ((float) odd / even));
    } //main ends

} //class ends