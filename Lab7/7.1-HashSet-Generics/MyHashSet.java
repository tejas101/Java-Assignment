/* MyHashSet.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 */
/** 
 *This program displays HashSet using generic
 * 
 *@author      Tejas Raval 
 */
import java.util.Scanner;

 interface SetI<E> {
	boolean	add(E e);

	boolean	addAll(Generic<? extends E> c);
	boolean	containsAll(Generic<?> c);
	boolean	removeAll(Generic<?> c);
	void	clear();
	boolean	contains(Object o);
	boolean	equals(Object o);
	int	hashCode();
	boolean	isEmpty();
	boolean	remove(Object o);
	int	size();
	Object[]	toArray();
	
}
public class MyHashSet < E > {
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
            System.out.println("2. remove(Object o)");
            System.out.println("3. size()");
            System.out.println("4. boolean add(E e)");
            System.out.println("5.toArray");
            System.out.println("6.contains");
            System.out.println("7.boolean addAll");
            System.out.println("8.boolean removeAll");
            System.out.println("9.boolean containsAll");
            System.out.println("10.boolean equals");
            System.out.println("11.HashCode");
            System.out.println("12.isEmpty()");
            System.out.println("13.Test Methods");
            System.out.println("14.add() Null");
            System.out.println("15.remove() Null");
            System.out.println("16. exit");

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
                    System.out.println("Enter element");
                    data = sc.nextLine();
                    if (g.remove(data))
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
                	g1.add("RIT");
                    g1.add("Rochester");
                    g1.add("Tejas");
                    g1.add("???Gang???");
                	System.out.println("value is "+g.containsAll(g1));
                	 if(g.containsAll(g1)==true)
                		 System.out.println("Set contains : {RIT,"
                		 		+ " Rochester, Tejas,???Gang???}");
                	 else
                		 System.out.println("Set doesn't  contains"
                		 		+ " all or some of  : {RIT, "
                		 		+ "Rochester, Tejas,???Gang???}");
                    g.displayGeneric();

                    break;
                case 10:
                	g1.add("RIT");
                    g1.add("Tejas");
                	 if(g.equals(g1)==true)
                		 System.out.println("2 set's are equal");
                	 else
                		 System.out.println("2 set's are not equal");
                    g.displayGeneric();

                    break;
                case 11:
                    g.displayGeneric();
                    g.displayHashCodes();

                    break;
                case 12:
                    if(g.isEmpty()==true)
                    	System.out.println("Set is empty");
                    else System.out.println("Set is not empty");
                    	

                    break;
                case 13:
                	Generic < String > testObj = new Generic < String > ();
                	
                	testObj.testAll();
                    
                    break;
                case 14:

                    g.add(null);
                    g.displayGeneric();

                    break;
                case 15:

                    g.remove(null);
                    g.displayGeneric();

