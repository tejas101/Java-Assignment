/* GenericLinkedList.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 *This program implements LinkeList class of the Java with the concept of Generics
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
class Node<T extends Comparable>{
	Node<T> next=null;
	T data;
	Node(T a){
		data=a;
		
	}
	 Node() {
		// TODO Auto-generated constructor stub
	}
}
public class GenericLinkedList<T extends Comparable> extends StorageImplementation<T>  {
	  Node < T > start;
      Node < T > end;
      //int size it will be in Parent class as counter;
      //int counter;
      public GenericLinkedList() {
          start = null;
          end = null;
          //counter = 0;
      }
      
     
     
     /**
      *addFirst adds the data to last index.
      * @param   data  data to be added in generic
      */
    
   
    
    @Override
	public boolean add(T o) {
    	Node<T> np = new Node(o);
        counter++;
        if (start == null) {
            start = np;
            end = start;
            return true;
        } else {
            end.next=np;
            end = np;
            return true;
        }
         
	}
    
    public void add(int index, T data) {

    	if(!(index<=counter+1 && index>-1)) {
    		throw new ArrayIndexOutOfBoundsException();
			
		}
		Node<T> temp= new Node(data);
		if(counter==0 ||index==counter) {
			this.add(data);
			return;
		}
		if(index==0 && counter!=0) {
			temp.next=start;
			start=temp;
			counter++;
			return;
			
		}
		Node<T> point=start;
		for(int i=0;i<index-1;i++) {
			point=point.next;
		}
		temp.next=point.next;
		point.next=temp;
		counter++;//check
    }
    
    /**
     * Add all the elements in the array to the end of the list.
     */
    	@Override
    	public boolean addAll(T[] os) {
    		return super.addAll(os);
    	}
    /**
     * Add all the elements in the array at the specific index
     */
    	@Override
    	public boolean addAll(int index, T[] os) {
    		return super.addAll(index, os);
    		// TODO Auto-generated method stub
    		
    	}
    /**
     * clear the list
     */
    	@Override
    	public void clear() {
    		// TODO Auto-generated method stub
    		start=null;
    		counter=0;
    		
    	}
    /**
     * Check if the list contains the specific element
     */
    	
    	public boolean contains(T o) {
    		// TODO Auto-generated method stub
    		Node<T> temp=start;
    		for(int i=0;i<counter;i++) {
    			if(temp.data==o) {
    				return true;
    			}
    			temp=temp.next;
    		}
    		return false;
    	}
    /**
     * Check if list contains all the element in the given array
     */
    	@Override
    	public boolean containsAll(T[] os) {
    		// TODO Auto-generated method stub
    		
    		return super.containsAll(os);
    	}
    /**
     * check if an other LinkeList is eqaul to the current linked list
     */
    	@Override
    	public boolean contentEquals(StorageInterface o) {
    		return super.contentEquals(o);
    	}
    /**
     * Get the element at the specific index from the linked list
     */
    	
		@Override
    	public T get(int index) {
    		// TODO Auto-generated method stub
    		if(index>counter) {
    			throw new NullPointerException("Wrong Index value given");
    		}
    		Node<T> temp=start;
    		for(int i=0;i<index;i++) {
    			temp=temp.next;
    		}
    		return (T) temp.data;
    	}
    /**
     * Get the index of the specfic element
     */
    	//@Override
    	public int indexOf(T o) {
    		// TODO Auto-generated method stub
    		Node<T> temp=start;
    		for(int i=0;i<counter;i++) {
    			if(temp.data==o) {
    				return i;
    			}
    			temp=temp.next;
    		}
    		return -1;
    	}
    /**
     * check if list is empty
     */
    	@Override
    	public boolean isEmpty() {
    		// TODO Auto-generated method stub
    		return super.isEmpty();
    	}
    /**
     * Check the last index of the specific element
     */
    	//@Override
    	public int lastIndexOf(T o) {
    		// TODO Auto-generated method stub
    		Node<T> temp=start;
    		int last=-1;
    		for(int i=0;i<counter;i++) {
    			if(temp.data==o) {
    				last=i;
    			}
    			temp=temp.next;
    		}
    		if(last!=-1) {
    			return last;
    		}
    		System.out.println("Data not found");
    		return last;
    	}
    	/**
    	 * Remove the element from the list at an index
    	 * @readdAllturn 
    	 * @return 
    	 * @return 
    	 */

    	
    	public T remove(int index) {
    		// TODO Auto-generated method stub
    		if((index>counter || index<0)) {
    			throw new NullPointerException("Wrong Index value given");
    		}
    		if(index==0) {
    			Node<T > temp=start;
    			T hold=start.data;
    			start=start.next;
    			//System.out.println(index+" removed from the list");
    			counter--;
    			return  hold;
    		}
    		Node<T> temp=start;
    		for(int i=0;i<index-1;i++) {
    			temp=temp.next;
    		}
    		T hold= temp.next.data;
    		temp.next=temp.next.next;
    		//System.out.println(index+" removed from the list");
    		counter--;
    		return hold;
    		
    		//return 0;
    	}
    /**
     * Remove an element from the list
     */
    	
    	public boolean remove(T o) {
    		// TODO Auto-generated method stub
    		Node<T> temp=start;
    		for(int i=0;i<counter;i++) {
    			if(temp.data==(T)o) {
    				 this.remove(i);
    				 return true;
    			}
    			temp=temp.next;
    		}
    		return false;
    	}
    /**
     * Remove all the elements from the list with respect to the given array
     */
    	@Override
    	public boolean removeAll(T[] os) {
    		// TODO Auto-generated method stub
    		return super.removeAll(os);
    		
    	}
    /**
     * Set an element in the list
     */
    	
    	public T set(int index, T element) {
    		// TODO Auto-generated method stub
    		if(!(index<=counter)) {
    			throw new NullPointerException("Wrong Index value given");
    		}
    		Node<T> temp=start;
    		for(int i=0;i<index;i++) {
    			temp=temp.next;
    		}
    		T hold=temp.data;
    		temp.data=element;
    		return (T) hold;
    	}
    /**
     * Get the size of the list
     */
    	@Override
    	public int size() {
    		// TODO Auto-generated method stub
    		return super.size();
    		
    	}
    	/**
    	 * Prints a String
    	 */
    	public String toString() {
    		
    		return super.toString();
    		
    	}
    	/**
    	 * Returns the hashcode of the list 
    	 */
    	public int hashCode() {
    		return super.hashCode();
    	}
    	/**
    	 * Sorts the data in the list.
    	 */
    	public void sort() {
    		super.sort();
    		 
    	}



		



		/*@Override
		public boolean containsAll(T[] os) {
			// TODO Auto-generated method stub
			return false;
		}*/


    	 public static void main(String args[]) {
    		 GenericLinkedList<String> obj=new GenericLinkedList<>();
    		// IntegerStorage obj2=new IntegerLinkedList();
    		//obj.add("Te");
    		//obj.add(1,"Tejas");
    		 /*GenericLinkedList<String> obj2=obj;
    		 obj2.add("1");
    		 obj2.add("2");
    		 obj2.add("3");*/
    		//obj.add("RIT");
    		
    		 obj.addAll(0, new String[]{"Raval","Ravals", "PQR"});
    		//obj.addAll(0, new Integer[]{-122,1,444,2,-9,5,3,433333,-0,3});
    		 //System.out.println(obj.toString());
    		 //obj.clear();
    		 //System.out.println(obj.isEmpty());
    		 //obj.add(0,"RIT");
    		 //obj.add("hah");
    		 //obj.add("dg");
    		//obj.add("a");
    		//obj.add("b");
    		//obj.add("ck");
    		 //GenericLinkedList<String> obj2=new GenericLinkedList<>();
    		// System.out.println(obj2.size());
    		 //obj2.addAll(0, new String[]{"Raval", "ABC", "Real Madrid"});
    		 //System.out.println(obj.containsAll(new String[]{"Ravals","ABC", "Real Madrid","ABC"}));    		 
    		 //obj.clear();
    		 //obj.add("f");
    		 //obj.add("e");
    		 obj.sort();
    		 System.out.println(obj.hashCode());
    		 //System.out.println(obj.removeAll( new String[]{"PQR","ABC", "Real Madrid"}));
    		 
    		/* System.out.println(obj.set(4,777777));
    		 System.out.println(obj.toString());
    		 System.out.println(obj.remove((Integer)7));
    		 System.out.println(obj.remove((Integer)5));
    		 System.out.println(obj.remove(7));
    		 System.out.println(obj.removeAll(new Integer[]{1,3,3,666,4545}));*/
    		 System.out.println(obj.toString());
    		 
    	 }

		}
