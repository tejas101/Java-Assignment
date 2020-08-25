/* IntegerArrayList.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 *This program implements ArrayList class of the Java
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
import java.util.Arrays;

public class IntegerArrayList extends IntegerStorageImplementation implements IntegerStorage {
	public int [] test_array;
	
	public IntegerArrayList() {
		test_array = new int[20];
	}
	//Dynamically increase the size of the array.
	private void increaseListSize(){
		test_array = Arrays.copyOf(test_array, test_array.length*2);
        //System.out.println("\nNew length: "+test_array.length);
    }
	//Get the data on the specific index
	public int get(int index){
		if(index==0)
			index=1;
        if(index-1>-1&&index-1<counter){
            return test_array[index-1];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    
	}
	//Return the index of the element
	public int indexOf(int element){
		int temp_index = 0;
		for(int index = 0; index < counter; index++) {
			if(test_array[index] == element) {
				temp_index = index;
			}
        }
		return temp_index;
	}
	
	 
	//Add the element to the array
	public boolean add(int element){
        if(test_array.length - counter <= 10){
            increaseListSize();
        }
        test_array[counter ++] = element;
        return true;
    }
	//Add the element to specific index
	public void add(int index, int element){
		if(index > counter && index < 0) {
			System.out.println("Invalid Index!");
			return;
		}
		if(counter == 0 || index == counter) {
			add(element);
			return;
		}
		if(index == 0 && counter!=0) {
			System.arraycopy(test_array, 0, test_array, 1, size() + 1);
			test_array[0] = element;
			counter++;
			
		}
        if(test_array.length - counter <= 10){
            increaseListSize();
        }
        System.arraycopy(test_array, index, test_array, index + 1, test_array.length - index - 1);
        test_array[index] = element;
        counter++;
        }
	//Remove the element from the list
	public int remove(int index){
        if(index < counter){
            int element = test_array[index];
            test_array[index] = 0;
            int temp = index;
            while(temp < counter){
            	test_array[temp] = test_array[temp + 1];
            	test_array[temp + 1] = 0;
                temp ++;
            }
            counter--;
            return element;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }         
    }
	
	//Check if the element is in the list
	public boolean contains(int element){
		int result = 0;
		for(int index = 0; index < counter; index++) {
			if(test_array[index] == element) {
				result = 1;
			}
		}
		if(result == 1) {
			return true;
		}
		else {return false;}
	}
		
	
	public int size(){
        return counter;
    }
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		counter=0;
		test_array=null;
		
	}

	@Override
	public int lastIndexOf(int o) {
		int temp_index = 0;
		for(int index = 0; index < counter; index++) {
			if(test_array[index] == o) {
				//System.out.println(index+ " " +test_array[index]);
				temp_index = index;
			}
        }
		return temp_index;
	}

	@Override
	public boolean remove(Integer o) {
		// TODO Auto-generated method stub
		for(int i=0;i<counter;i++) {
			if(test_array[i]==(int) o) {
				this.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public int set(int index, int element) {
		// TODO Auto-generated method stub
		if(index-1>test_array.length) {
			System.out.println("Invalid index. Returning -1");
			return -1;
		}
		else
		{
		int temp=test_array[index-1];
		test_array[index-1]=element;
		return temp;}
	}
	
	public void sort() {
		
		super.sort();
		 
	}
	
public String toString() {
		return super.toString();
		
	}
	
	public static void main(String args[]) {
		IntegerArrayList mal = new IntegerArrayList();
		/*mal.add(3);
		mal.add(-2);
		mal.add(1);
		mal.sort();
		System.out.println("here"+mal.toString());*/
		mal.add(new Integer(2));
		mal.add(new Integer(5));
		mal.add(new Integer(1));
		mal.add(new Integer(23));
		mal.add(new Integer(14));
		//System.out.println(mal.toString());
		System.out.println(mal.toString());
		System.out.println("Adding 29");
		mal.add(new Integer(29));
		System.out.println(mal.toString());
		System.out.println("\nElement at Index 5:"+mal.get(5));
		System.out.println("\nList size: "+mal.size());
		System.out.println("Removing element at index 2: "+mal.remove(2));
		System.out.println("Adding 20");
		mal.add(new Integer(20));
		System.out.println("Adding 5");
		mal.add(new Integer(5));
		System.out.println(mal.toString());
		System.out.println("\nIndex of element 5: "+mal.contains(5));
		System.out.println("\nIndex of element 5: "+mal.lastIndexOf(5));
		System.out.println("\nIndex of element 5: "+mal.indexOf(5));
		mal.add(3, new Integer(100));
		System.out.println(mal.toString());
		System.out.println();
		System.out.println("Adding 5,6,7 at index 8");
		mal.addAll(8, new int[]{5, 6, 7});
		System.out.println(mal.toString());
		System.out.println();
		System.out.println("Adding 44,66,77 at index 4");
		mal.addAll(4, new int[]{44,66, 77});
		System.out.println(mal.toString());System.out.println();
		System.out.println("Removing 5 , 6, 7");
		System.out.println("Checking remove all "+mal.removeAll(new int[]{5,6, 7}));
		System.out.println(mal.toString());
		System.out.println();
		System.out.println("Now sorting ");
		mal.sort();
		System.out.println(mal.toString());
		System.out.println();
		
	}

	 

	
}
