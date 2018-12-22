import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Producer {

    /**
     * This is the Producer working who's task is to 
     * just add particular item to the storage tray
     * @throws ClassNotFoundException 
     * @throws InterruptedException 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        ServerSocket listener = new ServerSocket(9290);
        //System.out.println("New socket made");

        while (true) {
            Socket socket = listener.accept();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to ArratList
            ArrayList < Integer > tray = (ArrayList < Integer > ) ois.readObject();
            //read the item number sent by storage
            int temp = (int) ois.readObject();
            //add the item to the tray
            tray.add(temp);
            System.out.println("Producer added item-"+temp);
            //create the output stream
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //wirte the tray back on the stream and send it back to Client ie storage
            oos.writeObject(tray);

            ois.close();
            oos.flush();
            oos.close();
            socket.close();
            //System.out.println("Socket is closed");
             
        } //while


    } //main
}