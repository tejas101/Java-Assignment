/* ScavengerHunt.java  
 * Version: 1
 *      
 * Revisions: 
 *     
 * Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 * Lipisha Chaudhary MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 * ----------Program Description-----------
 * Program whihc automates the process of downloading
 * and executing of a file and its content.
 *@author      Tejas Raval 
 *@author      Lipisha Chaudhary
 */
import java.sql.*;
import java.net.*;
import java.io.*;
import java.util.regex.*;

public class ScavengerHunt {
    public static void main(String args[]) throws IOException, InterruptedException{
        final String user = "csci605";
        final String pass = "sometables";
        final String url = "jdbc:postgresql://reddwarf.cs.rit.edu/";
        String result = "";
        String hidden_html = "";
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            System.out.println("Connected to Database!");
            String query = "SELECT to_parse FROM sites;";
            ResultSet res = connection.createStatement().executeQuery(query);
            if (res.next()) {
                result = res.getString("to_parse");
                System.out.println("Select Statement Result : "+result);
            }

            URL url_result = new URL(result);
            URLConnection url_Connection = url_result.openConnection();
            DataInputStream data = new DataInputStream(url_Connection.getInputStream());
            String inputLine;
            while ((inputLine = data.readLine()) != null) {
                if (inputLine.contains("hidden_html")) {
                    System.out.println(inputLine);
                    String pattern = "hidden_html=(.*)-->";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(inputLine);
                    if (m.find()) {
                        hidden_html = m.group(1);
                        System.out.println("Hidden HTML : " + hidden_html);
                    }
                }
            }
            String fileName = args[0] + "\\Ranges.java";
            Process p1 = null;
            Process p2 ;
            BufferedReader in_process;
            try (BufferedInputStream in = new BufferedInputStream(new URL(hidden_html).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
                System.out.println("\nRanges.java Output: \n\t");
                try
                {   p1 = Runtime.getRuntime().exec("javac -cp src src//Ranges.java");
                    p2 = Runtime.getRuntime().exec("java -cp src Ranges");
                    in_process = new BufferedReader(
                            new InputStreamReader( p2.getInputStream() ));
                    String line;
                    while((line = in_process.readLine()) != null) {
                        System.out.println("Test: "+line);
                    }
                    //Ranges.main(new String[0]);
                }
                catch(IOException e) {
                    System.err.println("Error on exec() method");
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MalformedURLException me) {
            System.out.println("MalformedURLException: " + me);
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe);
        }
    }
}

