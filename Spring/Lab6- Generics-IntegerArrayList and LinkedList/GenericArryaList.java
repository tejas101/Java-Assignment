/* GenericArryaList.java  
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

public class GenericArryaList<T extends Comparable> extends StorageImplementation<T> {
	private T element;
	int array_size = 10;
	public T [] test_array  = (T[]) new Comparable[array_size];
	//public int size = 0;
	
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
        test_array[counter ++] = (T) o;
		return true;
	}
	
	/*public int sizeOfArray(){
        return size;
    }*/

	@Override
	public void add(int index, T element) {
		// TODO Auto-generated method stub
		//public T [] test_array  = (T[]) new Comparable[array_size];
		if(index > counter && index < 0) {
			System.out.println("Invalid Index!");
			return;
		}
		if(counter == 0 || index == counter) {
			add(element);
			return;
		}
		if(index == 0 && counter!=0) {
			System.arraycopy(test_array, 0, test_array, 0, counter);
		}
        if(counter == 10){
            increaseListSize();
        }
        System.arraycopy(test_array, index, test_array, index + 1, test_array.length - index - 1);
        test_array[index] = (T) element;
        counter++;
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
		// TODO Auto-generated method stub
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
            throw new ArrayIndexOutOfBoundsException();
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
		// TODO Auto-generated method stub
		//T temp=test_array[index];
		test_array[index] = (T) element;
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
		GenericArryaList<Integer>  al = new GenericArryaList<>(); 
		/*al.add("1");
		al.add("8");
		al.add("10");
		al.add("Rit");
		al.add("Lipisha");
		al.add("Tejas");
		al.add("2");
		System.out.println("After adding : "+al);
		al.sort();
		System.out.println("After sorting : "+al);
		System.out.println("Should be\n\t0\n\t" + al.size());*/
        al.add(1);
        al.addAll(new Integer[]{2, 3, 4});
        System.out.println("Should be\n\t4\n\t" + al.size());
        System.out.println("Should be\n\t{1, 2, 3, 4}\n\t" + al);
        al.addAll(0, new Integer[]{5, 6, 7});
        System.out.println("Should be\n\t{5, 6, 7, 1, 2, 3, 4}\n\t" + al);
        al.sort();
        System.out.println("Should be\n\t{1, 2, 3, 4, 5, 6, 7}\n\t" + al);
        al.add(1, 3);
        System.out.println("Should be\n\t{1, 3, 2, 3, 4, 5, 6, 7}\n\t" + al);
        System.out.println("Should be\n\t1\n\t" + al.indexOf(3));
        System.out.println("Should be\n\t3\n\t" + al.lastIndexOf(3));
        al.remove(new Integer(3));
        System.out.println("Should be\n\t{1, 2, 3, 4, 5, 6, 7}\n\t" + al);
        al.remove(6);
        System.out.println("Should be\n\t{1, 2, 3, 4, 5, 6}\n\t" + al);
        System.out.println("Should be\n\tfalse\n\t" + al.contains(7));
        System.out.println("Should be\n\ttrue\n\t" + al.contains(4));
        System.out.println("Should be\n\tfalse\n\t" + al.containsAll(new Integer[]{4, 5, 6, 7}));
        System.out.println("Should be\n\ttrue\n\t" + al.containsAll(new Integer[]{4, 5, 6}));
        System.out.println("Should be\n\t21\n\t" + al.hashCode());
        System.out.println("1001.  Should be\n\tfalse\n\t" + al.removeAll(new Integer[]{7, 8, 9}));
        System.out.println("Should be\n\t{1, 2, 3, 4, 5, 6}\n\t" + al);
        System.out.println("100.  Should be\n\ttrue\n\t" + al.removeAll(new Integer[]{6, 7, 8})); //<----
        System.out.println("Should be\n\t{1, 2, 3, 4, 5}\n\t" + al);
        System.out.println("Should be\n\ttrue\n\t" + al.removeAll(new Integer[]{4, 5}));
        System.out.println("Should be\n\t{1, 2, 3}\n\t" + al);
        al.clear();
        System.out.println("should be\n\tempty\n\t" + al.isEmpty() + " : " + al);
        System.out.println("Should be\n\t0\n\t" + al.hashCode());
}
	

}
