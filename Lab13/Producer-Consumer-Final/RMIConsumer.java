/* RMIConsumer.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 */
/** 
 *This file has the code for Consumer working. Client
 *will connect this file via RMI
 * 
 *@author      Tejas Raval 
 */
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

interface ConsumerWork extends Remote {
    public ArrayList < Integer > Cwork(ArrayList < Integer > tray, int item)
    		throws RemoteException;
}
class CInterfaceClass extends UnicastRemoteObject
implements ConsumerWork {

    public CInterfaceClass() throws RemoteException {}

    //@Override
    public ArrayList < Integer > Cwork(ArrayList < Integer > tray, int item) 
    		throws RemoteException {
        tray.remove(item);
        return tray;
    }
}
public class RMIConsumer {
    public static void main(String[] argv) {
        try {
            CInterfaceClass CWorkObj = new CInterfaceClass();
            System.setProperty("java.rmi.server.hostname", "129.21.30.37");
            LocateRegistry.createRegistry(2002);
            Naming.rebind("rmi://localhost:2002/WorkC", CWorkObj);

            System.out.println("Consumer Server is ready.");
        } catch (Exception e) {
            System.out.println("Consumer Server failed: " + e);
        }
    }
}