/* Storage.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 */
/** 
 *This program displays Storage method using generic and
 *Linked list
 * 
 *@author      Tejas Raval 
 */
import java.util.Scanner;
public class Storage {
	/**
     *The main program.
     *
     *@param    args    command line arguments (ignored)
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        Generic < String > g = new Generic < String > ();

        int ch = 0;
        do {
            System.out.println("-------------------------------");
            System.out.println("1. add(int index, E element)");
            System.out.println("2. addFirst(E e)");
            System.out.println("3. addLast(E e)");
            System.out.println("4. clear()");
            System.out.println("5. element()");
            System.out.println("6. remove()");
            System.out.println("7. remove(int index)");
            System.out.println("8. size()");
            System.out.println("9. boolean add(E e)");
            System.out.println("10. exit");

            ch = sc.nextInt();
            sc.nextLine();
            String data;
            int index;
            switch (ch) {
                case 1:
                    System.out.println("Enter  element to insert");

                    data = sc.nextLine();
                    System.out.println("Enter position");
                    index = sc.nextInt();
                    sc.nextLine();
                    if (g.getSize() == 0 && index >= 0) {
                        System.out.println("Adding it to first "
                        		+ "postion as Generic is empty");
                        g.addFirst(data);
                        g.displayGeneric();
                    } else if (g.getSize() == index) {

                        g.addLast(data);
                        g.displayGeneric();
                    } else if (index > 0 && index < g.getSize()) {
                        g.add(index - 1, data); // send postion-1 to the function
                        g.displayGeneric();
                    } else
                        System.out.println("Worng input");
                    break;
                case 2:
                    System.out.println("Enter integer element to insert");
                    data = sc.nextLine();
                    g.addFirst(data);
                    g.displayGeneric();
                    break;
                case 3:

                    System.out.println("Enter integer element to insert");
                    data = sc.nextLine();
                    g.addLast(data);
                    g.displayGeneric();
                    break;
                case 4:
                    if (g.getSize() != 0) {
                        g.clear();

                        g.displayGeneric();
                        System.out.println("size is " + g.getSize());
                    } else System.out.println("Generics is already empty");
                    break;
                case 5:
                	if(g.getSize()!=0)
                    System.out.println(g.getFirst());
                	else System.out.println("List is empty");
                    break;
                case 6:
                    if (g.getSize() != 0) {
                        g.remove(1);
                        g.displayGeneric();
                    } else System.out.println("No data " +
                        "to be removed. Generic is empty");

                    break;
                case 7:
                    System.out.println("Enter index");
                    index = sc.nextInt();
                    if (index < 1 || index > g.getSize())
                        System.out.println("Worng input");
                    else
                        g.remove(index - 1);
                    g.displayGeneric();
                    break;
                case 8:
                    System.out.println("Size of Generic is " + g.getSize());

                    break;
                case 9:

                    System.out.println("Enter integer element to added");
                    data = sc.nextLine();
                    if (g.getSize() == 0)
                        g.addFirst(data);
                    else g.addLast(data);
                    g.displayGeneric();

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

class Node < String > {
    protected String data;
    protected Node < String > next;

  /**
    *This is the constructor which will be initialized
    *the node data d and the next link.
    * @param d
    * @param n
   */
    public Node(String d, Node < String > n) {
        data = d;
        next = n;
    }

   /**
     *Returns the data in the current node
     */

    public String getData() {
        return data;
    }
    /**
     *Returns the link to the next node
     */
    public Node < String > getLink() {
        return next;
    }
    /**
      *Sets the link to the current node
      */
    public void setLink(Node < String > n) {
        next = n;
    }
    /**
      *Sets data to the current node
      */
    public void setData(String d) {
        data = d;
    }



}


class Generic < String > {
    protected Node < String > start;
    protected Node < String > end;
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
    public String getFirst() {
        return start.data;
    }
    /**
     *getFirst() returns the size of the generic 
     */
    public int getSize() {
        return size;
    }
   /* *//**
      *checks 
      *//*
    public boolean isEmpty() {
        return start == null;
    }*/
    
    /**
      *addFirst adds the data to 1st index.
      * @param   data  data to be added in generic
      */

    public void addFirst(String data) {
        Node np = new Node(data, null);
        size++;
        if (start == null) {
            start = np;
            end = start;
        } else {
            np.setLink(start);
            start = np;
        }
    }
    /**
      *addFirst adds the data to last index.
      * @param   data  data to be added in generic
      */

    public void addLast(String data) {
        Node np = new Node(data, null);
        size++;
        if (start == null) {
            start = np;
            end = start;
        } else {
            end.setLink(np);
            end = np;
        }
    }

    /**
     *addFirst adds the data to 1st index.
     * @param   index  index where data to be added
     * @param   data data to be added in the generic
     */
    public void add(int index, String data) {

        Node np = new Node(data, null);
        Node currp = start;
        //index = index - 1 ;
        for (int i = 1; i < size; i++) {
            if (i == index) {
                Node temp = currp.getLink();
                currp.setLink(np);
                np.setLink(temp);
                break;
            }
            currp = currp.getLink();
        }
        size++;
    }
    /**
     *remove removs the data to from the specific index.
     * @param   postion  postion from data to be removed
     */
    public void remove(int position) {
        if (position == 1) {
            start = start.getLink();
            size--;
            return;
        }
        if (position == size) {
            Node st = start;
            Node temp = start;
            while (st != end) {
                temp = st;
                st = st.getLink();
            }
            end = temp;
            end.setLink(null);
            size--;
            return;
        } else {
            Node currpt = start;
            //position = position - 1 ;
            for (int i = 1; i < size - 1; i++) {
                if (i == position) {
                    Node temp = currpt.getLink();
                    temp = temp.getLink();
                    currpt.setLink(temp);
                    break;
                }
                currpt = currpt.getLink();
            }
            size--;
        }
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
            System.out.println("Generic is :"+start.getData());
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

}