/* RMIProducer.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 */
/** 
 *This file has the code for Producer working. Client
 *will connect this file via RMI
 * 
 *@author      Tejas Raval 
 */
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
//import java.security.AccessControlException;


interface ProducerWork extends Remote {
    public ArrayList < Integer > Pwork(ArrayList < Integer > tray, int item)
    		throws RemoteException;
}
class PInterfaceClass extends UnicastRemoteObject
implements ProducerWork {

    public PInterfaceClass() throws RemoteException {}

    //@Override
    public ArrayList < Integer > Pwork(ArrayList < Integer > tray, int item) 
    		throws RemoteException {
        tray.add(item);
        return tray;
    }

}
public class RMIProducer {
    public static void main(String[] argv) {
        try {

            PInterfaceClass PWorkObj = new PInterfaceClass();
            /**/
            System.setProperty("java.rmi.server.hostname", "129.21.22.196");
            LocateRegistry.createRegistry(2002);
            Naming.rebind("rmi://localhost:2002/WorkP", PWorkObj);


            System.out.println("Producer Server is ready.");
        } catch (Exception e) {
            System.out.println("Producer Server failed: " + e);
        }
    }
}