                    break;
                case 16:

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
class Generic < E > implements SetI <E> {
    protected Node < E > start;
    protected Node < E > end;
    public int size;
    /**
      * Array To store the hash codes of the element added in the array 
      */
    public int [] index_arr= new int[2];
    //To check if Null character is present or not
    boolean NC=false;
      
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
        NC=false;
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
     *increaseSize increases the size of the index array if needed
     */
    public int[] increaseSize() {
    		int[] new_index_arr= new int[size*2];
    		System.arraycopy(this.index_arr,0, new_index_arr,0, size );
    		return new_index_arr;
    		
    	    	
    }
    /**
      *copyarray copies a part of the array from the given array
      *@param     a       Array for which a part to be copied
      *@param     postion Location from where array needs to be copied
      */
    public int[] copyarray(int [] a,int position){
    	int [] temp= new int[a.length];
    	System.arraycopy(a, position, temp, 0,a.length-position);
    	return temp;
    	
    }
    /**
      *add() adds the element in the set at the first postion
      *@param E data  the element to be added in the set 
      */
    public boolean add(E data) {
        Node < E > np = new Node(data, null);
        if (this.contains_int(np.getData()) == -1) {
        	
        	if(index_arr.length==size)
        	{ 
        		this.index_arr=this.increaseSize();
        	}
        	size++;
            if (start == null) {
            	if(np.getData()!=null)
            	index_arr[0]=np.getData().hashCode();
            	else index_arr[0]=-1;
            	start = np;
                end = start;
            } else {
            	System.arraycopy(this.copyarray(index_arr,0), 0,
            			index_arr, 1, size-1);
            	if(np.getData()!=null)
            	index_arr[0]=np.getData().hashCode();
            	else index_arr[0]=-1;
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

    public boolean addAll(Generic <? extends E> g1) {
        Node < E > np = (Node<E>) g1.start;
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
    public boolean remove(Object e) {
    	if(NC==false)
    	{
    		System.out.println("No null element to be removed");
    		return true;
    	}
    	
    	int temp_szie=index_arr.length;
        Node currpt = start;
        Node prev=start;
            for (int i = 1; i <=size; i++) {
            	if(e!=null&&currpt.data!=null) {
            	if (currpt.data.equals(e) ) {
            		if(size==1)
            		{
            			start = null;
            			end = null;
            			size = 0;
            			index_arr[size]=0;
            			return true;
            		}
            		else if(i==1)
            		{
            			  start = start.getLink();
            	            size--;
            	            System.arraycopy(this.copyarray(index_arr, i)
            	            		, 0, index_arr, 0, temp_szie-i);
            	            return true;	
            			
            		}
            		else if (i == size) {
                        Node st = start;
                        Node temp = start;
                        while (st != end) {
                            temp = st;
                            st = st.getLink();
                        }
                        end = temp;
                        end.setLink(null);
                        index_arr[size-1]=0;
                        size--;
                        return true;
                    } 
            		System.out.println("Element removed is:"+currpt.data+" "+e);
            		prev.setLink(currpt.getLink());
                    System.arraycopy(this.copyarray(index_arr, i), 0,
                    		index_arr, i-1, temp_szie-i);
                    index_arr[size-1]=0;
                    size--;
                    return true;
                }}
            	else
            	{   
            		NC=false;
            		if(size==1)
            		{
            			start = null;
            			end = null;
            			size = 0;
            			index_arr[size]=0;
            			return true;
            		}
            		else if(i==1)
            		{
            			  start = start.getLink();
            	            size--;
            	            System.arraycopy(this.copyarray(index_arr, i),
            	            		0, index_arr, 0, temp_szie-i);
            	            return true;	
            			
            		}
            		else if (i == size) {
                        Node st = start;
                        Node temp = start;
                        while (st != end) {
                            temp = st;
                            st = st.getLink();
                        }
                        end = temp;
                        end.setLink(null);
                        index_arr[size]=0;
                        size--;
                        return true;
                    } 
            		System.out.println("Element removed is:"+currpt.data+" "+e);
            		prev.setLink(currpt.getLink());
                    System.arraycopy(this.copyarray(index_arr, i),
                    		0, index_arr, i-1, temp_szie-i);
                    index_arr[size-1]=0;
                    size--;
                    return true;
            		
            	}
            	prev=currpt;
                currpt = currpt.getLink();
            }
         
        return false;     
    }
    /**
     *containsAll() checks if the element of a set is present in the other
     * @param   Collection elements of which will be cross checked in the other
     *          collection
     * @return              True if elements are encountred in the set         
     */
    
    public boolean containsAll(Generic < ? > g1)
    {
    	Node < E > np = (Node<E>) g1.start;
        boolean flag=false;
        for (int k = 1; k <= g1.getSize(); k++) {
            if (this.contains_int(np.getData()) != -1) {
                flag=false;
            } 
            else 
            { 
            	flag=true;
                break;
            }
                
            np = np.getLink();
        }
        if(flag==false)
        	return true;
        else return false;
    	
    }
    /**
      * equals() check if 2 sets are identical
      * @param   Collection  Check if two sets are equal
      * @return
     */
    public boolean equals(Generic < E > g1)
    {
    	Node < E > np = g1.start;
        boolean flag=false;
        if(this.getSize()==g1.getSize())
        for (int k = 1; k <= g1.getSize(); k++) {
            if (this.contains_int(np.getData()) != -1) {
                flag=true;
            } 
            else 
            { 
            	flag=false;
                break;
            }
                
            np = np.getLink();
        }
        if(flag==true)
        	return true;
        else return false;
    	
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
        System.out.print("Set is  :{" + start.getData() + ",");
        np = start.getLink();

        while (np.getLink() != null) {
            System.out.print(np.getData() + ",");
            np = np.getLink();
        }
        System.out.println(np.getData() + " }");
        	
    }
    /**
     *displayHashCodes display's the hash codes
     */

    public void displayHashCodes() {

        if (size == 0) {
            System.out.println("No hash codes");
            return;
        }
        
        System.out.print("Hash Codes array is: ");
        for(int i=0;i<size;i++)
        {
        	System.out.print(index_arr[i]+"   ");
        	
        }
        System.out.println("");
        	
    }
    /**
      *isEmpty() return true if set is empty
      */

    public boolean isEmpty() {

        if (size == 0) 
            return true;
        else return false;       	
    }
    
    /**
     *size() returns size of the set
     */
    public int size() {
        return size;
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
     *contains returns true if element 
     *is in the set
     *
     *@param e data to be searched
     */
    public boolean contains(Object e) {



        Node np = this.start;
        for (int i = 1; i <= index_arr.length; i++)

        {   if(e!=null)
        {
            if (e.hashCode()==index_arr[i-1]) { 
                return true;
            }}
        else return true;
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

        {   if(e!=null) {
            if (e.equals(np.getData())) { 
                return i;
            } else {
                np = np.getLink();
            }}
           else {
        	   if(NC==false)
        	   {  NC=true;
        		   return -1;
        		   }
        	   else return 1;
        	   
        	   
           }
        }
        if(size==0&&e==null)
        {
        	NC=true;
        }
        return -1;
    }
    /**
     *elements to be removed from the set
     *
     *@param e elements to be removed from set
     */
    public boolean removeAll(Generic < ? > g1) {
    	if(this.equals(g1)==true)
    	{
    		this.clear();
    		return true;
    	}
        boolean modified = false;
        Node < E > np = (Node<E>) g1.start;
        for (int k = 1; k <= g1.getSize(); k++) {
            int location = this.contains_int(np.getData());
            if(this.contains(np.getData()))
            {
                this.remove((String) np.getData());
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
    	System.out.println("Trying to add CR7");
    	if(this.add((E) "CR7")==true)
    		System.out.println("add() works");
    	else System.out.println("add() fails");
    	System.out.println("set now is ");
    	this.displayGeneric();
    	
    	System.out.println("trying addAll on self");
    	if(this.addAll(this)==false)
    		System.out.println("You can not add yourself "
    				+ "to yourself.");
    	else System.out.println("addAll() worked");
    	
    	System.out.println("trying addAll with other "
    			+ "set which is: {Tejas}");
    	Generic < String > testObj1 = new Generic < String > ();
    	testObj1.add("Tejas");
    	if(this.addAll((Generic<? extends E>) testObj1)==true)
    		System.out.println("addAll() worked");
    	else System.out.println("addAll() failed");
    	System.out.println("set after adding a other set using addAll()");
    	this.displayGeneric();
    	System.out.println("-----------");
    	System.out.println("trying removeAll(). Tejas will be removed");
    	if(this.removeAll(testObj1)==true)
    		System.out.println("RemoveAll worked");
    	else System.out.println("RemoveAllfailed");
    	System.out.println("after remove all set is");
    	this.displayGeneric();
    	System.out.println("-----------");
    	System.out.println("Trying toArray");
    	String[] testArray=this.toArray();
    	if(testArray.length>0)
    		System.out.println("toArray worked");
    	else
    		System.out.println("toArray failed");
    	
    	System.out.println("checking contains by looking for CR7");
    	if(this.contains((E) "CR7")==true)
    		System.out.println("contains() works");
    	else
    		System.out.println("contains() failed");
    	
    	System.out.println("removing CR7");
    	if(this.remove("CR7")==true)
    		System.out.println("remove() works");
    	else
    		System.out.println("remove() failed");
    	System.out.println("set after remove('CR7')");
    	this.displayGeneric();
    	
    	System.out.println("adding CR7 to test size");
    	this.add((E) "CR7");
    	if(size==1)
    		System.out.println("size() works");
    	else
    		System.out.println("size() failed");
    	
    	System.out.println("Testing hash code");
    	this.displayHashCodes();
    	
    	System.out.println("Trying clear()");
    	this.clear();
    	if(size==0)
    		System.out.println("clear() works");
    	else
    		System.out.println("clear() failed");
    	
    	System.out.println("Testing isEmpty()");
    	if(this.isEmpty()==true)
    		System.out.println("isEmpty() works");
    	else
    		System.out.println("isEmpty() failed");
    	
    	this.clear();
    	
    	System.out.println("Testing containsAll() by"
    			+ " adding Tejas and Lipisha");
    	this.add((E) "Tejas");
    	this.add((E) "Lipisha");
    	testObj1.add("Tejas");
    	testObj1.add("Lipisha");
    	
    	if(this.containsAll(testObj1)==true)
    		System.out.println("containsAll works");
    	else
    		System.out.println("containsAll() failed");
    	
    	System.out.println("Testing equals()");
    	Object temp=this;
    	if(temp.equals(testObj1)==false)
    		System.out.println("equals() works");
    	else
    		System.out.println("equals() failed");
    
    	
    }
	
    
    

}