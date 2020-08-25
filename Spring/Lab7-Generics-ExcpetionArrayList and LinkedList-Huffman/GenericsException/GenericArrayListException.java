/* GenericArrayListException.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
 /** 
 *This program implements ArrayList class of the Java with the concept of Generics
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
import java.util.Arrays;


public class GenericArrayListException<T extends Comparable> extends StorageImplementation<T> {
	private T element;
	int array_size = 10;
	public T [] test_array  = (T[]) new Comparable[array_size];
	
	//-----------------------------------------------
	private void increaseListSize(){
		array_size = array_size * 2;
		test_array = Arrays.copyOf(test_array, array_size);
        System.out.println("New length: "+test_array.length);
    }
	
	
	@Override
	public boolean add(T o) {
		// TODO Auto-generated method stub
		if(counter == array_size){
            increaseListSize();
        }
		
		try {
			 if(checkEvenOdd(o)) {
				 test_array[counter ++] = (T) o;
				 return true;
			 }
		} catch (NonEvenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 return false;
		
		
			 
		}
	
	@Override
	public void add(int index, T element) {
		// TODO Auto-generated method stub
		if(index > counter || index < 0) {throw new IndexOutOfBoundsException("Index: " + index);}
		if(counter == 0 || index == counter) {add(element);return;}
 
		try {
			 if(checkEvenOdd(element)) {
				 if(index == 0 && counter!=0) {System.arraycopy(test_array, 0, test_array, 0, counter);}
			        if(counter == 10){increaseListSize();}
			        System.arraycopy(test_array, index, test_array, index + 1, test_array.length - index - 1);
			        test_array[index] = (T) element;
			        counter++;
			 }
		} catch (NonEvenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  
		
	}

	@Override
	public boolean addAll(T[] os) {
		// TODO Auto-generated method stub
		return super.addAll(os);
	}

	@Override
	public boolean addAll(int index, T[] os) {
		// TODO Auto-generated method stub
		return super.addAll(index, os);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		test_array = Arrays.copyOf(test_array, array_size);
		counter = 0;
	}

	@Override
	public boolean contains(T o) {
		// TODO Auto-generated method stub
		for(int i = 0; i < counter; ++i)
        {
            if(test_array[i] == o)
            {
                return true;
            }
        }
		return false;
	}
	

	@Override
	public boolean containsAll(T[] os) {
		// TODO Auto-generated method stub
		return super.containsAll(os);
	}

	@Override
	public T get(int index) {
		if(index >= counter || index <0) {
			throw new IndexOutOfBoundsException("Index out of bounds: " + index);
		}
		return (T) test_array[index];
		//return null;
	}

	@Override
	public int indexOf(T o) {
		// TODO Auto-generated method stub
		for(int index = 0; index < counter; index++) {
			if(test_array[index] == o) {
				return index;
			}
        }
		return 0;
	}

	@Override
	public int lastIndexOf(T o) {
		// TODO Auto-generated method stub
		for(int index = counter - 1; index >= 0; index--) {
			if(test_array[index] == o) {
				return index;
			}
        }
		return 0;
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		if(index < counter){
            T element = test_array[index];
            while(index < counter){
            	test_array[index] = test_array[index + 1];
                index ++;
            }
            counter--;
            return (T) element;
        } else {
    			throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        } 
	}
	

	@Override
	public boolean remove(T o) {
		// TODO Auto-generated method stub
		for(int i=0;i<counter;i++) {
			if(test_array[i].equals(o)) {
				remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeAll(T[] os) {
		// TODO Auto-generated method stub
		return super.removeAll(os);
	}

	@Override
	public T set(int index, T element) {
		if(index >= counter || index <0) {
			throw new IndexOutOfBoundsException("Index out of bounds: " + index);
		}
		try {
			 if(checkEvenOdd(element)) {
				 test_array[index] = (T) element;
				 return test_array[index];
			 }
		} catch (NonEvenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return test_array[index];
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		super.sort();
		
	}
	
	
	public String toString() {
		return super.toString();	
	}
	
	
	public static void main(String args[]) {
		GenericArrayListException<String>  al = new GenericArrayListException<>();
		GenericArrayListException<Integer>  ia = new GenericArrayListException<>();
		/*ia.add(4);
		System.out.println("After adding : "+ia);
		ia.add(5);
		System.out.println("After adding Odd Number : "+ia);
		System.out.println("Contains 4 :"+ia.contains(4));
		System.out.println("Contains 5 :"+ia.contains(5));----*/
		//System.out.println(al.add("aa"));
		//System.out.println(al.add("bb"));
		//System.out.println(al.add("ccc"));
		/*al.add("ho");
		al.add("afohaoi");
		al.add("alkjfajs");
		al.add("afjioaho");
		al.add("jupoklkn");
		al.add("9877");
		al.add("89");
		al.add("10");
		al.add("Rite");
		al.add("Lipishaa");
		al.add("Tejass");
		al.add("20");*/
		ia.add(2);
		ia.add(4);
		ia.add(6);
		ia.add(8);
		//ia.addAll(0, new Integer[]{2, 87, 4});
		ia.set(0, 144);
		//ia.set(0, );
		//ia.addAll(new Integer[]{2, 80, 41});
		System.out.println("After adding Odd Number : "+ia);
		//System.out.println("After adding : "+al);
		//System.out.println("Counter : "+al.counter);
		//al.add(10, "Tejas");
		//al.remove(10);
		//ArrayList<String>  al1 = al;
		//LinkedList<String> ll = new LinkedList<> ();
		/*al.add("1");
		al.add("8");
		al.add("10");
		al.add("Rit");
		al.add("Lipisha");
		al.add("Tejas");
		al.add("2");
		ll.add("1");
		ll.add("8");
		ll.add("10");
		ll.add("Rit");
		ll.add("Lipisha");
		ll.add("Tejas");
		ll.add("2");
		System.out.println("After adding : "+al.equals(al1));*/
}
}
