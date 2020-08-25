/* IntegerLinkedList.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 *This program implements LinkedList class of Java.
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
class Node{
	Node next=null;
	int data;
	Node(int a){
		data=a;
		
	}
	 Node() {
		// TODO Auto-generated constructor stub
	}
}
public class IntegerLinkedList extends IntegerStorageImplementation implements IntegerStorage  {
	Node start;
	Node end;
	
	IntegerLinkedList(){
		start= new Node();
	}
/**
 * Add and element at the end of the list.
 */
	@Override
	public boolean add(int o) {
		// TODO Auto-generated method stub
		if(counter==0) {
			start= new Node(o);
			end=start;
			counter++;
			}
		else {
			Node temp= new Node(o);
			end.next=temp;
			end=temp;
			counter++;
			
		}
		return false;
	}
/**
 * Add an element at the specific postion.
 */
	@Override
	public void add(int index, int element) {
		// TODO Auto-generated method stub
		if(!(index<=counter+1 && index>-1)) {
			System.out.println("Invalid index");
			return;
		}
		Node temp= new Node(element);
		if(counter==0 ||index==counter) {
			this.add(element);
			return;
		}
		if(index==0 && counter!=0) {
			temp.next=start;
			start=temp;
			counter++;
			return;
			
		}
		Node point=start;
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
	public boolean addAll(int[] os) {
		return super.addAll(os);
	}
/**
 * Add all the elements in the array at the specific index
 */
	@Override
	public boolean addAll(int index, int[] os) {
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
	@Override
	public boolean contains(int o) {
		// TODO Auto-generated method stub
		Node temp=start;
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
	public boolean containsAll(int[] os) {
		// TODO Auto-generated method stub
		
		return super.containsAll(os);
	}
/**
 * check if an other LinkeList is eqaul to the current linked list
 */
	@Override
	public boolean contentEquals(IntegerStorage o) {
		return super.contentEquals(o);
	}
/**
 * Get the element at the specific index from the linked list
 */
	@Override
	public int get(int index) {
		// TODO Auto-generated method stub
		if(!(index<=counter)) {
			System.out.println("Invalid input. IndexOutOfBoundsException. Returning 0 forcefully");
			return 0;
		}
		Node temp=start;
		for(int i=0;i<index-1;i++) {
			temp=temp.next;
		}
		return temp.data;
	}
/**
 * Get the index of the specfic element
 */
	@Override
	public int indexOf(int o) {
		// TODO Auto-generated method stub
		Node temp=start;
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
	@Override
	public int lastIndexOf(int o) {
		// TODO Auto-generated method stub
		Node temp=start;
		int last=-1;
		for(int i=0;i<counter;i++) {
			if(temp.data==o) {
				last=i;
			}
			temp=temp.next;
		}
		if(last!=-1) {
			System.out.println(o+" found at index "+(last+1));
			return last;
		}
		System.out.println("Data not found");
		return last;
	}
	/**
	 * Remove the element from the list at an index
	 */

	@Override
	public int remove(int index) {
		// TODO Auto-generated method stub
		if(!(index<=counter || index<=0)) {
			System.out.println("Invalid input. ");
			return 0;
		}
		if(index==0) {
			start=start.next;
			//System.out.println(index+" removed from the list");
			counter--;
			return 1;
		}
		Node temp=start;
		for(int i=0;i<index-1;i++) {
			temp=temp.next;
		}
		temp.next=temp.next.next;
		//System.out.println(index+" removed from the list");
		counter--;
		return 1;
		
		//return 0;
		}
/**
 * Remove an element from the list
 */
	@Override
	public boolean remove(Integer o) {
		// TODO Auto-generated method stub
		Node temp=start;
		for(int i=0;i<counter;i++) {
			if(temp.data==(int)o) {
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
	public boolean removeAll(int[] os) {
		// TODO Auto-generated method stub
		return super.removeAll(os);
		
	}
/**
 * Set an element in the list
 */
	@Override
	public int set(int index, int element) {
		// TODO Auto-generated method stub
		if(!(index<=counter)) {
			System.out.println("Invalid input. IndexOutOfBoundsException. ");
			return 0;
		}
		Node temp=start;
		for(int i=0;i<index-1;i++) {
			temp=temp.next;
		}
		temp.data=element;
		return 1;
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
	
	 public static void main(String args[]) {
		 IntegerStorage obj=new IntegerLinkedList();
		// IntegerStorage obj2=new IntegerLinkedList();
		obj.add(1);
		
		 obj.add(882);
		 obj.add(2);
		 obj.add(3);
		 obj.add(-2);
		 obj.add(4);
		 obj.addAll(0, new int[]{5, 6, 7});
		 //obj.add(61);
		 System.out.println(obj.toString());
		 obj.sort();
		 System.out.println("Sorting it");
		 System.out.println(obj.toString());
		 
	 }

}
