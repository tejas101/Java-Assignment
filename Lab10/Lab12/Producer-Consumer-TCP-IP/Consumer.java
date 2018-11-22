import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Consumer {

    /**
     * This is the consumer working who's task is to remove a specific item from the tray
     * @throws ClassNotFoundException 
     * @throws InterruptedException 
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        ServerSocket listener = new ServerSocket(9291);
        //System.out.println("New socket made");
        while (true) {
            Socket socket = listener.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to ArrayList
            ArrayList < Integer > tray = (ArrayList < Integer > ) ois.readObject();
            int temp = (int) ois.readObject();
            tray.remove(temp);
            System.out.println("Consumer removed a item");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(tray);
            ois.close();
            oos.flush();
            oos.close();
            socket.close();
            //System.out.println("Socket is closed");
        } //while


    } //main
}