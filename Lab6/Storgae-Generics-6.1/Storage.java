/* Storage.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 */
/** 
 *This program displays storage system that is 
 *behaves like a set.
 * 
 *@author      Tejas Raval 
 */
import java.util.Scanner;
public class Storage < E > {
    /**
     *The main program.
     *
     *@param    args    command line arguments (ignored)
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        Generic < String > g = new Generic < String > ();
        Generic < String > g1 = new Generic < String > ();

        int ch = 0;
        do {
            System.out.println("-------------------------------");
            System.out.println("1. clear()");
            System.out.println("2. remove(int index)");
            System.out.println("3. size()");
            System.out.println("4. boolean add(E e)");
            System.out.println("5.toArray");
            System.out.println("6.contains");
            System.out.println("7.boolean addAll");
            System.out.println("8.boolean removeAll");
            System.out.println("9.Test Method");
            System.out.println("10. exit");

            ch = sc.nextInt();
            sc.nextLine();
            String data;
            int index;
            switch (ch) {
             	case 1:
                    if (g.getSize() != 0) {
                        g.clear();

                        g.displayGeneric();
                        System.out.println("size is " + g.getSize());
                    } else System.out.println("Set is already empty");
                    break;
                case 2:
                    System.out.println("Enter index");
                    index = sc.nextInt();
                    if (index < 1 || index > g.getSize())
                        System.out.println("Worng input");
                    else
                    if (g.remove(index))
                        System.out.println("Element removed "
                        		+ "from the set");
                    else System.out.println("False- Eelemnt "
                    		+ "not found");
                    g.displayGeneric();
                    break;
                case 3:
                    System.out.println("Cardinality of set"
                    		+ " is " + g.getSize());

                    break;
                case 4:

                    System.out.println("Enter "
                    		+ "element to added");
                    data = sc.nextLine();
                    g.add(data);
                    g.displayGeneric();

                    break;
                case 5:
                    if (g.getSize() != 0) {
                        String[] output = g.toArray();
                        for (int i = 0; i < output.length; i++) {
                            System.out.print(output[i]);
                            System.out.println("");
                        }
                    } else System.out.println("Set is empty");
                    break;

                case 6:
                    System.out.println("Enter element to"
                    		+ " searched");
                    data = sc.nextLine();
                    if (g.contains(data)==true)
                        System.out.println("Contians");
                    else System.out.println("False-	Not found");
                    break;
                case 7:
                    g1.add("RIT");
                    g1.add("Rochester");
                    g1.add("Tejas");
                    g1.add("???Gang???");
                    g.addAll(g1);
                    g.displayGeneric();
                    break;
                case 8:

                    if (g.removeAll(g1))
                        System.out.println("There were modifications");
                    else
                        System.out.println("There were no "
                        		+ "modifications");
                    g.displayGeneric();

                    break;
                case 9:
                	Generic < String > testObj = new Generic < String > ();
                	
                	testObj.testAll();
                    
                    break;
                case 10:

                    System.out.println("Thanks buddy");
                    System.exit(0);

                    break;
                    
                default:
                    System.out.println("Wrong Input ");
                    break;
            }

            ch = 0;
        } while (ch != 10);
    }
}

class Node < E > {
    protected E data;
    protected Node < E > next;

    /**
     *This is the constructor which will be initialized
     *the node data d and the next link.
     * @param d
     * @param n
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
class Generic < E > {
    protected Node < E > start;
    protected Node < E > end;
    public int size;

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
     *deletes the Set
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
     *getFirst() returns the size of the set 
     */
    public int getSize() {
        return size;
    }
    /**
     *addFirst adds the data to set.
     * @param   data  to be added in set
     */

    public boolean add(E data) {
        Node < E > np = new Node(data, null);
        if (this.contains_int(np.getData()) == -1) {
            size++;
            if (start == null) {
                start = np;
                end = start;
            } else {
                np.setLink(start);
                start = np;
            }
        } 
   return true;
    }
    /**
     *addAll adds the data from a collection to set.
     * @param   collection  to be added in set
     */

