/* FastSort.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 */
/** 
 *This program displays storage solution based on a 
 *interface and a functionality requirement
 * 
 *@author      Tejas Raval 	
 */
import java.util.Scanner;

interface StorageI < E > {

    public boolean add(E e); // 2
    public E get();
    public void clear(); // 2 3
    public boolean contains(E e);
    public boolean isEmpty();
    public void sort(); // 3
    public int size(); // 2 3
    public String getClassName();

}


public class FastAdd {
	
    /**
     *The main program.
     *
     *@param    args    command line arguments (ignored)
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        Generic < String > g = new Generic < String > ();
        g.add("tejasvini");
        g.add("a1a");
        g.add("tejas");
        g.add("a");
        g.add("aa");
        g.add("1...222");
        g.add("1");
        g.add("Tanvi");
        System.out.println("Before sort");
        g.displayGeneric();
        g.sort();
        System.out.println("After sort");
        g.displayGeneric();
        System.out.println("Get " + g.get());
        if (g.contains("n"))
            System.out.println("Yes");
        else
            System.out.println("No");

        if (g.isEmpty())
            System.out.println("Empty");
        else
            System.out.println("Not empty");
        System.out.println("Size " + g.size());

        System.out.println("Class name " + g.getClassName());
        
        g.clear();
        
        g.testAll();       
    }
}

class Node < E > {
    protected E data;
    protected Node < E > next;

    /**
     *This is the constructor which will be initialized
     *the node data d and the next link.
     * @param d  this will hold the data
     * @param n  this will hold the pointer to the next node
     */
    public Node(E d, Node < E > n) {
        data = d;
        next = n;
    }

    /**
     *Returns the data in the current node
     */

    public E getData() {
        return data;
    }
    /**
     *Returns the link to the next node
     */
    public Node < E > getLink() {
        return next;
    }
    /**
     *Sets the link to the current node
     */
    public void setLink(Node < E > n) {
        next = n;
    }
    /**
     *Sets data to the current node
     */
    public void setData(E d) {
        data = d;
    }
}
class Generic < E > implements StorageI < E > {
    protected Node < E > start;
    protected Node < E > end;
    public static int size;
    /**
     *Constructor to set the start, end
     *and size 
     */
    public Generic() {
        start = null;
        end = null;
        size = 0;
    }
    /**
     *Clear() will set the start and
     *end nodes to null. Ie, its
     *deletes the Generic
     */

    public void clear() {
        start = null;
        end = null;
        size = 0;
    }
    /**
     *getFirst() returns the first element 
     */
    public E getFirst() {
        return start.data;
    }
    /**
     *getFirst() returns the size of the generic 
     */
    public int getSize() {
        return size;
    }
    /**
     *add adds the data to 1st index 
     * @param   data to be added in the generic
     */
    public boolean add(E data) {
        Node < E > np = new Node(data, null);

        size++;
        if (start == null) {
            start = np;
            end = start;
        } else {
            np.setLink(start);
            start = np;
        }

        return true;

    }
   
       
    /**
     *displayGeneric the data in generic
     */

    public void displayGeneric() {

        if (size == 0) {
            System.out.println("Generic is empty");
            return;
        }
        if (start.getLink() == null) {
            System.out.println("Generic is :" + start.getData());
            return;
        }
        Node np = start;
        System.out.print("Generic is :" + start.getData() + "->");
        np = start.getLink();
        while (np.getLink() != null) {
            System.out.print(np.getData() + "->");
            np = np.getLink();
        }
        System.out.println(np.getData() + " ");
    }
    /**
     *get returns the 1st element from the  generic
     */
    public E get() {
        return (E) start.getData();
    }
    /**
     *contains return true if element 
     *passed to it is in the generic
     *
     *@param e data in string format
     */
    public boolean contains(E e) {
        Node np = this.start;
        for (int i = 1; i <= size; i++)

        {
            if (e.equals(np.getData())) {
                return true;
            }

            np = np.getLink();
        }
        return false;
    }
    /**
     *isEmpty returns true if generic is empty
     */
    public boolean isEmpty() {
        if (size == 0)
            return true;
        else return false;
    }
    /**
     *sort the generic using bubble sort
     */
    public void sort() {
        String[] toArray = new String[size];
        Node np = start;
        for (int i = 0; i < this.getSize(); i++)

        {
            toArray[i] = "" + np.getData() + "";
            np = np.getLink();
        }

        for (int i = 0; i < size - 1; i++)
            for (int j = 0; j < size - i - 1; j++) {
                char c1, c2;
                int counter = 0;
                int min = (toArray[j].length() < toArray[j + 1].length())
                		? toArray[j].length() : toArray[j + 1].length();
                		// to manage cases like Tejas and Tejasvini
                for (counter = 0; counter < min; counter++) {
                    if (toArray[j].charAt(counter) != toArray[j + 1].charAt(counter))
                        break;
                }

                if (counter == min) { 
                    if (toArray[j].length() != min) {
                        String temp = toArray[j];
                        toArray[j] = toArray[j + 1];
                        toArray[j + 1] = temp;
                    }
                } else {
                    c1 = toArray[j].charAt(counter);
                    c2 = toArray[j + 1].charAt(counter);
                    if ((int) c1 > (int) c2) {
                        String temp = toArray[j];
                        toArray[j] = toArray[j + 1];
                        toArray[j + 1] = temp;
                    }

                }
            }

        int tempSize = size;
        this.clear();
        for (int i = tempSize - 1; i >= 0; i--)
            this.add((E) toArray[i]);
    }
    

    /**
     *size returns the size of the generic
     */
    public int size() {
        return size;
    }
    /**
     *getClassName returns the class name
     */
    public String getClassName() {
        return this.getClass().getSimpleName();
    }
    /**
     *testAll is the test function
     */
    void testAll()
    {
    	System.out.println("-----Testing funcitons now------");
    	if(this.add((E) "Test")==true)
    		System.out.println("Add function is working. Returned true.");
    	else System.out.println("Error in add function. Retunred false");
    	
    	
    	if(this.get()!=null)
    		System.out.println("Get function is working.A value is returned.");
    	else System.out.println("Error in get function. Retunred null");
    	
    	this.clear();
    	if(size==0)
    		System.out.println("Clear function is working.");
    	else System.out.println("Error in get function.");
    	
    	this.add((E) "Test");
    	if(this.contains((E) "Test")==true)
    		System.out.println("Contains function is working.");
    	else System.out.println("Error in Test function.");
    	
    	if(this.isEmpty()==false)
    		System.out.println("isEmpty function is working.");
    	else System.out.println("Error in isEmpty function.");
    	
    	if(this.size()==1)
    		System.out.println("Size function is working.");
    	else System.out.println("Error in size function.");
    	
    	if(this.getClassName()=="Generic")
    		System.out.println("getClassName function is working.");
    	else System.out.println("Error in getClassName function.");
    }
	

}