    public boolean addAll(Generic < E > g1) {
        Node < E > np = g1.start;
        boolean flag=false;
        for (int k = 1; k <= g1.getSize(); k++) {
            if (this.contains_int(np.getData()) == -1) {
                add(np.getData());
                System.out.println("Added " + np.getData());
                flag=true;
            } else
                System.out.println("Not Added " + np.getData());
            np = np.getLink();
        }
        if(flag==false&&(this.getSize()==g1.getSize()))
        	return false;
        else return true;
    }
    /**
     *remove removes the data to from the specific index.
     * @param   postion  position from data to be removed
     */
    public boolean remove(int position) {
        if (position == 1) {
            start = start.getLink();
            size--;
            return true;
        } else if (position == size) {
            Node st = start;
            Node temp = start;
            while (st != end) {
                temp = st;
                st = st.getLink();
            }
            end = temp;
            end.setLink(null);
            size--;
            return true;
        } else {
            Node currpt = start;
            position = position - 1;
            for (int i = 1; i < size - 1; i++) {
                if (i == position) {
                    Node temp = currpt.getLink();
                    temp = temp.getLink();
                    currpt.setLink(temp);
                    size--;
                    return true;
                }
                currpt = currpt.getLink();
            }
        }
        return false;
    }
    /**
     *displayGeneric the data in set
     */

    public void displayGeneric() {

        if (size == 0) {
            System.out.println("Set is empty");
            return;
        }
        if (start.getLink() == null) {
            System.out.println("Set is :{" +
        start.getData() + "}");
            return;
        }
        Node np = start;
        System.out.print("Set is :{" + start.getData() + ",");
        np = start.getLink();

        while (np.getLink() != null) {
            System.out.print(np.getData() + ",");
            np = np.getLink();
        }
        System.out.println(np.getData() + " }");
    }
    /**
     *toArray converts the data in set to array
     */
    public String[] toArray() {
        if (start.getLink() == null) {
            String[] toArray = new String[1];
            toArray[0] = (String) start.getData();
            return toArray;
        }
        String[] toArray = new String[size];
        Node np = start;
        for (int i = 0; i < this.getSize(); i++)

        {
            toArray[i] = "" + np.getData() + "";
            np = np.getLink();
        }
        return toArray;
    }
    /**
     *contains_int return location if element 
     *passed to it is in the set
     *
     *@param e data to be searched
     */
    public boolean contains(E e) {



        Node np = this.start;
        for (int i = 1; i <= size; i++)

        { 
            if (e.equals(np.getData())) { 
                return true;
            } else {
                np = np.getLink();
            }
        }
        return false;
    }
    /**
     *contains_int return location if element 
     *passed to it is in the set
     *
     *@param e data to be searched
     */
    public int contains_int(E e) {
        Node np = this.start;
        for (int i = 1; i <= size; i++)

        { 
            if (e.equals(np.getData())) { 
                return i;
            } else {
                np = np.getLink();
            }
        }
        return -1;
    }
    /**
     *elements to be removed from the set
     *
     *@param e elements to be removed from set
     */
    public boolean removeAll(Generic < E > g1) {
        boolean modified = false;
        Node < E > np = g1.start;
        for (int k = 1; k <= g1.getSize(); k++) {
            int location = this.contains_int(np.getData());
            if (location != -1) {
                this.remove(location);
                modified = true;
            }
            np = np.getLink();
        }
        return modified;

    }
    /**
     *testAll tests all methods
     */
    public void testAll()
    {
    	this.clear();
    	if(this.add((E) "CR7")==true)
    		System.out.println("add() works");
    	else System.out.println("add() fails");
    	
    	
    	if(this.addAll(this)==false)
    		System.out.println("You can not add yourself "
    				+ "to yourself.");
    	else System.out.println("addAll() worked");
    	
    	if(this.removeAll(this)==true)
    		System.out.println("RemoveAll worked");
    	else System.out.println("RemoveAllfailed");
    	this.displayGeneric();
    	
    	this.add((E) "CR7");
    	String[] testArray=this.toArray();
    	if(testArray.length>0)
    		System.out.println("toArray worked");
    	else
    		System.out.println("toArray failed");
    	
    	
    	if(this.contains((E) "CR7")==true)
    		System.out.println("contains() works");
    	else
    		System.out.println("contains() failed");
    	
    	if(this.remove(1)==true)
    		System.out.println("remove() works");
    	else
    		System.out.println("remove() failed");
    	this.displayGeneric();
    	
    	this.add((E) "CR7");
    	if(size==1)
    		System.out.println("size() works");
    	else
    		System.out.println("size() failed");
    	
    	this.clear();
    	if(size==0)
    		System.out.println("clear() works");
    	else
    		System.out.println("clear() failed");
    	
    	
    }
    
    

}